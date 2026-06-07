package graphic;

import logic.gameLogic.GameBoardLogic;
import logic.gameLogic.StackLogic;
import graphic.drawers.BoardDrawer;
import graphic.drawers.StackDrawer;
import graphic.drawers.HeaderDrawer;
import graphic.drawers.CardDrawer;

public class GameGraphic
{
    // On déclare la méthode principale pour afficher l'ensemble de l'écran de jeu
    public static void showGame(GameBoardLogic board, StackLogic draw, int round, int turn, int score)
    {
        // On affiche le bandeau de titre
        System.out.println("\n");
        HeaderDrawer.showRoundAndTurn(round, turn, score);
        System.out.println();

        // On affiche la ligne des intentions du Bot
        BoardDrawer.showLine(board, "PREVIEW");

        // On affiche les flèches d'attaque pointant vers le bas entre les deux lignes du bot
        printAttackArrows();

        // On affiche la ligne des monstres actuellement sur le terrain du Bot
        BoardDrawer.showLine(board, "BOT");

        // On appelle la méthode sur mesure pour fusionner l'affichage du joueur et de la pioche
        showPlayerLineWithDeck(board, draw.size());
        System.out.println();
    }

    // On déclare une méthode privée pour dessiner les flèches d'attaque centrées sous chaque carte
    private static void printAttackArrows()
    {
        String arrowsRow1 = "";
        String arrowsRow2 = "";

        // On fait une boucle pour générer les flèches alignées avec les 4 emplacements du plateau
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++)
        {
            arrowsRow1 = arrowsRow1 + StackDrawer.center("||", CardDrawer.WIDTH) + " ";
            arrowsRow2 = arrowsRow2 + StackDrawer.center("\\/", CardDrawer.WIDTH) + " ";
        }

        // On affiche les deux lignes de flèches dans la console
        System.out.println(arrowsRow1);
        System.out.println(arrowsRow2);
    }

    // On déclare une méthode privée pour afficher horizontalement les cartes du joueur et la pioche côte à côte
    private static void showPlayerLineWithDeck(GameBoardLogic board, int deckSize)
    {
        // On récupère la matrice de lignes de texte pour les cartes du joueur
        String[][] playerCardLines = BoardDrawer.getLineLines(board, "PLAYER");

        // On récupère le tableau de lignes de texte pour le dessin de la pioche
        String[] deckLines = StackDrawer.getDeckLines(deckSize);

        // On fait une boucle pour imprimer le tout ligne par ligne verticalement
        for (int row = 0; row < CardDrawer.HEIGHT; row++)
        {
            // On affiche d'abord la ligne de texte en cours pour les 4 cartes du joueur
            for (int col = 0; col < GameBoardLogic.BOARD_SIZE; col++)
            {
                System.out.print(playerCardLines[col][row] + " ");
            }

            // On ajoute des espaces de séparation, puis on colle la ligne de texte correspondante de la pioche
            System.out.print("      " + deckLines[row]);

            // On termine la ligne globale pour passer à la suivante dans la console
            System.out.println();
        }
    }
}