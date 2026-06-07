package graphic;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.powers.Power;
import logic.gameLogic.StackLogic;

import java.util.Optional;
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

    public static void powerTransfere(StackLogic stack, int deleteCard){
        AnimalLogic animal = stack.getCard(deleteCard);
        Optional<Power> power = animal.getPower();
        if(power.isPresent()) {
            boolean fini = false;
            while(!fini) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ta carte avait le pouvoir " + power.get().getName() + ". Choisie une carte qui aura ce pouvoir :");
                // On construit le cadre supérieur pour l'affichage des cartes du deck
                System.out.println("══════════════════ [Carte du deck] ══════════════════");

                // On fait une boucle pour lister toutes les cartes du paquet de départ
                for (int i = 0; i < StackLogic.DECK_SIZE; i++) {
                    System.out.println((i + 1) + ") " + stack.getCard(i).getName());
                }

                // On construit le cadre inférieur
                System.out.println("═══════════════════════════════════════════════════");

                System.out.println("Format : <numéro de la carte qui verra son pouvoir remplacer par " + power.get().getName() + ">");

                // On retourne le choix de remplacement saisi par l'utilisateur
                String input = scanner.nextLine();

                try {
                    int index = Integer.parseInt(input);

                    if(index >= 0 && index < StackLogic.DECK_SIZE) {
                        if (index != deleteCard) {
                            AnimalLogic animalResever = stack.getCard(index - 1);
                            animalResever.setPower(power.get().copy());
                            stack.changeCard(index, animalResever);
                            fini = true;
                        } else {
                            System.out.println("Tu essaye de mettre le pouvoir sur la carte que tu veux supprimer change de carte.");
                        }
                    }else{
                        System.out.println("Cette carte n'existe pas.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Forma incorrect il faut un chiffre.");
                }
            }
        }
    }
}