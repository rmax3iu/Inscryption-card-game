package logic.cardLogic.powers;

public class Stinking implements Power{
    //mit en static final car peut importe l'objet Stinking il aura toujours le même nom et on le changera jamais
    public static final String NAME = "Stinking";

    public Stinking() {}

    //Renvoie le nom de puant soit Stinking
    @Override
    public String getName(){
        return NAME;
    }

    //Renvoie le -1 pour quand la carte en face de lui veut attaquer (la carte ou son actor) son attaque soit réduit de 1
    @Override
    public int attackModifierOnFacing(){
        return -1;
    }
}
