package graphic;

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
}
