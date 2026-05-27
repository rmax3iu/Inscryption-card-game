package Logic.gameLogic;

import Logic.Ask;
import Logic.actorLogic.BotLogic;
import Logic.actorLogic.PlayerLogic;
import Logic.cardLogic.CardLogic;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic
{
    private PlayerLogic m_player;            // On appelle le joueur
    private BotLogic m_bot;                  // On appelle le bot
    private ArrayList<CardLogic> m_baseCards;         // ...

    public GameLogic()
    {
        this.m_player = new PlayerLogic(0);      // On instancie le joueur
        this.m_bot = new BotLogic(0);            // On instancie le bot

        this.m_baseCards = new ArrayList<CardLogic>();           // ...
    }

    public void play()
    {
        int partieG = 0;
        for(int i = 0; i < 3; i++)
        {
            if(i == 2){
                offerCard();
            }

            RoundLogic round = new RoundLogic(m_baseCards);

            if(round.isWon()){
                partieG ++;
            }
        }

        //afficher le score
    }

    public void offerCard()
    {
        //Cartes possible d'optenir
        CardLogic[] cards = new CardLogic[];
        Random rnd = new Random();

        //Carte de Gauche et Droite
        CardLogic cardG = cards[rnd.nextInt(0, cards.length)];
        CardLogic cardD = cards[rnd.nextInt(0, cards.length)];

        // Méthode pour ajouter une carte
        String demande = "";
        while (demande != "D" || demande != "G"){
            demande = Ask.Demande("Quelle carte veux tu (G/D)");
        }
        switch (demande){
            case "G":
                m_baseCards.add(cardG);
            case "D":
                m_baseCards.add(cardD);
        }
    }
}

