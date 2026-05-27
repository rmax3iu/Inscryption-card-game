import actorLogic.PlayerLogic;
import cardLogic.SummonCostLogic;
import cardLogic.TerrestrialLogic;
import gameLogic.GameBordLogic;
import gameLogic.StackLogic;
import graphics.cardGraphics.CardGraphics;
import graphics.gameGraphics.ConsoleGrid;
import graphics.gameGraphics.MenuGraphics;
import graphics.gameGraphics.TurnGraphics;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MenuGraphics menu = new MenuGraphics();
        TurnGraphics turnGraphics = new TurnGraphics();

        GameBordLogic board = new GameBordLogic();
        PlayerLogic player = new PlayerLogic(20);
        StackLogic stack = new StackLogic(new ArrayList<>());

        ConsoleGrid menuGrid = new ConsoleGrid(60, 14);

        menu.drawStartMenu(menuGrid);
        System.out.println("\n------------------------------------------------------------\n");

        // Test affichage d'une carte
        ConsoleGrid cardGrid = new ConsoleGrid(20, 12);
        CardGraphics cardGraphics = new CardGraphics();
        TerrestrialLogic hermine = new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1));
        hermine.setPower("aucun");
        cardGraphics.drawCard(cardGrid, hermine, 1, 1);
        cardGrid.render();
        System.out.println("\n------------------------------------------------------------\n");

        turnGraphics.displayFullTurn(board, player, stack, 0, 1, 1);
        System.out.println("\n------------------------------------------------------------\n");

        menu.drawEndMenu(menuGrid, 5);
        System.out.println("\n------------------------------------------------------------\n");

        menu.drawEndMenu(menuGrid, -3);
        System.out.println("\n------------------------------------------------------------\n");
    }
}