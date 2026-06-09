package logic.cardLogic;

import logic.cardLogic.powers.Power;

import java.util.Optional;

public class FlyingLogic extends AnimalLogic
{
    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost)
    {
        super(name, hp, attack, cost);
    }

    public FlyingLogic(String name, int hp, int attack, SummonCostLogic cost, Power power)
    {
        super(name, hp, attack, cost, power);
    }

    // Les cartes volantes attaquent directement le score et elles ignorent la carte adverse sauf si elle a le pouvoir stiking
    @Override
    public int attack(Optional<CardLogic> target)
    {
        int att = getAttack();
        if (target.isPresent() && target.get().hasPower())
        {
            Optional<Power> power = target.get().getPower();
            if (power.isPresent())
            {
                att -= power.get().attackModifierOnFacing();
            }
        }
        return att;
    }

    @Override
    public boolean isFlying()
    {
        return true;
    }

    @Override
    public String toString()
    {
        String nomPouvoir;
        if (getPower().isPresent())
        {
            nomPouvoir = getPower().get().getName();
        }
        else
        {
            nomPouvoir = "Aucun";
        }
        return "Animal volant { Nom :" + getName() + ", Attaque : " + getAttack() + ", PV : " + getHp() + ", Pouvoir : " + nomPouvoir + ", Coût : " + getSummonCost() + " }";
    }
}
