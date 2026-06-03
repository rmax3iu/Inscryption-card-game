package logic.cardLogic.powers;


import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class ManyLife extends Power{
    //mit en static final car peut importe l'objet ManyLife il aura toujours le même nom et on le changera jamais
    public static final String NAME = "ManyLife";

    public ManyLife() {}

    //Renvoie le nom de Nombreuses vies soit ManyLife
    @Override
    public String getName(){
        return NAME;
    }

    //Renvoie false car elle peut pas mourir quand on la sacrifie
    @Override
    public boolean canDeath() {
        return false;
    }
}
