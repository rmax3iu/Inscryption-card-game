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

    public static int sacificePlayerCard(int count, int nbSacrifie){
        String reponse = Message.ask("Donne la position de la carte que vous voulez sacrifier (il reste " + (count - nbSacrifie) + " cartes à sacrifier)");
        int index;
        // On traduit la demande en un indice pour connaître la carte
        switch (reponse)
        {
            case "B1":
                index = 0;
                break;
            case "B2":
                index = 1;
                break;
            case "B3":
                index = 2;
                break;
            case "B4":
                index = 3;
                break;
            default:
                index = -1;     // Quand le joueur écrit quelque chose d'inattendu
                break;
        }

        if(index == -1){
            Message.tell("Saisie incorrecte ! (type de saisie valide : B1/B2/B3/B4)");
        }

        return index;
    }

    public static boolean poserCard(int longueur){
        boolean posable = longueur == 3;
        if(!posable){
            Message.tell("Format invalide. Exemple : placer 1 B3");
        }
        return posable;
    }

    public static boolean piocherCard(boolean hasDraw){
        if(hasDraw){
            Message.tell("Tu as déjà pioché, fais autre chose.");
        }
        return !hasDraw;
    }

    public static boolean cardExiste(int numeroCarte, int handSize){
        boolean existe = numeroCarte >= 0 && numeroCarte < handSize;
        if(!existe){
            Message.tell("Numéro de carte inexistant. Vérifiez votre main.");
        }
        return existe;
    }

    public static boolean positionExiste(int index){
        boolean existe = index != -1;
        if(!existe){
            Message.tell("Position incorrecte (ex : B1, B2, B3, B4).");
        }
        return existe;
    }

    public static boolean cardNonVide(boolean existe){
        if(existe){
            Message.tell("Cette position contient déjà une carte.");
        }
        return existe;
    }

    public static boolean valideCost(boolean valide, String name, int position){
        if(!valide){
            Message.tell("Tu n'as pas les ressources nécessaires pour cette carte.");
        }else{
            Message.tell("Tu as joué la carte " + name + " sur la position " + position + ".");
        }
        return valide;
    }

    public static int getNumCard(String num){
        try{
            return Integer.parseInt(num) - 1;
        }
        catch (NumberFormatException e)
        {
            Message.tell("Le premier paramètre doit être un chiffre ! (Exemple : placer 1 B2)");
            return -1;
        }
    }

    public static void unkownCommand(){
        Message.tell("Commande inconnue. Tapez [fin], [piocher] ou [placer <n> <pos>].");

    }

    public static boolean indexInDeck(int index){
        boolean indexInDeck = index > 0 && index <= StackLogic.DECK_SIZE;
        if(!indexInDeck){
            Message.tell("Le numéro de carte n'existe pas.");
        }
        return indexInDeck;
    }

    public static int GaucheOuDroite(String choix){
        if(choix.equals("Gauche")){
            return  0;
        } else if (choix.equals("Droite")) {
            return  1;
        }
        Message.tell("Nom de nouvelle carte incorrect (ex : Gauche/Droite)");
        return -1;
    }

    public static int cardChoice(String index){
        try {
            return Integer.parseInt(index);
        }
        catch (NumberFormatException e)
        {
            Message.tell("Format invalide (ex : Gauche 1)");
        }
        return -1;
    }

    public static boolean goodLength(int length){
        boolean goodLength = length == 2;
        if(!goodLength){
            Message.tell("Format invalide (ex : Gauche 1)");
        }
        return goodLength;
    }

    public static void demandeCard(String left, String right, int size){
        Message.tell("Choisissez une carte à ajouter : Gauche (" + left+ ") ou Droite (" + right + ")");
        Message.tell("Format : <Gauche/Droite> <numéro de la carte à remplacer dans votre pioche (1-" + size + ")>");
    }
}