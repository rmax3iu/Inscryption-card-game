import logic.cardLogic.*;
import logic.gameLogic.GameBoardLogic;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GameBoardLogicTest
{
    // État initial

    @Test
    public void testPlateau4EmplacementsVides()
    {
        GameBoardLogic board = new GameBoardLogic();
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++)
        {
            assertTrue(board.getPlayerLine(i).isEmpty());
            assertTrue(board.getBotLine(i).isEmpty());
            assertTrue(board.getPreviewLine(i).isEmpty());
        }
    }

    @Test
    public void testComptageInitialZero()
    {
        GameBoardLogic board = new GameBoardLogic();
        assertEquals(0, board.countPlayerCards());
        assertEquals(0, board.countBotCard());
    }

    // Placement cartes joueur

    @Test
    public void testPlacerCarteJoueur()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic hermine = CardFactory.createHermine();
        board.setPlayerLine(0, Optional.of(hermine));
        assertTrue(board.getPlayerLine(0).isPresent());
        assertEquals("Hermine", board.getPlayerLine(0).get().getName());
    }

    @Test
    public void testPlacerCarteJoueurEnPosition3()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic grizzly = CardFactory.createGrizzly();
        board.setPlayerLine(3, Optional.of(grizzly));
        assertTrue(board.getPlayerLine(3).isPresent());
        assertFalse(board.getPlayerLine(0).isPresent());
    }

    @Test
    public void testPlacerCarteBot()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic loup = CardFactory.createLoup();
        board.setBotLine(1, Optional.of(loup));
        assertTrue(board.getBotLine(1).isPresent());
        assertEquals("Loup", board.getBotLine(1).get().getName());
    }

    @Test
    public void testPlacerCartePreview()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic louveteau = CardFactory.createLouveteau();
        board.setPreviewLine(2, Optional.of(louveteau));
        assertTrue(board.getPreviewLine(2).isPresent());
    }

    // Retirer des cartes

    @Test
    public void testRetirerCarteJoueur()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic hermine = CardFactory.createHermine();
        board.setPlayerLine(0, Optional.of(hermine));
        Optional<CardLogic> removed = board.removePlayerLine(0);
        assertTrue(removed.isPresent());
        assertTrue(board.getPlayerLine(0).isEmpty());   // L'emplacement est maintenant vide
    }

    @Test
    public void testRetirerCarteBotLine()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic loup = CardFactory.createLoup();
        board.setBotLine(0, Optional.of(loup));
        board.removeBotLine(0);
        assertTrue(board.getBotLine(0).isEmpty());
    }

    @Test
    public void testRetirerCartePreview()
    {
        GameBoardLogic board = new GameBoardLogic();
        AnimalLogic hermine = CardFactory.createHermine();
        board.setPreviewLine(1, Optional.of(hermine));
        board.removePreviewLine(1);
        assertTrue(board.getPreviewLine(1).isEmpty());
    }

    // Comptages

    @Test
    public void testCountPlayerCardsAvecAnimaux()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setPlayerLine(0, Optional.of(CardFactory.createHermine()));
        board.setPlayerLine(2, Optional.of(CardFactory.createGrizzly()));
        assertEquals(2, board.countPlayerCards());
    }

    @Test
    public void testCountPlayerCardsIgnoreObstacles()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setPlayerLine(0, Optional.of(CardFactory.createHermine()));
        board.setPlayerLine(1, Optional.of(CardFactory.createRocher())); // obstacle
        assertEquals(1, board.countPlayerCards());    // L'obstacle ne compte pas
    }

    @Test
    public void testCountBotCardsInclutBotEtPreview()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setBotLine(0, Optional.of(CardFactory.createLoup()));
        board.setPreviewLine(1, Optional.of(CardFactory.createCoyote()));
        assertEquals(2, board.countBotCard());
    }

    @Test
    public void testCountBotCardsIgnoreObstacles()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setBotLine(0, Optional.of(CardFactory.createLoup()));
        board.setBotLine(1, Optional.of(CardFactory.createSapin())); // obstacle
        assertEquals(1, board.countBotCard());
    }

    // getIndex

    @Test
    public void testGetIndexB1()
    {
        GameBoardLogic board = new GameBoardLogic();
        assertEquals(0, board.getIndex("B1"));
    }

    @Test
    public void testGetIndexB4()
    {
        GameBoardLogic board = new GameBoardLogic();
        assertEquals(3, board.getIndex("B4"));
    }

    @Test
    public void testGetIndexPositionInvalide()
    {
        GameBoardLogic board = new GameBoardLogic();
        assertEquals(-1, board.getIndex("X9"));
    }

    @Test
    public void testGetIndexB2EtB3()
    {
        GameBoardLogic board = new GameBoardLogic();
        assertEquals(1, board.getIndex("B2"));
        assertEquals(2, board.getIndex("B3"));
    }

    // Remplacement de carte

    @Test
    public void testRemplacerCarteJoueur()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setPlayerLine(0, Optional.of(CardFactory.createHermine()));
        board.setPlayerLine(0, Optional.of(CardFactory.createGrizzly()));
        assertEquals("Grizzly", board.getPlayerLine(0).get().getName());
    }

    @Test
    public void testViderEmplacementAvecOptionalEmpty()
    {
        GameBoardLogic board = new GameBoardLogic();
        board.setPlayerLine(0, Optional.of(CardFactory.createHermine()));
        board.setPlayerLine(0, Optional.empty());
        assertTrue(board.getPlayerLine(0).isEmpty());
    }
}
