package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.SummonCostLogic;
import logic.cardLogic.TerrestrialLogic;

public class Growth implements Power{
    private int nbTimeOnBord = 0;
    public static final String NAME = "Growth";

    public Growth() {}

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public AnimalLogic onTurnStart() {
        if(nbTimeOnBord == 1){
            return new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2));
        }
        nbTimeOnBord++;
        return null;
    }
}
