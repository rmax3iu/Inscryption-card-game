package graphics.gameGraphics;

// On définit la classe qui gère l'affichage des écrans d'accueil et de fin de partie
public class MenuGraphics
{
    // On définit le constructeur de la classe
    public MenuGraphics()
    {
        // Le constructeur reste vide car aucun composant externe n'est requis
    }

    // On définit la méthode qui dessine le menu d'accueil avec des bordures parfaitement alignées
    public void drawStartMenu(ConsoleGrid grid)
    {
        // On nettoie intégralement la grille pour effacer les vieux résidus de texte
        grid.clear();

        // On trace la ligne supérieure en étoiles (longueur exacte de 60)
        grid.writeString("************************************************************", 0, 0);

        // On écrit chaque ligne en s'assurant que le dernier '|' soit pile à la colonne 59
        grid.writeString("|                                                          |", 0, 1);
        grid.writeString("|                        INSCRIPTION                       |", 0, 2);
        grid.writeString("|                                                          |", 0, 3);
        grid.writeString("************************************************************", 0, 4);
        grid.writeString("|                                                          |", 0, 5);
        grid.writeString("|                Bienvenue dans la forêt...                |", 0, 6);
        grid.writeString("|                                                          |", 0, 7);
        grid.writeString("|           Appuyez sur [ENTRÉE] pour interagir            |", 0, 8);
        grid.writeString("|                                                          |", 0, 9);
        grid.writeString("|             [jouer]   - Lancer la partie                 |", 0, 10);
        grid.writeString("|             [quitter] - Fermer le jeu                    |", 0, 11);
        grid.writeString("|                                                          |", 0, 12);

        // On trace la ligne inférieure de fermeture du menu
        grid.writeString("************************************************************", 0, 13);

        // On affiche le menu final dans le terminal
        grid.render();
    }

    // On définit la méthode qui dessine l'écran de fin avec le même alignement strict
    public void drawEndMenu(ConsoleGrid grid, Integer score)
    {
        // On nettoie la grille pour préparer l'affichage du bilan de fin de match
        grid.clear();

        // On trace la ligne supérieure de l'écran de fin
        grid.writeString("************************************************************", 0, 0);

        grid.writeString("|                                                          |", 0, 1);

        // On affiche le titre centré selon le score avec des espaces ajustés pour faire 60 de large
        if (score >= 0)
        {
            grid.writeString("|                         VICTOIRE                         |", 0, 2);
            grid.writeString("|                                                          |", 0, 3);
            grid.writeString("************************************************************", 0, 4);
            grid.writeString("|                                                          |", 0, 5);
            grid.writeString("|           Les créatures de la forêt vous saluent.        |", 0, 6);
        }
        else
        {
            grid.writeString("|                 " +
                    "" +
                    "        GAME OVER                        |", 0, 2);
            grid.writeString("|                                                          |", 0, 3);
            grid.writeString("************************************************************", 0, 4);
            grid.writeString("|                                                          |", 0, 5);
            grid.writeString("|         Votre âme s'évanouit dans les ombres...          |", 0, 6);
        }

        grid.writeString("|                                                          |", 0, 7);

        // On formate dynamiquement la ligne du score pour qu'elle fasse exactement la bonne taille
        String scoreText = "                Score Final : " + score + " points";
        String scoreLine = "| " + scoreText;
        while (scoreLine.length() < 59)
        {
            scoreLine += " ";
        }
        scoreLine += "|";
        grid.writeString(scoreLine, 0, 8);

        grid.writeString("|                                                          |", 0, 9);
        grid.writeString("|            Tapez [quitter] pour fermer le jeu.           |", 0, 10);
        grid.writeString("|                                                          |", 0, 11);

        // On trace la ligne inférieure de fermeture
        grid.writeString("************************************************************", 0, 12);

        // On affiche l'écran de fin dans la console
        grid.render();
    }
}