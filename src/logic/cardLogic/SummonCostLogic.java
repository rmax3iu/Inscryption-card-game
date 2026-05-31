package logic.cardLogic;

public class SummonCostLogic {
    private final int m_blood;      //Nombre de gouttes de sang nécessaire pour apparaitre
    private final int m_bones;      //Nombre d'os nécessaire our apparaitre

    //On met le constructeur de base en privé car une carte qui demande des gouttes de sang et des os ça n'existe pas
    private SummonCostLogic(int blood, int bones) {
        m_blood = blood;
        m_bones = bones;
    }

    //Crée un SummmonCostLogic avec juste du sang
    public static SummonCostLogic newBloodCost(int blood) {
        return new SummonCostLogic(blood, 0);
    }

    //Crée un SummmonCostLogic avec juste des os
    public static SummonCostLogic newBonesCost(int bones) {
        return new SummonCostLogic(0, bones);
    }

    //Crée un SummmonCostLogic gratuit
    public static SummonCostLogic newFree() {
        return new SummonCostLogic(0, 0);
    }

    //Renvoie le nombre de gouttes de sang
    public int getBlood() {
        return m_blood;
    }

    //Renvoie le nombre d'os
    public int getBones() {
        return m_bones;
    }

    //Renvoie un boolean qui dit si c'est gratuit
    public boolean isFree() {
        return m_blood == 0 && m_bones == 0;
    }

    //Renvoie un boolean qui dit si la carte demande du sang
    public boolean isBloodCost()  {
        return m_blood > 0;
    }

    //Renvoie un boolean qui dit si la carte demande des os
    public boolean isBonesCost()  {
        return m_bones > 0;
    }
}
