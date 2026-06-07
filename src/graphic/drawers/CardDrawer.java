package graphic.drawers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;
import logic.cardLogic.powers.Power;
import java.util.Optional;

public class CardDrawer
{
    public static final int WIDTH = 16;
    public static final int HEIGHT = 8;

    public static String[] drawCard(CardLogic card, String emptyLabel)
    {
        String[] lines = new String[HEIGHT];

        // Si la carte est vide sur le plateau
        if (card == null)
        {
            // On construit le contour supérieur de la case vide
            String topBorder = "╔";
            for (int i = 0; i < WIDTH - 2; i++)
            {
                topBorder = topBorder + "═";
            }
            topBorder = topBorder + "╗";
            lines[0] = topBorder;

            lines[1] = "║" + center("", WIDTH - 2) + "║";

            // On construit la ligne de séparation du milieu
            String middleBorder = "╠";
            for (int i = 0; i < WIDTH - 2; i++)
            {
                middleBorder = middleBorder + "═";
            }
            middleBorder = middleBorder + "╣";
            lines[2] = middleBorder;

            // On écrit le nom de la position reçu en paramètre centré au milieu du cadre
            lines[3] = "║" + center(emptyLabel, WIDTH - 2) + "║";
            lines[4] = "║" + center("", WIDTH - 2) + "║";
            lines[5] = "║" + center("", WIDTH - 2) + "║";
            lines[6] = "║" + center("", WIDTH - 2) + "║";

            // On construit le contour inférieur de la case vide
            String bottomBorder = "╚";
            for (int i = 0; i < WIDTH - 2; i++)
            {
                bottomBorder = bottomBorder + "═";
            }
            bottomBorder = bottomBorder + "╝";
            lines[7] = bottomBorder;

            return lines;
        }

        // Si une vraie carte est présente, on construit le contour supérieur de la carte
        String topBorder = "╔";
        for (int i = 0; i < WIDTH - 2; i++)
        {
            topBorder = topBorder + "═";
        }
        topBorder = topBorder + "╗";
        lines[0] = topBorder;

        // On affiche le nom de la carte sur la première ligne
        lines[1] = "║" + center(card.getName(), WIDTH - 2) + "║";

        // On construit la ligne de séparation du milieu
        String middleBorder = "╠";
        for (int i = 0; i < WIDTH - 2; i++)
        {
            middleBorder = middleBorder + "═";
        }
        middleBorder = middleBorder + "╣";
        lines[2] = middleBorder;

        String attackStr = "Att : 0";
        String hpStr = "PV : " + card.getHp();
        String flyStr = "";

        // Si la carte est un animal
        if (card.canBeSacrify())
        {
            // On convertit la carte pour accéder aux données de l'animal
            AnimalLogic animal = (AnimalLogic) card;
            attackStr = "Att : " + animal.getAttack();
            hpStr = "PV : " + animal.getHp();
            if (animal.isFlying())
            {
                flyStr = "Volant";
            }
        }

        // On affiche les points de vie sur la troisième ligne
        lines[3] = "║" + center(hpStr, WIDTH - 2) + "║";

        // On affiche l'attaque sur la quatrième ligne
        lines[4] = "║" + center(attackStr, WIDTH - 2) + "║";

        String powerStr = "";

        // Si la carte possède un pouvoir particulier
        if (card.hasPower())
        {
            // On récupère le pouvoir de façon sécurisée à l'aide d'un conteneur Optional
            Optional<Power> optionalPower = card.getPower();

            // Si le pouvoir est bien présent à l'intérieur du conteneur
            if (optionalPower.isPresent())
            {
                // On extrait le pouvoir et on place son nom entre crochets
                Power power = optionalPower.get();
                powerStr = power.getName();
            }
        }

        // On affiche le statut volant sur la cinquième ligne
        lines[5] = "║" + center(flyStr, WIDTH - 2) + "║";

        // On affiche le pouvoir sur la sixième ligne
        lines[6] = "║" + center(powerStr, WIDTH - 2) + "║";

        // On construit le contour inférieur de la carte
        String bottomBorder = "╚";
        for (int i = 0; i < WIDTH - 2; i++)
        {
            bottomBorder = bottomBorder + "═";
        }
        bottomBorder = bottomBorder + "╝";
        lines[7] = bottomBorder;

        return lines;
    }

    private static String center(String text, int width)
    {
        // Si le texte fourni est inexistant
        if (text == null)
        {
            text = "";
        }

        // Si la longueur du texte dépasse la largeur maximale autorisée
        if (text.length() > width)
        {
            // On coupe le texte pour qu'il s'adapte aux dimensions de la carte
            text = text.substring(0, width);
        }

        // On calcule l'espace total restant à combler de chaque côté du texte
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        // On génère les espaces nécessaires pour le côté gauche
        String spacesLeft = "";
        for (int i = 0; i < left; i++)
        {
            spacesLeft = spacesLeft + " ";
        }

        // On génère les espaces nécessaires pour le côté droit
        String spacesRight = "";
        for (int i = 0; i < right; i++)
        {
            spacesRight = spacesRight + " ";
        }

        // On rassemble les espaces de gauche, le texte et les espaces de droite
        String result = spacesLeft + text + spacesRight;

        return result;
    }
}