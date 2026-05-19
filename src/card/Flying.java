package card;

import actor.Actor;
import actor.Player;

public class Flying extends Animal {

    public Flying(String name, int hp, int attack, SummonCost summonCost){
        super(name, hp, attack, summonCost);
    }

    @Override
    public int attack(Actor actor, Card card){
        if(actor instanceof Player){
            return getAttack();
        }else{
            return -getAttack();
        }
    }
}
