package card;

public class SummonCost {
    private int m_bonnes;
    private int m_blood;

    private SummonCost(int blood, int bonnes){
        m_blood = blood;
        m_bonnes = bonnes;
    }

    public static SummonCost newBloodCost(int blood){
        return new SummonCost(blood, 0);
    }

    public static SummonCost newBonnesCost(int bonnes){
        return new SummonCost(0, bonnes);
    }

    public int getBonnes(){
        return m_bonnes;
    }

    public int getBlood(){
        return m_blood;
    }
}
