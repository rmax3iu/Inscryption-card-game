package logic.cardLogic;

import logic.cardLogic.powers.Power;

import java.util.Optional;

public class FlyingLogic extends AnimalLogic {

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp, attack, cost);
    }

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }

    @Override
    public int attack(Optional<CardLogic> target) {
        // Les volantes attaquent directement le score, elles ignorent la carte adverse sauf si elle a le pouvoir stiking
        int att = getAttack();
        if(target.isPresent() && target.get().hasPower()){
            Optional<Power> power = target.get().getPower();
            if (power.isPresent()){
                att -= power.get().attackModifierOnFacing();
            }
        }
        return att;
    }

}
