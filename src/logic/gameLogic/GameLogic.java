package logic.gameLogic;

import logic.Ask;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.*;
import logic.cardLogic.powers.*;

import java.util.ArrayList;

public class GameLogic
{
    private ActorLogic m_player;
    private ActorLogic m_bot;
    private ArrayList<CardLogic> m_baseCards;

    public GameLogic()
    {
        this.m_player = ActorLogic.newPlayerLogic();
        this.m_bot = ActorLogic.newBotLogic();
    }

    public void play()
    {
        int partiesGagnees = 0;
        for (int i = 0; i < 3; i++)
        {
            if (i == 1)
            {
                offerCard();
            }

            RoundLogic round = new RoundLogic();
            round.play(m_player, m_bot);
            if (round.isWon())
            {
                partiesGagnees++;
            }
        }
    }

    public void offerCard() {
        CardLogic[] cards = new CardLogic[] {
                new TerrestrialLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new ManyLife()),
                new TerrestrialLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3)),
                new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1)),
                new TerrestrialLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Growth()),
                new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2)),
                new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1)),
                new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2)),
                new TerrestrialLogic("Elan", 4, 2, SummonCostLogic.newBloodCost(2), new Runner()),
                new TerrestrialLogic("Vipere", 1, 1, SummonCostLogic.newBloodCost(2), new DeadlyContact()),
                new TerrestrialLogic("Porc-epic", 2, 1, SummonCostLogic.newBloodCost(1), new SharpSpikes())
        };

        CardLogic cardG = cards[(int)(Math.random() * cards.length)];
        CardLogic cardD = cards[(int)(Math.random() * cards.length)];

        System.out.println("Carte G : " + cardG.getName() + " | Carte D : " + cardD.getName());

        String demande = "";
        while (!demande.equals("G") && !demande.equals("D"))
        {
            demande = Ask.Demande("Quelle carte veux tu ? (G/D)");
            if (!demande.equals("G") && !demande.equals("D"))
            {
                System.out.println("Saisie invalide, entrez G ou D.");
            }
        }
        switch (demande)
        {
            case "G" -> m_baseCards.add(cardG);
            case "D" -> m_baseCards.add(cardD);
        }
    }
}