package logic.gameLogic;

import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;
import java.util.Random;

public class BotStrategy
{
    public BotStrategy()
    {
    }

    // Pioche tant que la pioche n'est pas vide
    public void drawIfPossible(ActorLogic bot, StackLogic stack)
    {
        if (!stack.isEmptyBot())
        {
            bot.addCard(stack.drawBot());
        }
    }

    // Pose toutes les cartes qu'il peut poser
    public void placeCards(ActorLogic bot, GameBoardLogic board)
    {
        for (int indexCard = bot.handSize() - 1; indexCard >= 0; indexCard--)
        {
            AnimalLogic currentCard = bot.getCard(indexCard);
            if (canAfford(bot, board, currentCard))
            {
                int position = choosePlacementSlot(board);
                if (position != -1)
                {
                    // On vérifie qu'on puisse la poser
                    payCost(bot, board, currentCard);
                    board.setPreviewLine(position, Optional.of(currentCard));
                    bot.removeCard(indexCard);
                }
            }
        }
    }

    // Renvoie un booléen qui dit si on peut payer le coût de la carte
    private boolean canAfford(ActorLogic bot, GameBoardLogic board, AnimalLogic card)
    {
        if (card.getSummonCost().isBonesCost() && bot.getBones() >= card.getSummonCost().getBones())
        {
            return true;
        }
        else if (card.getSummonCost().isBloodCost() && board.countBotCard() >= card.getSummonCost().getBlood())
        {
            return true;
        }
        else if (card.getSummonCost().isFree())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Fait payer au bot le coût de la carte
    private void payCost(ActorLogic bot, GameBoardLogic board, AnimalLogic card)
    {
        if (card.getSummonCost().isBloodCost())
        {
            sacrificeBotCards(board, card.getSummonCost().getBlood());
            bot.addBones(card.getSummonCost().getBlood());    // Vu qu'on sacrifie des cartes on gagne des os
        }
        else if (card.getSummonCost().isBonesCost())
        {
            bot.addBones(-card.getSummonCost().getBones());
        }
    }

    // Sacrifie le nombre de cartes nécessaire
    private void sacrificeBotCards(GameBoardLogic board, int count)
    {
        int nbCarteSacrifie = 0;
        int i = 0;
        while (nbCarteSacrifie < count && i < GameBoardLogic.BOARD_SIZE)
        {
            Optional<CardLogic> card1 = board.getBotLine(i);
            Optional<CardLogic> card2 = board.getPreviewLine(i);

            // On vérifie que la carte peut être sacrifiée (en gros si c'est un obstacle ou une vraie carte)
            if (card1.isPresent() && card1.get().canBeSacrify())
            {
                board.setBotLine(i, card1.get().sacrify());       // On sacrifie la carte et le retour de sacrify() c'est soit un Optional.empty soit la même carte, c'est pour le pouvoir plusieurs vies (Many life)
                nbCarteSacrifie++;
            }
            // On vérifie que la carte peut être sacrifiée (en gros qu'elle existe et si c'est un obstacle ou une vraie carte)
            if (card2.isPresent() && card2.get().canBeSacrify())
            {
                board.setPreviewLine(i, card2.get().sacrify());       // On sacrifie la carte et le retour de sacrify() c'est soit un Optional.empty soit la même carte, c'est pour le pouvoir plusieurs vies (Many life)
                nbCarteSacrifie++;
            }
            i++;
        }
    }

    // Renvoie la position où l'on peut placer la carte et -1 si on ne peut pas
    private int choosePlacementSlot(GameBoardLogic board)
    {
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++)
        {
            if (board.getPreviewLine(i).isEmpty())
            {
                return i;
            }
        }
        return -1;
    }
}