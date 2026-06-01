package logic.cardLogic;

import logic.cardLogic.powers.Power;

public class FlyingLogic extends AnimalLogic {

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp, attack, cost);
    }

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }

    /** Les cartes volantes attaquent toujours le score directement. */
    @Override
    public boolean attacksDirectly() {
        return true;
    }

    @Override
    public int attack(CardLogic target) {
        // Rien à faire sur la cible : l'AttackResolver gère le score via attacksDirectly()
        return 0;
    }

    @Override
    public FlyingLogic copie(){
        return new FlyingLogic(super.getName(),super.getHp(),super.getAttack(),super.getSummonCost().copie(),super.getPower().copie());
    }
}
