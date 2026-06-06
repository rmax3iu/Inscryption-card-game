package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public abstract class Power
{
    // Méthode abstraite à redéfinir dans les classes filles pour renvoyer leur nom
    public abstract String getName();

    public abstract Power copy();

    // Appelé quand la carte attaque une autre carte (utile pour coureur)
    public int onAttack(int position, Optional<CardLogic> left, Optional<CardLogic> right)
    {
        return position;
    }

    // Appelé au début du tour si la carte est présente sur le plateau (utile pour croissance)
    public Optional<CardLogic> onTurnStart()
    {
        return Optional.empty();
    }

    // Appelé lorsque la carte reçoit des dégâts (utile pour piques pointues)
    public void onDamageReceived(AnimalLogic attacker)
    {
    }

    // Appelé quand une carte attaque en face d'elle, même s'il s'agit d'une unité volante (utile pour puant)
    public int attackModifierOnFacing()
    {
        return 0;
    }

    // Appelé lors de la tentative de sacrifice de la carte (utile pour nombreuses vies)
    public boolean canDeath()
    {
        return true;
    }

    // Appelé lors d'une attaque pour déterminer si elle élimine instantanément la carte adverse
    public boolean killsOnHit()
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "Pouvoir : " + getName();
    }
}