package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class DeadlyContact implements Power{
    public static final String NAME = "DeadlyContact";

    public DeadlyContact() {}

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public boolean killsOnHit(CardLogic card) {
        return card instanceof AnimalLogic;
    }
}
