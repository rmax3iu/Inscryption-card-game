package gameLogic;

import actorLogic.BotLogic;
import actorLogic.PlayerLogic;
import cardLogic.CardLogic;

import java.util.ArrayList;

public class RoundLogic
{
    private Integer m_score;
    private GameBordLogic m_gamebord;
    private StackLogic m_stack;
    private PlayerLogic m_player;
    private BotLogic m_bot;

    public RoundLogic(ArrayList<CardLogic> cards)
    {
        this.m_score = 0;
        this.m_gamebord = new GameBordLogic();
        this.m_stack = new StackLogic(cards);
    }

    public void play(PlayerLogic player, BotLogic bot)
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
            int pointTurn;
            pointTurn = m_currentTurn.resolveAttacks(m_player, m_bot);

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

