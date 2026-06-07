package graphic;

import logic.gameLogic.StackLogic;
import java.util.Scanner;

public class Message
{
    // On déclare la méthode pour poser une question générique dans la console et récupérer la réponse
    public static String ask(String phrase)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(phrase + " : ");
        return scanner.nextLine();
    }

    // On déclare la méthode pour afficher les actions possibles et récupérer le choix de l'utilisateur
    public static String basicChoice()
    {
        Scanner scanner = new Scanner(System.in);

        // On prépare la chaîne contenant la liste des commandes disponibles
        String phrase = "Actions possibles: \n" +
                "\t[fin] Terminer votre tour\n" +
                "\t[piocher] Piocher une carte\n" +
                "\t[placer <numero carte> <position>] Placer une carte sur le plateau\n";

        System.out.println(phrase);
        System.out.print("$ ");
        return scanner.nextLine();
    }

    // On déclare la méthode pour afficher un message simple d'information
    public static void tell(String phrase)
    {
        System.out.println(phrase);
    }

    // On déclare la méthode pour gérer le remplacement d'une carte dans le deck
    public static String demandeCard(StackLogic stack)
    {
        Scanner scanner = new Scanner(System.in);

        // On construit le cadre supérieur pour l'affichage des cartes du deck
        System.out.println("══════════════════ [Carte du deck] ══════════════════");

        // On fait une boucle pour lister toutes les cartes du paquet de départ
        for (int i = 0; i < StackLogic.DECK_SIZE; i++)
        {
            System.out.println((i + 1) + ") " + stack.getCard(i).getName());
        }

        // On construit le cadre inférieur
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("Format : <Gauche/Droite> <numéro de la carte à remplacer>");

        // On retourne le choix de remplacement saisi par l'utilisateur
        return scanner.nextLine();
    }
}