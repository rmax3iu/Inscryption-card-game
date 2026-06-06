package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public class DeadlyContact extends Power
{
    // On le définit en static final car peu importe l'objet DeadlyContact, il aura toujours le même nom et on ne le changera jamais
    public static final String NAME = "DeadlyContact";

    public DeadlyContact()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    // On indique si la cible peut être éliminée en un coup
    @Override
    public boolean killsOnHit()
    {
        return true;
    }

    @Override
    public DeadlyContact copy()
    {
        return new DeadlyContact();
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}