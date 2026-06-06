package logic.gameLogic;

import graphic.Message;
import graphic.GameGraphic;
import graphic.drawers.HandDrawer;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public class TurnLogic
{
    private GameBoardLogic m_gameboard;
    private StackLogic m_stack;
    private final PlayerAction m_playerAction = new PlayerAction();
    private final BotStrategy m_botStrategy = new BotStrategy();
    private final AttacksResolver m_attacksResolver = new AttacksResolver();

    private int m_round = 1;
    private int m_turn = 1;
    private int m_score = 0;

    public TurnLogic(GameBoardLogic gameboard, StackLogic stack)
    {
        m_gameboard = gameboard;
        m_stack = stack;
    }

    public void botTurn(ActorLogic bot)
    {
        m_botStrategy.drawIfPossible(bot, m_stack);
        m_botStrategy.placeCards(bot, m_gameboard);
    }

    public void playerTurn(ActorLogic player)
    {
        boolean hasDraw = false;
        boolean turnOver = false;

        while (!turnOver)
        {
            GameGraphic.showGame(m_gameboard, m_stack, m_round, m_turn, m_score);
            HandDrawer.showHand(player);
            String input = Message.basicChoice();
            String[] action = input.split(" ");

            switch (action[0])
            {
                case "fin" :
                    turnOver = true;
                    m_turn++;
                    break;
                case "piocher" :
                    if (!hasDraw)
                    {
                        m_playerAction.drawCard(player, m_stack);
                        hasDraw = true;
                    }
                    else
                    {
                        Message.tell("Tu as déjà pioché, fais autre chose.");
                    }
                    break;
                case "placer" :
                    if (action.length == 3)
                    {
                        placeCardPlayer(action, player);
                    }
                    else
                    {
                        Message.tell("Format invalide. Exemple : placer 1 B3");
                    }
                    break;
                default :
                    Message.tell("Commande inconnue. Tapez [fin], [piocher] ou [placer <n> <pos>].");
                    break;
            }
        }
    }

    private void placeCardPlayer(String[] action, ActorLogic player)
    {
        try
        {
            int numeroCarte = Integer.parseInt(action[1]) - 1;
            String position = action[2];

            if (numeroCarte >= 0 && numeroCarte < player.handSize())
            {
                int index = m_gameboard.getIndex(position);
                if (index != -1)
                {
                    Optional<CardLogic> card = m_gameboard.getPlayerLine(index);
                    if (card.isEmpty())
                    {
                        AnimalLogic playerCard = player.getCard(numeroCarte);
                        if (m_playerAction.validateCost(player, m_gameboard, playerCard))
                        {
                            player.removeCard(numeroCarte);
                            m_playerAction.payCost(player, m_gameboard, playerCard);
                            m_gameboard.setPlayerLine(index, Optional.of(playerCard));
                            Message.tell("Tu as joué la carte " + playerCard.getName() + " sur la position " + position + ".");
                        }
                        else
                        {
                            Message.tell("Tu n'as pas les ressources nécessaires pour cette carte.");
                        }
                    }
                    else
                    {
                        Message.tell("Cette position contient déjà une carte.");
                    }
                }
                else
                {
                    Message.tell("Position incorrecte (ex : B1, B2, B3, B4).");
                }
            }
            else
            {
                Message.tell("Numéro de carte inexistant. Vérifiez votre main.");
            }
        }
        catch (NumberFormatException e)
        {
            Message.tell("Le premier paramètre doit être un chiffre ! (Exemple : placer 1 B2)");
        }
    }

    public int resolveAttacks()
    {
        int pointsGagnes = m_attacksResolver.resolveAll(m_gameboard);
        m_score += pointsGagnes;
        return pointsGagnes;
    }

    @Override
    public String toString()
    {
        return "Tour en cours : " + m_turn + " | Round : " + m_round + " | Score : " + m_score;
    }
}