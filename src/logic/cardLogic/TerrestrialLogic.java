package logic.cardLogic;

import logic.cardLogic.powers.Power;

public class TerrestrialLogic extends AnimalLogic {

    public TerrestrialLogic(String name, int hp, int attack, SummonCostLogic summonCostLogic){
        super(name, hp, attack, summonCostLogic);
    }

    public TerrestrialLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp, attack, cost, power);
    }

    @Override
    public TerrestrialLogic copie(){
        return new TerrestrialLogic(super.getName(),super.getHp(),super.getAttack(),super.getSummonCost().copie(),super.getPower().copie());
    }
}
