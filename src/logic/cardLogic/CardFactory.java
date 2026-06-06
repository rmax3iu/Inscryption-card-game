package logic.cardLogic;

import logic.cardLogic.powers.*;

// Sert à instancier les différents animaux et obstacles du jeu
public class CardFactory
{
    public static final int NB_CARD = 13;

    // Les cartes animaux terrestres

    public static AnimalLogic createChat()
    {
        return new AnimalLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new ManyLife());
    }

    public static AnimalLogic createGrizzly()
    {
        return new AnimalLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3));
    }

    public static AnimalLogic createCoyote()
    {
        return new AnimalLogic("Coyote", 1, 2, SummonCostLogic.newBonesCost(4));
    }

    public static AnimalLogic createEcureuil()
    {
        return new AnimalLogic("Ecureuil", 1, 0, SummonCostLogic.newFree());
    }

    public static AnimalLogic createHermine()
    {
        return new AnimalLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1));
    }

    public static AnimalLogic createLouveteau()
    {
        return new AnimalLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Growth());
    }

    public static AnimalLogic createLoup()
    {
        return new AnimalLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2));
    }

    public static AnimalLogic createPunaise()
    {
        return new AnimalLogic("Punaise", 2, 1, SummonCostLogic.newBonesCost(2), new Stinking());
    }

    public static AnimalLogic createElan()
    {
        return new AnimalLogic("Elan", 4, 2, SummonCostLogic.newBloodCost(2), new Runner());
    }

    public static AnimalLogic createVipere()
    {
        return new AnimalLogic("Vipère", 1, 1, SummonCostLogic.newBloodCost(2), new DeadlyContact());
    }

    public static AnimalLogic createPorcEpic()
    {
        return new AnimalLogic("Porc-épic", 2, 1, SummonCostLogic.newBloodCost(1), new SharpSpikes());
    }

    // Les cartes animaux volants

    public static FlyingLogic createMoineau()
    {
        return new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1));
    }

    public static FlyingLogic createCorbeau()
    {
        return new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2));
    }

    // Les cartes obstacles

    public static CardLogic createRocher()
    {
        return new CardLogic("Rocher", 5);
    }

    public static CardLogic createSapin()
    {
        return new CardLogic("Sapin", 3);
    }
}