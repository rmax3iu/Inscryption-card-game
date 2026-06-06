package logic.cardLogic.powers;

import logic.cardLogic.CardFactory;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public class Growth extends Power{
    //permet de savoir combien de tour la carte est sur le plateau
    private int nbTimeOnBord = 0;
    //mit en static final car peut importe l'objet Growth il aura toujours le même nom et on le changera jamais
    public static final String NAME = "Growth";

    public Growth() {}

    //Renvoie le nom de Croissance soit Growth
    @Override
    public String getName(){
        return NAME;
    }

    //Renvoie un loup si la carte est sur le plateau depuis un tour sinon rien
    @Override
    public Optional<CardLogic> onTurnStart() {
        nbTimeOnBord++;
        //Donc au 2e tour la carte se transforme en loup
        if(nbTimeOnBord == 2){
            return Optional.of(CardFactory.createLoup());
        }
        return Optional.empty();    //Vu que de base elle ne change pas on renvoie rien
    }

    @Override
    public Growth copy(){
        return new Growth();
    }
}
