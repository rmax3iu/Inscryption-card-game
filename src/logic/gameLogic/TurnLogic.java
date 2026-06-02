package logic.gameLogic;

import logic.actorLogic.ActorLogic;

public class TurnLogic {
    private GameBoardLogic m_gamebord;
    private StackLogic m_stack;
    private final PlayerAction m_playerAction = new PlayerAction();
    private final BotStrategy m_botStrategy = new BotStrategy();
    private final AttacksResolver m_attacksResolver = new AttacksResolver();

    public TurnLogic(GameBoardLogic gamebord, StackLogic stack){
        m_gamebord = gamebord;
        m_stack = stack;
    }

    public void botTurn(ActorLogic bot){
        m_botStrategy.drawIfPossible(bot, m_stack);
        m_botStrategy.placeCards(bot,m_gamebord);
    }

    public void playerTurn(ActorLogic player) {

    }


    public int resolveAttacks(){
        return m_attacksResolver.resolveAll(m_gamebord);
    }

}
