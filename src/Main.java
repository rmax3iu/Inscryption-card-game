import actorLogic.PlayerLogic;
import gameLogic.GameBordLogic;
import gameLogic.StackLogic;
import graphics.gameGraphics.TurnGraphics;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.\n");

        // 1. On crée des objets de simulation vides
        GameBordLogic fauxPlateau = new GameBordLogic();
        PlayerLogic fauxJoueur = new PlayerLogic(0);
        StackLogic faussePioche = new StackLogic(new ArrayList<>());

        // 2. On instancie ton moteur d'affichage
        TurnGraphics ui = new TurnGraphics();

        // 3. On force l'affichage du plateau (Score: 0, Tour: 1, Partie: 1)
        ui.displayFullTurn(fauxPlateau, fauxJoueur, faussePioche, 0, 1, 1);
    }
}