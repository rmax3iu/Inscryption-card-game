package logic.cardLogic;

import logic.cardLogic.powers.Power;

public class TerrestrialLogic extends AnimalLogic {

    //Constructeur sans pouvoir
    public TerrestrialLogic(String name, int hp, int attack, SummonCostLogic summonCostLogic){
        super(name, hp, attack, summonCostLogic);
    }

    //Avec pouvoir
    public TerrestrialLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }
}
