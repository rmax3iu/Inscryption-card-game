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

    public void playerTurn(ActorLogic player, int currentRound, int currentTurn, int score)
    {
        boolean hasDraw = false;
        boolean turnOver = false;

        while (!turnOver)
        {
            GameGraphic.showGame(m_gameboard, m_stack, currentRound, currentTurn, score);
            HandDrawer.showHand(player);
            String input = Message.basicChoice();
            String[] action = input.split(" ");

            switch (action[0])
            {
                case "fin" :
                    turnOver = true;
                    break;
                case "piocher" :
                    if (Message.piocherCard(hasDraw))
                    {
                        m_playerAction.drawCard(player, m_stack);
                        hasDraw = true;
                    }
                    break;
                case "placer" :
                    if (Message.poserCard(action.length))
                    {
                        placeCardPlayer(action, player);
                    }
                    break;
                default :
                    Message.unkownCommand();
                    break;
            }
        }
    }

    private void placeCardPlayer(String[] action, ActorLogic player)
    {
        int numeroCarte = Message.getNumCard(action[1]);

        if(numeroCarte != -1) {
            String position = action[2];

            if (Message.cardExiste(numeroCarte,player.handSize()))
            {
                int index = m_gameboard.getIndex(position);
                if (Message.positionExiste(index))
                {
                    Optional<CardLogic> card = m_gameboard.getPlayerLine(index);
                    if (Message.cardNonVide(card.isPresent()))
                    {
                        AnimalLogic playerCard = player.getCard(numeroCarte);
                        if (Message.valideCost(m_playerAction.validateCost(player, m_gameboard, playerCard), playerCard.getName(), index))
                        {
                            player.removeCard(numeroCarte);
                            m_playerAction.payCost(player, m_gameboard, playerCard);
                            m_gameboard.setPlayerLine(index, Optional.of(playerCard));
                        }
                    }
                }
            }
        }
    }

    public int resolveAttacks()
    {
        int pointsGagnes = m_attacksResolver.resolveAll(m_gameboard);
        return pointsGagnes;
    }
}