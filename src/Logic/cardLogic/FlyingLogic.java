package Logic.cardLogic;

import Logic.actorLogic.ActorLogic;
import Logic.actorLogic.PlayerLogic;

public class FlyingLogic extends AnimalLogic {

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic summonCostLogic){
        super(name, hp, attack, summonCostLogic);
    }

    @Override
    public int attack(ActorLogic actorLogic, CardLogic cardLogic){
        if(actorLogic instanceof PlayerLogic){
            return getAttack();
        }else{
            return -getAttack();
        }
    }
}
