import graphic.Message;
import logic.gameLogic.GameLogic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class victoireDefaite {

    // Vérification sur l'état du round
    @Test
    public void roundWon(){
        GameLogic game = new GameLogic();
        assertTrue(game.isRoundOver(5));
    }

    @Test
    public void roundLose(){
        GameLogic game = new GameLogic();
        assertTrue(game.isRoundOver(-5));
    }

    @Test
    public void roundPasFini(){
        GameLogic game = new GameLogic();
        assertFalse(game.isRoundOver(0));
    }

    // Vérification victoire ou défaite
    @Test
    public void testGameOverVictoire() {
        // On met 2 en paramètre
        String resultat = Message.gameOver(2);

        // On vérifie que le texte renvoyé contient bien le mot VICTOIRE
        assertTrue(resultat.contains("VICTOIRE"));
    }

    @Test
    public void testGameOverDefaite() {
        // On met 1 en paramètre
        String resultat = Message.gameOver(1);

        // On vérifie que le texte renvoyé contient bien le mot DÉFAITE
        assertTrue(resultat.contains("DÉFAITE"));
    }
}
