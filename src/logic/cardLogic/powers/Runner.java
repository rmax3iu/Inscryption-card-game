package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.security.PublicKey;

public class Runner extends Power{
    //mit en static final car peut importe l'objet Runner il aura toujours le même nom et on le changera jamais
    public static final String NAME = "Runner";

    public Runner() {}

    //Renvoie le nom de Coureur soit Runner
    @Override
    public String getName(){
        return NAME;
    }

    //Renvoie la positon à laquelle la carte devra aller après avoir attaqué
    @Override
    public int onAttack(int position, CardLogic left, CardLogic right) {
        if (right == null) {    //S'il y personne à droite elle va à droite
            return position + 1;
        } else if (left == null) {  //Sinon à gauche
            return position - 1;
        }
        return position;    //Et si vraiment elle est bloqué alors elle bouge pas
    }
}
