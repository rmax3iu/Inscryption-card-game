package logic.cardLogic.powers;

public class Stinking implements Power{
    public static final String NAME = "Stinking";

    public Stinking() {}

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public int attackModifierOnFacing(){
        return -1;
    }
}
