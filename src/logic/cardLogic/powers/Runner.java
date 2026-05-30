package logic.cardLogic.powers;

import logic.cardLogic.CardLogic;

import java.security.PublicKey;

public class Runner implements Power{
    public static final String NAME = "Runner";

    public Runner() {}

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public int onAttack(int position, CardLogic left, CardLogic right) {
        if (right == null) {
            return position + 1;
        } else if (left == null) {
            return position - 1;
        }
        return position;
    }
}
