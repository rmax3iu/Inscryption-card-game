package card;

import actor.Actor;
import actor.Player;

public abstract class Animal extends Card {
    private int m_attack;
    private SummonCost m_summonCost;

    public Animal(String name, int hp, int attack, SummonCost summonCost){
        super(name,hp);
        m_attack = attack;
        m_summonCost = summonCost;
    }

    public int getAttack(){
        return m_attack;
    }

    //actor est la personne qui attaque et card la carte qui ressoit l'attaque
    public int attack(Actor actor, Card card){
        if(card == null){
            if(actor instanceof Player){
                return getAttack();
            }else{
                return -getAttack();
            }
        }
        card.takeDamage(getAttack());
        return 0;
    }

    public int getCost(){
        return m_summonCost.getBlood() + m_summonCost.getBonnes();
    }

    public boolean isBlood(){
        return m_summonCost.getBlood() > 0;
    }

    public boolean isBonnes(){
        return m_summonCost.getBonnes() > 0;
    }
}
