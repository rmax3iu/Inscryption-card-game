package logic.cardLogic.powers;

import logic.cardLogic.CardLogic;
import logic.gameLogic.GameBoardLogic;
import java.util.Optional;

public class Runner extends Power{
    //mit en static final car peut importe l'objet Runner il aura toujours le même nom et on le changera jamais
    public static final String NAME = "Runner";

    public Runner() {}

    //Renvoie le nom de Coureur soit Runner
    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public Runner copy(){
        return new Runner();
    }

    //Renvoie la positon à laquelle la carte devra aller après avoir attaqué
    @Override
    public int onAttack(int position, Optional<CardLogic> left, Optional<CardLogic> right) {
        if (right.isEmpty() && position < GameBoardLogic.BOARD_SIZE -1) {    //S'il y a personne à droite elle va à droite
            return position + 1;
        } else if (left.isEmpty() && position > 0) {  //Sinon si gauche est libre elle va à gauche
            return position - 1;
        }
        return position;    //Et si vraiment elle est bloquée alors elle bouge pas
    }
}
