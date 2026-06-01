package logic.cardLogic;

import logic.cardLogic.powers.Power;

public abstract class AnimalLogic extends CardLogic {
    private final int m_attack;
    private final SummonCostLogic m_summonCostLogic;
    private Power m_power;

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = null;
    }

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = power;
    }


    public int getAttack() {
        return m_attack;
    }

    public SummonCostLogic getSummonCost()  {
        return m_summonCostLogic;
    }

    public Power getPower(){
        return m_power;
    }

    public boolean hasPower() {
        return m_power != null;
    }

    public String getPowerName(){
        return m_power.getName();
    }

    /** Indique si la carte attaque directement le score (ex. carte volante). */
    public boolean attacksDirectly() {
        return false;
    }

    public void setPower(Power power) {
        m_power  = power;
    }

    /**
     * Inflige des dégâts à la cible.
     * Utilisée par AttackResolver qui gère lui-même le score.
     */
    public int attack(CardLogic target) {
        if (target != null) {
            return target.takeDamage(getAttack());
        }
        return 0;
    }

    @Override
    public AnimalLogic copie(){
        return null;
    }
}
