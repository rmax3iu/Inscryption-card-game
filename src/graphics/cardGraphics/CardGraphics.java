package graphics.cardGraphics;

import cardLogic.AnimalLogic;
import cardLogic.CardLogic;
import cardLogic.ObstacleLogic;
import graphics.gameGraphics.ConsoleGrid;

// On définit la classe qui s'occupe de dessiner le visuel textuel d'une carte
public class CardGraphics
{

    // On définit la méthode qui dessine une carte en fonction de son type (Animal ou Obstacle)
    public void drawCard(ConsoleGrid grid, CardLogic card, int x, int y)
    {
        // On dessine le cadre extérieur de la carte avec une largeur de 13 et une hauteur de 9
        grid.writeBox(13, 9, x, y);

        // On vérifie si la carte à dessiner est un Animal
        if (card instanceof AnimalLogic animal)
        {
            // On écrit le nom de l'animal sur la première ligne intérieure
            grid.writeString(" " + animal.getName(), x + 1, y + 1);
            // On écrit une ligne de séparation sous le nom de l'animal
            grid.writeString("-----------", x + 1, y + 2);
            // On écrit les points de vie de l'animal sur la quatrième ligne
            grid.writeString(" PV: " + animal.getHp(), x + 1, y + 4);
            // On écrit les points d'attaque de l'animal sur la cinquième ligne
            grid.writeString(" Att: " + animal.getAttack(), x + 1, y + 5);
            // On écrit le coût d'invocation de la carte sur la sixième ligne
            grid.writeString(" Cout: " + animal.getCost(), x + 1, y + 6);
            // On écrit le pouvoir de la carte  sur la septième ligne
            grid.writeString(" Pouvoir: " + animal.getPower(), x + 1, y + 7);
        }
        // On vérifie si la carte à dessiner est un Obstacle (comme un Rocher ou un Sapin)
        else if (card instanceof ObstacleLogic obstacle)
        {
            // On écrit le nom de l'obstacle sur la première ligne intérieure
            grid.writeString(" " + obstacle.getName(), x + 1, y + 1);
            // On écrit une ligne de séparation sous le nom de l'obstacle
            grid.writeString("-----------", x + 1, y + 2);
            // On écrit les points de vie de l'obstacle sur la quatrième ligne
            grid.writeString(" PV: " + obstacle.getHp(), x + 1, y + 4);
            // On écrit le texte indicatif Obstacle sur la sixième ligne
            grid.writeString(" Obstacle", x + 1, y + 6);
        }
        // On gère le cas par défaut si la carte n'est ni un animal ni un obstacle
        else
        {
            // On écrit le mot vide au centre de la carte pour combler l'espace
            grid.writeString("   vide", x + 1, y + 4);
        }
    }

    // On définit une méthode pour dessiner explicitement une carte vide si besoin
    public void drawEmpty(ConsoleGrid grid, int x, int y)
    {
        // On dessine le cadre extérieur de la boîte vide
        grid.writeBox(13, 9, x, y);
        // On écrit le mot vide au milieu de cette boîte
        grid.writeString("   vide", x + 1, y + 4);
    }
}