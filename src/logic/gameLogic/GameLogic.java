package logic.gameLogic;

import logic.Ask;
import logic.cardLogic.CardLogic;
import logic.cardLogic.FlyingLogic;
import logic.cardLogic.SummonCostLogic;
import logic.cardLogic.TerrestrialLogic;
import logic.cardLogic.powers.Croissance;
import logic.cardLogic.powers.NombreusesVies;
import logic.cardLogic.powers.Puant;
import logic.cardLogic.powers.Coureur;
import logic.cardLogic.powers.ContactMortel;
import logic.cardLogic.powers.PiquesPointues;

import java.util.ArrayList;

public class GameLogic
{
    private PlayerLogic m_player;
    private BotLogic m_bot;
    private ArrayList<CardLogic> m_baseCards;

    public GameLogic()
    {
        this.m_player = new PlayerLogic(0);
        this.m_bot = new BotLogic(0);
        this.m_baseCards = new ArrayList<>();

        // Ecureuils (majoritaires)
        for (int i = 0; i < 8; i++)
        {
            m_baseCards.add(new TerrestrialLogic("Ecureuil", 1, 0, SummonCostLogic.newBloodCost(0)));
        }

        // Cartes avec pouvoirs
        m_baseCards.add(new TerrestrialLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new NombreusesVies()));
        m_baseCards.add(new TerrestrialLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Croissance()));
        m_baseCards.add(new TerrestrialLogic("Punaise", 2, 1, SummonCostLogic.newBonnesCost(2), new Puant()));

        // Cartes sans pouvoir
        m_baseCards.add(new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1)));
        m_baseCards.add(new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2)));
        m_baseCards.add(new TerrestrialLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3)));
        m_baseCards.add(new TerrestrialLogic("Coyote", 1, 2, SummonCostLogic.newBonnesCost(4)));
        m_baseCards.add(new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1)));
        m_baseCards.add(new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2)));
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

            RoundLogic round = new RoundLogic(m_baseCards);
            round.play(m_player, m_bot);
            if (round.isWon())
            {
                partiesGagnees++;
            }
        }
    }

    public void offerCard() {
        CardLogic[] cards = new CardLogic[] {
                new TerrestrialLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new NombreusesVies()),
                new TerrestrialLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3)),
                new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1)),
                new TerrestrialLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Croissance()),
                new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2)),
                new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1)),
                new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2)),
                new TerrestrialLogic("Elan", 4, 2, SummonCostLogic.newBloodCost(2), new Coureur()),
                new TerrestrialLogic("Vipere", 1, 1, SummonCostLogic.newBloodCost(2), new ContactMortel()),
                new TerrestrialLogic("Porc-epic", 2, 1, SummonCostLogic.newBloodCost(1), new PiquesPointues())
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