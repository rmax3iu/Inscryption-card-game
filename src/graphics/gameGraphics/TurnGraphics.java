package graphics.gameGraphics;

import actorLogic.PlayerLogic;
import cardLogic.AnimalLogic;
import cardLogic.CardLogic;
import gameLogic.GameBordLogic;
import gameLogic.StackLogic;

// On définit la classe qui gère l'affichage complet d'un tour de jeu
public class TurnGraphics
{
    // On déclare le composant responsable du dessin du plateau central
    private GameBoardGraphics m_boardGraphics;

    // On définit le constructeur de la classe
    public TurnGraphics()
    {
        // On instancie le moteur d'affichage du plateau de jeu
        m_boardGraphics = new GameBoardGraphics();
    }

    // On définit la méthode principale qui assemble et affiche tous les éléments visuels du tour
    public void displayFullTurn(GameBordLogic board, PlayerLogic player, StackLogic stack, int score, int turn, int match)
    {
        // On instancie une nouvelle grille virtuelle de 95 colonnes et 34 lignes
        ConsoleGrid grid = new ConsoleGrid(95, 34);

        // On appelle le dessin du plateau central avec les cartes du bot, du joueur et les prévisions
        m_boardGraphics.drawBoard(grid, board, score, turn, match);

        // On définit la coordonnée X pour positionner la pioche à droite du plateau
        int piocheX = 76;
        // On définit la coordonnée Y pour aligner la pioche avec le terrain du joueur
        int piocheY = 25;

        // On écrit le texte de titre au-dessus de la boîte de la pioche
        grid.writeString("Pioche", piocheX + 4, piocheY - 1);
        // On dessine le contour de la boîte de la pioche avec une largeur de 13 et une hauteur de 7
        grid.writeBox(13, 7, piocheX, piocheY);
        // On écrit le nombre de cartes restantes au milieu de la pioche
        grid.writeString(stack.length() + "", piocheX + 6, piocheY + 2);
        // On écrit le mot sous le nombre pour finaliser le visuel de la pioche
        grid.writeString("cartes", piocheX + 3, piocheY + 4);

        // On affiche la grille complète dans le terminal de l'utilisateur
        grid.render();

        // On écrit le texte d'en-tête pour la section de la main du joueur
        System.out.println("Votre main :");
        // On initialise l'index de départ pour parcourir les cartes en main
        int i = 0;
        // On définit un indicateur booléen pour savoir quand arrêter la lecture de la main
        boolean endOfHand = false;

        // On lance une boucle de lecture sécurisée pour parcourir toute la main du joueur
        while (!endOfHand)
        {
            // On ouvre un bloc de sécurité pour intercepter les erreurs de fin de liste
            try
            {
                // On récupère la carte correspondant à l'index actuel
                CardLogic card = player.getCard(i);
                // On vérifie si la carte existe bel et bien
                if (card != null)
                {
                    if (card instanceof AnimalLogic animal) {
                        System.out.println("  " + (i + 1) + ". " + animal.getName() + "  PV: " + animal.getHp() + "  Pouvoir: " + animal.getPower());
                    } else {
                        System.out.println("  " + (i + 1) + ". " + card.getName() + "  PV: " + card.getHp());
                    }
                    // On incrémente l'index pour passer à la carte suivante au prochain tour de boucle
                    i++;
                }
                // On gère le cas où la carte récupérée est nulle
                else
                {
                    // On bascule l'indicateur à vrai pour terminer la boucle
                    endOfHand = true;
                }
            }
            // On intercepte l'erreur provoquée si l'index dépasse la taille réelle de la main
            catch (Exception e)
            {
                // On bascule l'indicateur à vrai pour arrêter proprement la boucle
                endOfHand = true;
            }
        }
        // On vérifie si aucune carte n'a été trouvée dans la main
        if (i == 0)
        {
            // On écrit que la main est vide si le compteur est resté à zéro
            System.out.println("  (vide)");
        }

        // On écrit un espace et le titre de la section des commandes utilisables
        System.out.println("\nActions possibles:");
        // On écrit l'instruction pour permettre de clore le tour actuel
        System.out.println("  [fin] Terminer votre tour");
        // On écrit l'instruction pour permettre de tirer une carte de la pioche
        System.out.println("  [piocher] Piocher une carte");
        // On écrit l'instruction pour détailler la commande de pose d'une carte sur le plateau
        System.out.println("  [placer <numero carte> <position>] Placer une carte sur le plateau");
        // On écrit le symbole d'invite de commande pour attendre la saisie de l'utilisateur
        System.out.print("\n$ ");
    }
}