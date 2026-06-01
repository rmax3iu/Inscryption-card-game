package logic.cardLogic;

public class SummonCostLogic {
    private final int m_blood;
    private final int m_bones;

    private SummonCostLogic(int blood, int bones) {
        m_blood = blood;
        m_bones = bones;
    }

    public static SummonCostLogic newBloodCost(int blood) {
        return new SummonCostLogic(blood, 0);
    }

    public static SummonCostLogic newBonesCost(int bones) {
        return new SummonCostLogic(0, bones);
    }

    public static SummonCostLogic newFree() {
        return new SummonCostLogic(0, 0);
    }

    public int getBlood() {
        return m_blood;
    }

    public int getBones() {
        return m_bones;
    }

    public boolean isFree() {
        return m_blood == 0 && m_bones == 0;
    }

    public boolean isBloodCost()  {
        return m_blood > 0;
    }

    public boolean isBonesCost()  {
        return m_bones > 0;
    }

    public SummonCostLogic copie(){
        return new SummonCostLogic(m_blood,m_bones);
    }
}
