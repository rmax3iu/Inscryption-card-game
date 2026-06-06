package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class Stinking extends Power
{
    // Défini en static final car peu importe l'objet Stinking, il aura toujours le même nom et ne changera jamais
    public static final String NAME = "Stinking";

    public Stinking()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    // Modifie la puissance de l'attaque adverse située en face en réduisant ou altérant sa valeur de 1
    @Override
    public int attackModifierOnFacing()
    {
        return 1;
    }

    @Override
    public Stinking copy()
    {
        return new Stinking();
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}