package logic.cardLogic.powers;

import logic.cardLogic.CardLogic;
import logic.gameLogic.GameBoardLogic;
import java.util.Optional;

public class Runner extends Power
{
    // Défini en static final car peu importe l'objet Runner, il aura toujours le même nom et ne changera jamais
    public static final String NAME = "Runner";

    public Runner()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public Runner copy()
    {
        return new Runner();
    }

    // Détermine et renvoie la position vers laquelle la carte se déplace après avoir attaqué
    @Override
    public int onAttack(int position, Optional<CardLogic> left, Optional<CardLogic> right)
    {
        // Si l'emplacement de droite est libre, la carte s'y déplace
        if (right.isEmpty() && position < GameBoardLogic.BOARD_SIZE - 1)
        {
            return position + 1;
        }
        // Sinon, si l'emplacement de gauche est libre, elle s'y déplace
        else if (left.isEmpty() && position > 0)
        {
            return position - 1;
        }
        // Si les deux côtés sont bloqués, la carte reste à sa place
        return position;
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}