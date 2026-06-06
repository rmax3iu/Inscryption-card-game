package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;

public class SharpSpikes extends Power
{
    // Défini en static final car peu importe l'objet SharpSpikes, il aura toujours le même nom et ne changera jamais
    public static final String NAME = "SharpSpikes";

    public SharpSpikes()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    // Inflige 1 point de dégât en retour à l'attaquant lorsque la carte subit des dommages
    @Override
    public void onDamageReceived(AnimalLogic attacker)
    {
        attacker.takeDamage(1);
    }

    @Override
    public SharpSpikes copy()
    {
        return new SharpSpikes();
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}