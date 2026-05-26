package gameLogic;

import actorLogic.PlayerLogic;
import actorLogic.BotLogic;
import actorLogic.ActorLogic;
import cardLogic.AnimalLogic;
import cardLogic.CardLogic;
import java.util.Scanner;

public class TurnLogic {
    private GameBordLogic m_gamebord;
    private StackLogic m_stack;

    public TurnLogic(GameBordLogic gamebord, StackLogic stack){
        m_gamebord = gamebord;
        m_stack = stack;
    }

    public void botTurn(BotLogic bot){

    }

    public void playerTurn(PlayerLogic player) {
        boolean hasDrawn = false;
        boolean turnOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!turnOver) {
            System.out.println("\nActions possibles :");
            if (!hasDrawn && !m_stack.isEmpty()) {
                System.out.println("  [piocher] Piocher une carte");
            }
            System.out.println("  [placer <numero carte> <position (1-4)>] Placer une carte");
            System.out.println("  [fin] Terminer votre tour");

            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "piocher" -> {
                    if (hasDrawn) {
                        System.out.println("Vous avez déjà pioché ce tour !");
                    } else {
                        drawCard(player);
                        hasDrawn = true;
                        System.out.println("Carte piochée !");
                    }
                }
                case "placer" -> {
                    if (parts.length != 3) {
                        System.out.println("Format invalide. Exemple : placer 1 3");
                        break;
                    }
                    try {
                        int indexCarte = Integer.parseInt(parts[1]) - 1;
                        int position = Integer.parseInt(parts[2]) - 1;
                        placeCard(player, indexCarte, position);
                        System.out.println("Carte placée !");
                    } catch (NumberFormatException e) {
                        System.out.println("Format invalide. Exemple : placer 1 3");
                    }
                }
                case "fin" -> turnOver = true;
                default -> System.out.println("Commande inconnue.");
            }
        }
    }

    //Place les cartes du joueur ou du bot sur leur ligne respectif
    // (ne met pas les cartes du bot sur bot line se serra fait tout seul après resolveAttacks)
    public void placeCard(ActorLogic actor, int IndexHand, int position){
        if(actor instanceof PlayerLogic){
            m_gamebord.setPlayerLine(actor.removeCard(IndexHand), position);
        }else{
            m_gamebord.setPreviewLine(actor.removeCard(IndexHand), position);
        }
    }

    public void drawCard(ActorLogic actor){
        actor.addCard(m_stack.drawCard());
    }

    public int resolveAttacks(PlayerLogic player, BotLogic bot){
        int score = 0;
        for(int i = 0; i < 4; i++){
            if(m_gamebord.getPlayerLine(i) != null && m_gamebord.getPlayerLine(i) instanceof AnimalLogic animal){
                if(m_gamebord.getBotLine(i) != null) {
                    score += animal.attack(player, m_gamebord.getBotLine(i));
                } else {
                    score += animal.attack(player,m_gamebord.getPreviewLine(i));
                }
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getBotLine(i) != null && m_gamebord.getBotLine(i).isDead()){
                m_gamebord.removeBotLine(i);
            }
            if(m_gamebord.getPreviewLine(i) != null && m_gamebord.getPreviewLine(i).isDead()){
                m_gamebord.removePreviewLine(i);
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getPreviewLine(i) != null && m_gamebord.getBotLine(i) == null){
                m_gamebord.setBotLine(m_gamebord.removePreviewLine(i), i);
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getBotLine(i) != null && m_gamebord.getBotLine(i) instanceof AnimalLogic animal){
                if(m_gamebord.getPlayerLine(i) != null) {
                    score += animal.attack(player, m_gamebord.getBotLine(i));
                } else {
                    score += animal.attack(player,m_gamebord.getPreviewLine(i));
                }
            }
        }

        for(int i = 0 ; i < 4 ; i++) {
            if(m_gamebord.getPlayerLine(i) != null && m_gamebord.getPlayerLine(i).isDead()){
                m_gamebord.removePlayerLine(i);
            }
        }
        return score;
    }

}
