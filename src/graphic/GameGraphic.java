package graphic;

import logic.cardLogic.CardLogic;
import logic.gameLogic.GameBoardLogic;
import logic.gameLogic.StackLogic;

import java.util.Optional;

public class GameGraphic {
    public static void showGame(GameBoardLogic board, StackLogic draw, int round, int turn, int score){
        System.out.println("\n\n");
        showRoundAndTurn(round,turn,score);
        System.out.println("\n\n");
        showPreviewLine(board);
        System.out.println("\n");
        showBotLine(board);
        System.out.println("\n");
        showPlayerLine(board);
        System.out.println("\n");
        showDraw(draw);
        System.out.println("\n");
    }

    private static void showRoundAndTurn(int round, int turn, int score){
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║        Partie : " + round + "        Tour : " + turn + "        Score : " + score + "        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }

    private static void showPreviewLine(GameBoardLogic board){
        System.out.println("\uD83D\uDFCA━━━━━━━━[P1]━━━━━━━━━[P2]━━━━━━━━━[P3]━━━━━━━━━[P4]━━━━━━━━\uD83D\uDFCA");
        System.out.println("|");
        for(int i = 0; i < GameBoardLogic.BOARD_SIZE; i++){
            Optional<CardLogic> card = board.getPreviewLine(i);
            if(card.isPresent()) {
                System.out.print("("+card.get().getName()+") ");
            }else{
                System.out.print("(Pas de carte)");
            }
        }
        System.out.print("|");
    }

    private static void showBotLine(GameBoardLogic board){
        System.out.println("\uD83D\uDFCA━━━━━━━━[A1]━━━━━━━━━[A2]━━━━━━━━━[A3]━━━━━━━━━[A4]━━━━━━━━\uD83D\uDFCA");
        System.out.println("|");
        for(int i = 0; i < GameBoardLogic.BOARD_SIZE; i++){
            Optional<CardLogic> card = board.getBotLine(i);
            if(card.isPresent()) {
                System.out.print("("+card.get().getName()+") ");
            }else{
                System.out.print("(Pas de carte)");
            }
        }
        System.out.print("|");
    }

    private static void showPlayerLine(GameBoardLogic board){
        System.out.println("\uD83D\uDFCA━━━━━━━━[B1]━━━━━━━━━[B2]━━━━━━━━━[B3]━━━━━━━━━[B4]━━━━━━━━\uD83D\uDFCA");
        System.out.println("|");
        for(int i = 0; i < GameBoardLogic.BOARD_SIZE; i++){
            Optional<CardLogic> card = board.getPlayerLine(i);
            if(card.isPresent()) {
                System.out.print("("+card.get().getName()+") ");
            }else{
                System.out.print("(Pas de carte)");
            }
        }
        System.out.print("|");
    }

    private static void showDraw(StackLogic draw) {
        System.out.println("(Pioche : "+draw.size()+" cartes restantes)");
    }
}
