package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class DeadlyContact extends Power{
    //mit en static final car peut importe l'objet DeadlyContact il aura toujours le même nom et on le changera jamais
    public static final String NAME = "DeadlyContact";

    public DeadlyContact() {}

    //Renvoie le nom de Contacte mortel soit DeadlyContact
    @Override
    public String getName(){
        return NAME;
    }

    //Renvoie si elle la cible peut être one shot (en gros si c'est un animal oui si c'est un obstacle non)
    @Override
    public boolean killsOnHit() {
        return true;
    }
}
