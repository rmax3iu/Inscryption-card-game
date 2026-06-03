package logic.cardLogic;

import logic.cardLogic.powers.Power;

public class FlyingLogic extends AnimalLogic {

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp, attack, cost);
    }

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }

    @Override
    public int attack(CardLogic target) {
        // Rien à faire sur la cible on renvoie juste l'attaque de la carte pour que attackResolver l'applique sur le score
        return getAttack();
    }

}
