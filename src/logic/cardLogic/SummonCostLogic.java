package logic.cardLogic;

public class SummonCostLogic {
    private int m_bonnes;
    private int m_blood;

    private SummonCostLogic(int blood, int bonnes){
        m_blood = blood;
        m_bonnes = bonnes;
    }

    public static SummonCostLogic newBloodCost(int blood){
        return new SummonCostLogic(blood, 0);
    }

    public static SummonCostLogic newBonnesCost(int bonnes){
        return new SummonCostLogic(0, bonnes);
    }

    public int getBonnes(){
        return m_bonnes;
    }

    public int getBlood(){
        return m_blood;
    }
}
