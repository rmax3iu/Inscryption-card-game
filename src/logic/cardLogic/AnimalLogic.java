package logic.cardLogic;

import logic.actorLogic.ActorLogic;
import logic.actorLogic.PlayerLogic;

public abstract class AnimalLogic extends CardLogic {
    private int m_attack;
    private SummonCostLogic m_summonCostLogic;

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic summonCostLogic){
        super(name,hp);
        m_attack = attack;
        m_summonCostLogic = summonCostLogic;
    }

    public int getAttack(){
        return m_attack;
    }

    //actor est la personne qui attaque et card la carte qui ressoit l'attaque
    public int attack(ActorLogic actorLogic, CardLogic cardLogic){
        if(cardLogic == null){
            if(actorLogic instanceof PlayerLogic){
                return getAttack();
            }else{
                return -getAttack();
            }
        }
        cardLogic.takeDamage(getAttack());
        return 0;
    }

    public int getCost(){
        return m_summonCostLogic.getBlood() + m_summonCostLogic.getBonnes();
    }

    public boolean isBlood(){
        return m_summonCostLogic.getBlood() > 0;
    }

    public boolean isBonnes(){
        return m_summonCostLogic.getBonnes() > 0;
    }
}
