package logic.cardLogic.powers;


public class ManyLife implements Power{
    public static final String NAME = "ManyLife";

    public ManyLife() {}

    @Override
    public String getName(){
        return NAME;
    }

    @Override
    public boolean canDeath() {
        return false;
    }
}
