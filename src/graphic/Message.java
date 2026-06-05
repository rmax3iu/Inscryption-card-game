package graphic;

import logic.gameLogic.StackLogic;

import java.util.Scanner;

public class Message {
    public static String ask(String phrase){
        Scanner scanner = new Scanner(System.in);


        System.out.println(phrase + " : ");


        return scanner.nextLine();
    }

    public static String basicChoice(){
        Scanner scanner = new Scanner(System.in);

        String phrase = "Actions possibles: \n" +
                        "\t[fin] Terminer votre tour\n" +
                        "\t[piocher] Piocher une carte\n" +
                        "\t[placer <numero carte> <position>] Placer une carte sur le plateau\n\n";


        System.out.println(phrase);


        return scanner.nextLine();
    }

    public static void tell(String phrase) {
        System.out.println(phrase);
    }

    public static String demandeCard() {
        Scanner scanner = new Scanner(System.in);

        //affichage des cartes du deck
        System.out.println("=================[Carte du deck]=================");
        for(int i= 0; i< StackLogic.DECK_SIZE; i++) {
            System.out.println((i+1) + ") " + StackLogic.getCard(i).toString());
        }
        System.out.println("=================================================");

        return scanner.nextLine();
    }
}
