package gameLogic;

import actorLogic.BotLogic;
import actorLogic.PlayerLogic;
import cardLogic.CardLogic;

public class GameLogic
{
    private RoundLogic[] m_rounds;           // On initialise un tableau de RoundLogic pour stocker les 3 manches du jeu
    private PlayerLogic m_player;            // On appelle le joueur
    private BotLogic m_bot;                  // On appelle le bot
    private CardLogic[] m_baseCards;         // ...

    public GameLogic()
    {
        this.m_player = new PlayerLogic(0);      // On instancie le joueur
        this.m_bot = new BotLogic(0);            // On instancie le bot

        this.m_rounds = new RoundLogic[3];              // On prépare un tableau pour acceuillir les 3 manches

        this.m_baseCards = new CardLogic[12];           // ...
    }

    public void play()
    {
        for(int i = 0; i < m_rounds.length; i++)
        {
            System.out.println("Tour n°" + (i + 1));

            // m_rounds[i] = new RoundLogic(m_baseCards);
            // m_rounds[i].play(m_player, m_bot);
            // m_rounds[i].isWon();
        }
    }

    public void offerCard()
    {
        // Méthode pour ajouter une carte
    }
}
