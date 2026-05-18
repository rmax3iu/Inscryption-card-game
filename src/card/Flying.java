package card;

import actor.Actor;

public class Flying extends Animal {

    public Flying(String name, int hp, int attack, SummonCost summonCost){
        super(name, hp, attack, summonCost);
    }

    @Override
    public int attack(Actor actor, Card card){
        if(actor.getType().equals("Player")){
            return getAttack();
        }else{
            return -getAttack();
        }
    }
}
