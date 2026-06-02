package logic.gameLogic;

import logic.actorLogic.ActorLogic;
import logic.cardLogic.CardLogic;

import java.util.ArrayList;

public class RoundLogic
{
    private Integer m_score;
    private GameBoardLogic m_gamebord;
    private StackLogic m_stack;
    private ActorLogic m_player;
    private ActorLogic m_bot;

    public RoundLogic()
    {
        this.m_score = 0;
        this.m_gamebord = new GameBoardLogic();
        this.m_stack = new StackLogic();
    }

    public void play(ActorLogic player, ActorLogic bot)
    {
        this.m_player = player;
        this.m_bot = bot;

        while(!isEnd())
        {
            // 1. On instancie un nouveau tour en lui passant le plateau et la pioche
            TurnLogic m_currentTurn = new TurnLogic(m_gamebord, m_stack);

            // 2. Le joueur joue son tour
            m_currentTurn.playerTurn(m_player);

            // 3. Le bot joue son tour
            m_currentTurn.botTurn(m_bot);

            // 4. On résout les attaques et on récupère les points de dégâts
            int pointTurn = m_currentTurn.resolveAttacks();

            // 5. On met à jour le score
            updateScore(pointTurn);
        }
    }

    public void updateScore(int newScore)
    {
        this.m_score += newScore;
    }

    public Boolean isWon()
    {
        if(this.m_score >= 5 || this.m_score <= -5)
        {
            return true;
        }
        return false;
    }

    public Boolean isEnd() {
        return this.m_score >= 5 || this.m_score <= -5;
    }
}

