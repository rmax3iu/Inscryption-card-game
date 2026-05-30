package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;

public class SharpSpikes implements Power{
    public static final String NAME = "SharpSpikes";

    public SharpSpikes() {}

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void onDamageReceived(AnimalLogic attacker) {
        attacker.takeDamage(1);
    }
}
