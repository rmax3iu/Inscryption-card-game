package logic.cardLogic;

import logic.cardLogic.powers.Power;

public class FlyingLogic extends AnimalLogic {

    //Constructeur sans pouvoir
    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp, attack, cost);
    }

    //Avec pouvoir
    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }

    //Renvoie true car les cartes volante attaque toujours le score directement
    @Override
    public boolean attacksDirectly() {
        return true;
    }

    //Renvoie 0 car une carte volant n'attaque pas de carte
    @Override
    public int attack(CardLogic target) {
        return 0;
    }
}
