package graphic;

import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.gameLogic.StackLogic;

import java.util.Scanner;

public class Message {
    public static String ask(String phrase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(phrase + " : ");

        return scanner.nextLine();
    }

    public static String basicChoice() {
        Scanner scanner = new Scanner(System.in);

        String phrase = "Actions possibles: \n" +
                        "\t[fin] Terminer votre tour\n" +
                        "\t[piocher] Piocher une carte\n" +
                        "\t[placer <numero carte> <position>] Placer une carte sur le plateau\n";

        System.out.println(phrase);

        return scanner.nextLine();
    }

    public static void showHand(ActorLogic player) {
        System.out.println("Votre main (os : " + player.getBones() + ") :");
        for(int i = 0; i < player.handSize(); i++){
            AnimalLogic card = player.getCard(i);
            System.out.println("  " + (i+1) + ". " + card.getName()
                + "  PV: " + card.getHp()
                + "  Att: " + card.getAttack()
                + "  Sang: " + card.getSummonCost().getBlood()
                + "  Os: " + card.getSummonCost().getBones()
                + (card.hasPower() ? "  [" + card.getPower().get().getName() + "]" : ""));
        }
    }

    public static void tell(String phrase) {
        System.out.println(phrase);
    }

    public static String demandeCard(StackLogic stack) {
        Scanner scanner = new Scanner(System.in);

        //affichage des cartes du deck
        System.out.println("══════════════════[Carte du deck]══════════════════");
        for(int i = 0; i < StackLogic.DECK_SIZE; i++) {
            System.out.println((i+1) + ") " + stack.getCard(i).getName());
        }
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("Format : <Gauche/Droite> <numéro de la carte à remplacer>");

        return scanner.nextLine();
    }
}
