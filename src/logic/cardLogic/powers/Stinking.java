package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class Stinking extends Power{
    //mit en static final car peut importe l'objet Stinking il aura toujours le même nom et on le changera jamais
    public static final String NAME = "Stinking";

    public Stinking() {}

    //Renvoie le nom de puant soit Stinking
    @Override
    public String getName(){
        return NAME;
    }

    //Appelé quand une carte attaque en face d'elle (même si c'est une volante) (pour puant)
    @Override
    public int attackModifierOnFacing() {
        return 1;
    }
}
