package graphic.drawers;

public class StackDrawer
{
    public static String[] getDeckLines(int cardCount)
    {
        // On crée un tableau de chaînes pour fabriquer les 9 lignes du paquet
        String[] lines = new String[CardDrawer.HEIGHT];

        // On construit le contour supérieur du paquet
        String topBorder = "╔";
        for (int i = 0; i < CardDrawer.WIDTH - 2; i++)
        {
            topBorder = topBorder + "═";
        }
        topBorder = topBorder + "╗";
        lines[0] = topBorder;

        // On affiche le mot Pioche
        lines[1] = "║" + center("Pioche", CardDrawer.WIDTH - 2) + "║";

        // On construit la ligne de séparation du milieu pour faire comme les cartes
        String middleBorder = "╠";
        for (int i = 0; i < CardDrawer.WIDTH - 2; i++)
        {
            middleBorder = middleBorder + "═";
        }
        middleBorder = middleBorder + "╣";
        lines[2] = middleBorder;

        lines[3] = "║" + center("", CardDrawer.WIDTH - 2) + "║";

        // On écrit le nombre de cartes actuel
        lines[4] = "║" + center(String.valueOf(cardCount), CardDrawer.WIDTH - 2) + "║";

        // On écrit le mot cartes
        lines[5] = "║" + center("cartes", CardDrawer.WIDTH - 2) + "║";

        lines[6] = "║" + center("", CardDrawer.WIDTH - 2) + "║";

        // On construit le contour inférieur
        String bottomBorder = "╚";
        for (int i = 0; i < CardDrawer.WIDTH - 2; i++)
        {
            bottomBorder = bottomBorder + "═";
        }
        bottomBorder = bottomBorder + "╝";
        lines[7] = bottomBorder;

        return lines;
    }

    // On déclare la méthode pour afficher la pioche
    public static void showDeck(int cardCount)
    {
        String[] lines = getDeckLines(cardCount);

        // On fait une boucle pour afficher le dessin complet de la pioche ligne par ligne
        for (int i = 0; i < CardDrawer.HEIGHT; i++)
        {
            System.out.println(lines[i]);
        }
    }

    // On déclare la méthode pour centrer le texte
    public static String center(String text, int width)
    {
        if (text == null)
        {
            text = "";
        }

        if (text.length() > width)
        {
            text = text.substring(0, width);
        }

        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;

        String spacesLeft = "";
        for (int i = 0; i < left; i++)
        {
            spacesLeft = spacesLeft + " ";
        }

        String spacesRight = "";
        for (int i = 0; i < right; i++)
        {
            spacesRight = spacesRight + " ";
        }

        return spacesLeft + text + spacesRight;
    }
}