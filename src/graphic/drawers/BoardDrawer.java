package graphic.drawers;

import logic.cardLogic.CardLogic;
import logic.gameLogic.GameBoardLogic;
import java.util.Optional;

public class BoardDrawer
{
    public static String[][] getLineLines(GameBoardLogic board, String type)
    {
        // On crée un tableau à deux dimensions pour stocker les lignes de texte des 4 cartes
        String[][] allCardsLines = new String[GameBoardLogic.BOARD_SIZE][CardDrawer.HEIGHT];

        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++)
        {
            // On crée une variable pour stocker la carte temporairement
            Optional<CardLogic> optionalCard;

            // On crée une variable pour stocker le nom de la position comme B1 ou B2
            String positionName;

            // Si on demande la ligne de prévisualisation du bot
            if (type.equals("PREVIEW"))
            {
                positionName = "P" + (i + 1);
                optionalCard = board.getPreviewLine(i);
            }

            // Si on demande la ligne de combat du bot
            else if (type.equals("BOT"))
            {
                positionName = "A" + (i + 1);
                optionalCard = board.getBotLine(i);
            }

            // Dans tous les autres cas, on récupère la ligne du joueur humain
            else
            {
                positionName = "B" + (i + 1);
                optionalCard = board.getPlayerLine(i);
            }

            // Si une carte est présente dans l'emplacement actuel
            if (optionalCard.isPresent())
            {
                allCardsLines[i] = CardDrawer.drawCard(optionalCard.get(), positionName);
            }

            // Si l'emplacement est vide
            else
            {
                allCardsLines[i] = CardDrawer.drawCard(null, positionName);
            }
        }

        return allCardsLines;
    }

    // On déclare la méthode pour afficher une ligne complète sur le plateau
    public static void showLine(GameBoardLogic board, String type)
    {
        // On récupère la matrice de lignes de texte grâce à notre nouvelle méthode
        String[][] allCardsLines = getLineLines(board, type);

        for (int row = 0; row < CardDrawer.HEIGHT; row++)
        {
            for (int col = 0; col < GameBoardLogic.BOARD_SIZE; col++)
            {
                // On affiche la ligne de texte de la carte actuelle suivie d'un espace pour séparer les cartes
                System.out.print(allCardsLines[col][row] + " ");
            }

            // On saute une ligne pour passer à la ligne de texte suivante du cadre des cadres
            System.out.println();
        }
    }
}