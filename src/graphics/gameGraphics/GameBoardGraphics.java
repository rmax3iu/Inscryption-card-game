package graphics.gameGraphics;

import cardLogic.CardLogic;
import gameLogic.GameBordLogic;
import graphics.cardGraphics.CardGraphics;

// On définit la classe qui gère le dessin et l'organisation du plateau de jeu
public class GameBoardGraphics
{
    // On déclare le composant qui sait dessiner une carte individuelle
    private CardGraphics m_cardGraphics;

    // On définit le constructeur de la classe
    public GameBoardGraphics()
    {
        // On instancie l'outil de dessin des cartes
        m_cardGraphics = new CardGraphics();
    }

    // On définit la méthode qui place tous les éléments du plateau sur la grille
    public void drawBoard(ConsoleGrid grid, GameBordLogic board, int score, int currentTurn, int currentMatch)
    {
        // On écrit le numéro de la partie en haut à gauche
        grid.writeString("Partie " + currentMatch, 4, 1);
        // On écrit le numéro du tour juste en dessous
        grid.writeString(currentTurn + "er Tour:", 4, 3);

        // On définit les positions sur l'axe X pour aligner nos 4 colonnes de cartes
        int[] xPositions = {9, 25, 41, 57};

        // On lance une boucle pour dessiner la ligne des prévisions du bot tout en haut
        for (int i = 0; i < 4; i++)
        {
            // On récupère la carte de prévision correspondante
            CardLogic card = board.getPreviewLine(i);
            // On dessine l'emplacement sans lui donner de nom textuel
            drawSlotOrCard(grid, card, xPositions[i], 5, null);
        }

        // On lance une boucle pour afficher les flèches d'attaque au milieu
        for (int i = 0; i < 4; i++)
        {
            // On écrit les barres verticales de la flèche
            grid.writeString("||", xPositions[i] + 5, 13);
            // On écrit la pointe de la flèche juste en dessous
            grid.writeString("\\/", xPositions[i] + 5, 14);
        }

        // On lance une boucle pour dessiner les cartes actuellement posées par le bot
        for (int i = 0; i < 4; i++)
        {
            // On récupère la carte présente sur la ligne du bot
            CardLogic card = board.getBotLine(i);
            // On dessine la carte ou l'emplacement vide nommé de A1 à A4
            drawSlotOrCard(grid, card, xPositions[i], 16, "A" + (i + 1));
        }

        // On écrit le texte du score sur le côté gauche du plateau
        grid.writeString("Score", 1, 24);
        // On écrit la valeur numérique du score juste en dessous du texte
        grid.writeString("  " + score, 1, 25);

        // On lance une boucle pour dessiner les cartes posées sur le terrain du joueur
        for (int i = 0; i < 4; i++)
        {
            // On récupère la carte présente sur la ligne du joueur
            CardLogic card = board.getPlayerLine(i);
            // On dessine la carte ou l'emplacement vide nommé de B1 à B4
            drawSlotOrCard(grid, card, xPositions[i], 25, "B" + (i + 1));
        }
    }

    // On définit la méthode utilitaire qui choisit de dessiner une vraie carte ou une boîte vide étoilée
    private void drawSlotOrCard(ConsoleGrid grid, CardLogic card, int x, int y, String slotName)
    {
        // On vérifie si une vraie carte est présente dans cet emplacement
        if (card != null)
        {
            // On demande au moteur de dessin des cartes d'afficher les détails de l'animal
            m_cardGraphics.drawCard(grid, card, x, y);
        }
        // On gère le cas où l'emplacement est vide
        else
        {
            // On définit la largeur de notre boîte en étoiles
            int width = 13;
            // On définit la hauteur de notre boîte en étoiles
            int height = 7;

            // On lance une boucle pour dessiner les lignes horizontales du haut et du bas
            for (int i = 0; i < width; i++)
            {
                // On écrit une étoile sur la ligne supérieure
                grid.writeString("*", x + i, y);
                // On écrit une étoile sur la ligne inférieure
                grid.writeString("*", x + i, y + height - 1);
            }

            // On lance une boucle pour dessiner les lignes verticales de gauche et de droite
            for (int j = 1; j < height - 1; j++)
            {
                // On écrit une étoile sur le bord gauche
                grid.writeString("*", x, y + j);
                // On écrit une étoile sur le bord droit
                grid.writeString("*", x + width - 1, y + j);
            }

            // On vérifie si cet emplacement possède un nom de case à afficher
            if (slotName != null)
            {
                // On écrit le nom de la case pile au milieu de la boîte étoilée
                grid.writeString(slotName, x + 5, y + 3);
            }
        }
    }
}