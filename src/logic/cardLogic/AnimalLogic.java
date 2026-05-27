package logic.cardLogic;

import logic.actorLogic.ActorLogic;
import logic.actorLogic.PlayerLogic;
import logic.cardLogic.powers.Power;

public abstract class AnimalLogic extends CardLogic {
    private int m_attack;
    private SummonCostLogic m_summonCostLogic;
    private Power m_power;

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic summonCostLogic){
        super(name,hp);
        m_attack = attack;
        m_summonCostLogic = summonCostLogic;
        m_power = null;
    }

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = power;
    }

    public int getAttack(){
        return m_attack;
    }

    //actor est la personne qui attaque et card la carte qui ressoit l'attaque
    public int attack(ActorLogic actorLogic, CardLogic cardLogic){
        if(cardLogic == null){
            if(actorLogic instanceof PlayerLogic){
                return getAttack();
            }else{
                return -getAttack();
            }
        }
        cardLogic.takeDamage(getAttack());
        return 0;
    }

    public int getCost(){
        return m_summonCostLogic.getBlood() + m_summonCostLogic.getBonnes();
    }

    public boolean isBlood(){
        return m_summonCostLogic.getBlood() > 0;
    }

    public boolean isBonnes(){
        return m_summonCostLogic.getBonnes() > 0;
    }

    public Power getPower()
    {
        return m_power;
    }

    public void setPower(Power power)
    {
        m_power = power;
    }

    public boolean hasPower()
    {
        return m_power != null;
    }

    public String getPowerName()
    {
        if (m_power != null)
        {
            return m_power.getName();
        }
        else
        {
            return "Aucun";
        }
    }
}
