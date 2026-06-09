import logic.cardLogic.*;
import logic.gameLogic.StackLogic;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class StackLogicTest
{


    // Construction

    @Test
    public void testDeckTailleInitiale()
    {
        StackLogic stack = new StackLogic();
        // La pioche contient DECK_SIZE cartes après construction
        assertEquals(StackLogic.DECK_SIZE, stack.size());
    }

    @Test
    public void testDeckNonVide()
    {
        StackLogic stack = new StackLogic();
        assertFalse(stack.isEmpty());
        assertFalse(stack.isEmptyBot());
    }

    // draw (piocher une carte)

    @Test
    public void testPiocherRetourneUneAnimalLogic()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic card = stack.draw();
        assertNotNull(card);
    }

    @Test
    public void testPiocherReduitLaTaille()
    {
        StackLogic stack = new StackLogic();
        int tailleBefore = stack.size();
        stack.draw();
        assertEquals(tailleBefore - 1, stack.size());
    }

    @Test
    public void testPiocherToutesLesCartes()
    {
        StackLogic stack = new StackLogic();
        while (!stack.isEmpty())
        {
            assertNotNull(stack.draw());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPiocherQuandVideRetourneNull()
    {
        StackLogic stack = new StackLogic();
        while (!stack.isEmpty()) { stack.draw(); }
        assertNull(stack.draw());
    }

    @Test
    public void testPiocherBotFonctionne()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic card = stack.drawBot();
        assertNotNull(card);
        assertFalse(stack.isEmptyBot());
    }

    // copyDeck

    @Test
    public void testCopyDeckRechargeAPiocheComplete()
    {
        StackLogic stack = new StackLogic();
        // On vide d'abord
        while (!stack.isEmpty()) { stack.draw(); }
        assertTrue(stack.isEmpty());

        // copyDeck recharge
        stack.copyDeck();
        assertEquals(StackLogic.DECK_SIZE, stack.size());
    }

    @Test
    public void testCopyDeckRechargeAussiPiocheBot()
    {
        StackLogic stack = new StackLogic();
        while (!stack.isEmptyBot()) { stack.drawBot(); }
        stack.copyDeck();
        assertFalse(stack.isEmptyBot());
    }

    @Test
    public void testCopyDeckProduitsDesCopiesIndependantes()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic original = stack.getCard(0);
        stack.copyDeck();
        AnimalLogic fromDraw = stack.draw();
        // Modifier la carte tirée ne doit pas modifier le deck original
        fromDraw.takeDamage(100);
        assertEquals(original.getName(), stack.getCard(0).getName());
        assertEquals(original.getHp(), stack.getCard(0).getHp());
    }

    // ChangeCard (ajout nouvelle carte après partie 2)

    @Test
    public void testChangeCardRemplaceLaCarteAuBonIndex()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic nouvelleCartel = CardFactory.createGrizzly();
        stack.changeCard(0, nouvelleCartel);
        assertEquals("Grizzly", stack.getCard(0).getName());
    }

    @Test
    public void testChangeCardMettreJourDeckNeSupprimePasAutresCartes()
    {
        StackLogic stack = new StackLogic();
        String nomAvant = stack.getCard(1).getName();
        stack.changeCard(0, CardFactory.createGrizzly());
        // La carte en index 1 est inchangée
        assertEquals(nomAvant, stack.getCard(1).getName());
    }

    @Test
    public void testChangeCardRechargeLaPioche()
    {
        StackLogic stack = new StackLogic();
        // Vide la pioche
        while (!stack.isEmpty()) { stack.draw(); }
        // changeCard doit rappeler copyDeck
        stack.changeCard(0, CardFactory.createGrizzly());
        assertEquals(StackLogic.DECK_SIZE, stack.size());
    }

    // getCard

    @Test
    public void testGetCardRetourneCarteValide()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic card = stack.getCard(0);
        assertNotNull(card);
    }

    //  randomeCard

    @Test
    public void testRandomeCardRetourneAnimalLogic()
    {
        StackLogic stack = new StackLogic();
        AnimalLogic card = StackLogic.randomeCard();
        assertNotNull(card);
        assertTrue(card.canBeSacrify());
    }

    @Test
    public void testRandomeCardProduitDesCartesVariees()
    {
        StackLogic stack = new StackLogic();
        // Sur 30 tirages, on doit avoir au moins 2 noms différents (loi des grands nombres)
        Set<String> noms = new HashSet<>();
        for (int i = 0; i < 30; i++)
        {
            noms.add(StackLogic.randomeCard().getName());
        }
        assertTrue(noms.size() > 1);
    }
}
