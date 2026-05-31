package logic.cardLogic;

import logic.cardLogic.powers.Power;

public abstract class AnimalLogic extends CardLogic {
    private final int m_attack;                             //C'est le nombre de points de dégât que fait la carte
    private final SummonCostLogic m_summonCostLogic;        //C'est le coup demandé par la carte pour être posé
    private Power m_power;                                  //C'est le pouvoir que la carte possède (peut être null si elle n'a pas de pouvoir)

    //Constructeur sans pouvoir
    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = null;
    }

    //Constructeur avec pouvoir
    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = power;
    }

    //Renvoie l'attaque
    public int getAttack() {
        return m_attack;
    }

    //Renvoie le coup d'apparition
    public SummonCostLogic getSummonCost()  {
        return m_summonCostLogic;
    }

    //Renvoie le pouvoir
    public Power getPower() {
        return m_power;
    }

    //Renvoie un boolean qui dit si la carte a un pouvoir
    public boolean hasPower() {
        return m_power != null;
    }

    //Renvoie le nom de son pouvoir si elle en a un sinon une chaine vide
    public String getPowerName(){
        if(hasPower()){
            return m_power.getName();
        }else{
            return "";
        }
    }

    //Renvoie un boolean qui dit si la carte attaque directement le score (ex. carte volante)
    public boolean attacksDirectly() {
        return false;
    }

    //Modif le pouvoir de la carte (on l'utilise à la fin du 2e tour quand on sacrifie une carte avec pouvoir et qu'on la donne à une autre carte)
    public void setPower(Power power) {
        m_power  = power;
    }

    //Attaque la carte cible et si le nombre de pv est inférieur à son attaque on renvoie le surplus (on l'utilisera pour attaque la carte juste derrière)
    public int attack(CardLogic target) {
        if (target != null) {
            return target.takeDamage(getAttack());
        }
        return 0;
    }
}
