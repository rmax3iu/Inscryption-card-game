package logic.cardLogic.powers;

import logic.cardLogic.CardFactory;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public class Growth extends Power
{
    // Permet de suivre le nombre de tours passés par la carte sur le plateau
    private int nbTimeOnBord = 0;

    // Défini en static final car peu importe l'objet Growth, il aura toujours le même nom et ne changera jamais
    public static final String NAME = "Growth";

    public Growth()
    {
    }

    // Renvoie le nom du pouvoir
    @Override
    public String getName()
    {
        return NAME;
    }

    // Transforme la carte en Loup si elle est sur le plateau depuis deux tours, sinon ne fait rien
    @Override
    public Optional<CardLogic> onTurnStart()
    {
        nbTimeOnBord++;
        // Transformation de la carte en Loup au bout du deuxième tour
        if(nbTimeOnBord == 2)
        {
            return Optional.of(CardFactory.createLoup());
        }
        return Optional.empty();    // Renvoie un élément vide si la carte ne change pas à ce tour
    }

    @Override
    public Growth copy()
    {
        return new Growth();
    }

    @Override
    public String toString()
    {
        return "Nom du pouvoir : " + NAME;
    }
}