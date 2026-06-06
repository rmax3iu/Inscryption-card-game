package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class ManyLife extends Power
{
    // Défini en static final car peu importe l'objet ManyLife, il aura toujours le même nom et ne changera jamais
    public static final String NAME = "ManyLife";

    public ManyLife()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    // Indique que la carte ne meurt pas lorsqu'elle est sacrifiée
    @Override
    public boolean canDeath()
    {
        return false;
    }

    @Override
    public ManyLife copy()
    {
        return new ManyLife();
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}