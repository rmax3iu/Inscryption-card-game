package graphics.gameGraphics;

// On définit la classe qui gère la grille virtuelle en texte pour l'affichage
public class ConsoleGrid
{
    // On déclare le tableau à deux dimensions de caractères représentant la grille
    private char[][] m_grid;
    // On déclare la variable pour stocker la largeur de la grille
    private int m_width;
    // On déclare la variable pour stocker la hauteur de la grille
    private int m_height;

    // On définit le constructeur de la grille avec sa largeur et sa hauteur
    public ConsoleGrid(int width, int height)
    {
        // On instancie le tableau de caractères avec les dimensions reçues
        m_grid = new char[width][height];
        // On sauvegarde la largeur dans la variable de classe
        m_width = width;
        // On sauvegarde la hauteur dans la variable de classe
        m_height = height;
        // On appelle la méthode pour remplir la grille d'espaces vides
        clear();
    }

    // On définit la méthode pour écrire une chaîne de caractères à des coordonnées précises
    public void writeString(String text, int x, int y)
    {
        // On lance une boucle pour parcourir chaque lettre du texte à écrire
        for (int i = 0; i < text.length(); i++)
        {
            // On vérifie que la position de la lettre ne dépasse pas la largeur maximale de la grille
            if (x + i < m_width)
            {
                // On place le caractère actuel dans le tableau à la bonne position horizontale
                m_grid[x + i][y] = text.charAt(i);
            }
        }
    }

    // On définit la méthode pour dessiner un rectangle avec des bordures propres
    public void writeBox(int width, int height, int x, int y)
    {
        // On place le symbole '+' sur le coin supérieur gauche
        m_grid[x][y] = '+';
        // On place le symbole '+' sur le coin supérieur droit
        m_grid[x + width - 1][y] = '+';
        // On place le symbole '+' sur le coin inférieur gauche
        m_grid[x][y + height - 1] = '+';
        // On place le symbole '+' sur le coin inférieur droit
        m_grid[x + width - 1][y + height - 1] = '+';

        // On lance une boucle pour tracer les lignes horizontales du haut et du bas
        for (int i = 1; i < width - 1; i++)
        {
            // On écrit un tiret sur le bord supérieur
            m_grid[x + i][y] = '-';
            // On écrit un tiret sur le bord inférieur
            m_grid[x + i][y + height - 1] = '-';
        }

        // On lance une boucle pour tracer les lignes verticales de gauche et de droite
        for (int j = 1; j < height - 1; j++)
        {
            // On écrit une barre verticale sur le bord gauche
            m_grid[x][y + j] = '|';
            // On écrit une barre verticale sur le bord droit
            m_grid[x + width - 1][y + j] = '|';
        }
    }

    // On définit la méthode qui convertit la grille en texte et l'affiche à l'écran
    public void render()
    {
        // On instancie un constructeur de chaîne pour assembler rapidement le texte
        StringBuilder sb = new StringBuilder();

        // On lance une boucle pour parcourir chaque ligne du haut vers le bas
        for (int j = 0; j < m_height; j++)
        {
            // On lance une boucle imbriquée pour parcourir chaque colonne de gauche à droite
            for (int i = 0; i < m_width; i++)
            {
                // On ajoute le caractère de la case actuelle dans le constructeur de texte
                sb.append(m_grid[i][j]);
            }
            // On ajoute un retour à la ligne à la fin de chaque rangée complétée
            sb.append('\n');
        }
        // On envoie la totalité du dessin final d'un seul coup dans la console
        System.out.print(sb);
    }

    // On définit la méthode pour réinitialiser la grille complète
    public void clear()
    {
        // On lance une boucle pour parcourir l'axe des X
        for (int i = 0; i < m_width; i++)
        {
            // On lance une boucle imbriquée pour parcourir l'axe des Y
            for (int j = 0; j < m_height; j++)
            {
                // On remplace le caractère de la case par un espace vide
                m_grid[i][j] = ' ';
            }
        }
    }
}