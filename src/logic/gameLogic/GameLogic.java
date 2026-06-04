package logic.gameLogic;

import logic.actorLogic.ActorLogic;

public class GameLogic
{
    private int m_nbVictory;
    private StackLogic m_stack;

    public GameLogic()
    {
        m_nbVictory = 0;
        m_stack = new StackLogic();
    }

    public void play()
    {

    }

    private int round(){
        int score = 0;
        boolean isEnd = false;

        ActorLogic bot = ActorLogic.newBotLogic();
        ActorLogic player = ActorLogic.newPlayerLogic();

        GameBoardLogic board = new GameBoardLogic();

        TurnLogic turn;

        while(!isEnd){
            turn = new TurnLogic(board,stack);

            turn.botTurn(bot);
            turn.playerTurn(player);

            score = turn.resolveAttacks();


            if(score <= -5 || score >= 5){
                isEnd = true;
            }
        }

        return score;
    }
}

