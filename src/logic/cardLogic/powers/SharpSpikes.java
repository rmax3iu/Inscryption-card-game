package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class SharpSpikes extends Power{
    //mit en static final car peut importe l'objet SharpSpikes il aura toujours le même nom et on le changera jamais
    public static final String NAME = "SharpSpikes";

    public SharpSpikes() {}

    //Renvoie le nom de Pique pointue soit SharpSpikes
    @Override
    public String getName() {
        return NAME;
    }

    //Quand la carte se fait attaquer, l'attaqueur perd 1 hp
    @Override
    public void onDamageReceived(AnimalLogic attacker) {
        attacker.takeDamage(1);
    }
}
