import logic.cardLogic.*;
import logic.gameLogic.AttacksResolver;
import logic.gameLogic.GameBoardLogic;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class AttacksResolverTest
{
    // Score : attaque joueur sans adversaire (score positif)

    @Test
    public void testJoueurAttaqueEmptyScorePositif()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPlayerLine(0, Optional.of(CardFactory.createGrizzly()));   // ATT 4
        int score = resolver.resolveAll(board);
        assertTrue(score > 0);
    }

    @Test
    public void testJoueurAttaqueScoreEgalAttaque()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPlayerLine(0, Optional.of(CardFactory.createCoyote()));    // ATT 2
        int score = resolver.resolveAll(board);
        assertEquals(2, score);
    }

    // Score : attaque bot sans adversaire (score négatif)

    @Test
    public void testBotAttaqueScoreNegatif()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPreviewLine(0, Optional.of(CardFactory.createLoup()));     // ATT 3
        int score = resolver.resolveAll(board);
        assertTrue(score < 0);
    }

    @Test
    public void testBotAttaqueScoreEgalMoinsAttaque()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPreviewLine(0, Optional.of(CardFactory.createLoup()));     // ATT 3
        int score = resolver.resolveAll(board);
        assertEquals(-3, score);
    }

    // Les deux s'annulent

    @Test
    public void testDeuxCartesAttaquentScoreNul()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        AnimalLogic hermine = CardFactory.createHermine();   // ATT 1
        AnimalLogic louveteau = CardFactory.createLouveteau(); // ATT 1
        board.setPlayerLine(0, Optional.of(hermine));
        board.setPreviewLine(0, Optional.of(louveteau));
        int score = resolver.resolveAll(board);
        assertEquals(0, score);
    }

    // Déplacement preview → bot

    @Test
    public void testCartePreviewPasseEnBotLine()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPreviewLine(0, Optional.of(CardFactory.createLoup()));
        resolver.resolveAll(board);
        assertTrue(board.getBotLine(0).isPresent());
        assertTrue(board.getPreviewLine(0).isEmpty());
    }

    @Test
    public void testCartePreviewNePasBougerSiBotLineOccupee()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setBotLine(0, Optional.of(CardFactory.createLoup()));
        board.setPreviewLine(0, Optional.of(CardFactory.createHermine()));
        resolver.resolveAll(board);
        // La preview reste si la bot line est occupée
        assertTrue(board.getPreviewLine(0).isPresent());
    }

    // Nettoyage des morts

    @Test
    public void testCarteBotMorteSupprimee()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        AnimalLogic ecureuil = CardFactory.createEcureuil(); // PV 1 ATT 0
        AnimalLogic grizzly = CardFactory.createGrizzly();   // ATT 4 → tue l'écureuil
        board.setPlayerLine(0, Optional.of(grizzly));
        board.setPreviewLine(0, Optional.of(ecureuil));
        resolver.resolveAll(board);
        assertTrue(board.getBotLine(0).isEmpty());  // L'écureuil est mort et retiré
    }

    @Test
    public void testCarteJoueurMorteSupprimee()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        AnimalLogic ecureuil = CardFactory.createEcureuil(); // PV 1 ATT 0
        AnimalLogic grizzly = CardFactory.createGrizzly();   // ATT 4
        board.setPlayerLine(0, Optional.of(ecureuil));
        board.setPreviewLine(0, Optional.of(grizzly));
        resolver.resolveAll(board);
        assertTrue(board.getPlayerLine(0).isEmpty());   // L'écureuil est mort et retiré
    }

    // Plusieurs cartes sur le plateau

    @Test
    public void testPlusieursCartesJoueurScoreTotal()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        board.setPlayerLine(0, Optional.of(CardFactory.createCoyote()));     // ATT 2
        board.setPlayerLine(1, Optional.of(CardFactory.createHermine()));    // ATT 1
        int score = resolver.resolveAll(board);
        assertEquals(3, score);
    }

    // Carte volante ignore les cartes bot

    @Test
    public void testVolantAttaqueDirectScoreMemeAvecBotEnFace()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        FlyingLogic moineau = CardFactory.createMoineau();     // ATT 1, volant
        AnimalLogic grizzly = CardFactory.createGrizzly();     // PV 6
        board.setPlayerLine(0, Optional.of(moineau));
        board.setPreviewLine(0, Optional.of(grizzly));
        int score = resolver.resolveAll(board);
        // Le moineau vole au-dessus : ajoute +1 au score
        // Le grizzly attaque le moineau : perd PV
        assertTrue(score >= 1);
        assertEquals(6, board.getBotLine(0).get().getHp());    // Grizzly non touché par le moineau
    }

    // SharpSpikes en combat

    @Test
    public void testSharpSpikesRetourDegatsEnCombat()
    {
        GameBoardLogic board = new GameBoardLogic();
        AttacksResolver resolver = new AttacksResolver();
        AnimalLogic grizzly = CardFactory.createGrizzly();     // ATT 4
        AnimalLogic porcEpic = CardFactory.createPorcEpic();   // ATT 1, SharpSpikes
        board.setPlayerLine(0, Optional.of(grizzly));
        board.setPreviewLine(0, Optional.of(porcEpic));
        resolver.resolveAll(board);
        // Le Grizzly doit avoir pris 1 dégât de retour
        assertEquals(5, grizzly.getHp());
    }
}
