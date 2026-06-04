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
        // Rien à faire sur la cible on renvoie juste l'attaque de la carte pour que attackResolver l'applique sur le score
        int degat = getAttack();
        if(target.isPresent()) {
            Optional<Power> power = target.get().getPower();
            if(power.isPresent()) {
                degat -= power.get().attackModifierOnFacing();
            }
        }
        return degat;
    }

}
