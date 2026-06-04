package logic.cardLogic;

import logic.cardLogic.powers.ManyLife;
import logic.cardLogic.powers.Power;

import java.util.Optional;

public class AnimalLogic extends CardLogic {
    private final int m_attack;
    private final SummonCostLogic m_summonCostLogic;
    private Optional<Power> m_power;

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = Optional.empty();
    }

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = Optional.of(power);
    }

    public AnimalLogic(AnimalLogic animal) {
        super(animal.getName(), animal.getHp());
        m_attack = animal.getAttack();
        m_summonCostLogic = animal.getSummonCost();     //C'est pas grave si c'est pas une copie profonde vu que le nombre d'os ou de sang ne change jamais
        Optional<Power> power = animal.getPower();
        if(power.isPresent()){
            m_power = Optional.of(power.get().copy());
        }else{
            m_power = Optional.empty();
        }
    }

    public int getAttack() {
        return m_attack;
    }

    public SummonCostLogic getSummonCost() {
        return m_summonCostLogic;
    }

    @Override
    public AnimalLogic copy() {
        return new AnimalLogic(this);
    }

    @Override
    public Optional<Power> getPower() {
        return m_power;
    }

    @Override
    public Optional<CardLogic> sacrify() {
        if (m_power.isPresent() && !m_power.get().canDeath()) {
            return Optional.of(new AnimalLogic(getName(), getHp(), m_attack, m_summonCostLogic, new ManyLife()));
        }
        return Optional.empty();
    }

    @Override
    public boolean hasPower() {
        return m_power.isPresent();
    }

    public void setPower(Power power) {
        m_power = Optional.of(power);
    }

    // S'il y a une carte on l'attaque et sinon on
    @Override
    public int attack(Optional<CardLogic> target) {
        if (target.isPresent()) {
            Optional<Power> targetPower = target.get().getPower();
            if(m_power.isPresent() && m_power.get().killsOnHit()){
                target.get().kill();
            }else{
                int degat = getAttack();
                if(targetPower.isPresent()) {
                    degat -= targetPower.get().attackModifierOnFacing();
                }
                target.get().takeDamage(degat);
            }
            if(targetPower.isPresent()) {
                targetPower.get().onDamageReceived(this);
            }
            return 0;
        }
        return getAttack();
    }

    @Override
    public void kill() {
        super.setHp(0);
    }

}