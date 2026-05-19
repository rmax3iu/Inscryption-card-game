package gameLogic;

import actor.Player;
import actorLogic.BotLogic;

public class TurnLogic {
    private GameBordLogic m_gamebord;
    private StackLogic m_stack;

    public TurnLogic(GameBordLogic gamebord, StackLogic stack){
        m_gamebord = gamebord;
        m_stack = stack;
    }

    public void botTurn(BotLogic bot){

    }

    public void playerTurn(PlayerLogic player){

    }

    public void placeCard(){

    }

    public void drawCard(ActorLogic actor){

    }

    public void resolveAttacks(PlayerLogic player, BotLogic bot){

    }

}
