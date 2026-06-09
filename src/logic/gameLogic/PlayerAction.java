package logic.gameLogic;

import graphic.Message;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public class PlayerAction
{
    public PlayerAction()
    {
    }

    public void drawCard(ActorLogic player, StackLogic stack)
    {
        if (!stack.isEmpty())
        {
            AnimalLogic card = stack.draw();
            player.addCard(card);
            Message.tell("Tu as pioché une carte " + card.getName() + ".");
        }
    }

    public boolean validateCost(ActorLogic player, GameBoardLogic board, AnimalLogic card)
    {
        return  (card.getSummonCost().isBonesCost() && player.getBones() >= card.getSummonCost().getBones())
                || (card.getSummonCost().isBloodCost() && board.countPlayerCards() >= card.getSummonCost().getBlood())
                || (card.getSummonCost().isFree());
    }

    public void payCost(ActorLogic player, GameBoardLogic board, AnimalLogic card)
    {
        if (card.getSummonCost().isBloodCost())
        {
            sacrificePlayerCards(board, card.getSummonCost().getBlood());
            player.addBones(card.getSummonCost().getBlood());
        }
        else if (card.getSummonCost().isBonesCost())
        {
            player.addBones(-card.getSummonCost().getBones());
        }
    }

    public void sacrificePlayerCards(GameBoardLogic board, int count)
    {
        int nbSacrifie = 0;
        while (nbSacrifie < count)
        {
            // On demande la carte qu'on veut sacrifier
            String reponse = Message.ask("Donne la position de la carte que vous voulez sacrifier (il reste " + (count - nbSacrifie) + " cartes à sacrifier)");
            int index;
            // On traduit la demande en un indice pour connaître la carte
            switch (reponse)
            {
                case "B1":
                    index = 0;
                    break;
                case "B2":
                    index = 1;
                    break;
                case "B3":
                    index = 2;
                    break;
                case "B4":
                    index = 3;
                    break;
                default:
                    index = -1;     // Quand le joueur écrit quelque chose d'inattendu
                    break;
            }

            // On vérifie que l'utilisateur demande à sacrifier une carte sur une case du plateau qui existe
            if (index != -1)
            {
                Optional<CardLogic> card = board.getPlayerLine(index);

                // On vérifie que la carte peut être sacrifiée (en gros si c'est un obstacle ou une vraie carte)
                if (card.isPresent() && card.get().canBeSacrify())
                {
                    board.setPlayerLine(index, card.get().sacrify());       // On sacrifie la carte et le retour de sacrify() c'est soit un Optional.empty soit la même carte c'est pour le pouvoir plusieurs vies(Many life)
                    nbSacrifie++;
                }
            }
            else
            {
                Message.tell("Saisie incorrecte ! (type de saisie valide : B1/B2/B3/B4)");
            }
        }
    }
}