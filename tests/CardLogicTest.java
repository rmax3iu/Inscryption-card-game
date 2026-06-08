import logic.gameLogic.*;
import logic.cardLogic.*;
import logic.cardLogic.powers.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CardLogicTest
{
    // ─── Construction ───────────────────────────────────────────────────────────

    @Test
    public void testNomEtPvInitiaux()
    {
        CardLogic rocher = CardFactory.createRocher();
        assertEquals("Rocher", rocher.getName());
        assertEquals(5, rocher.getHp());
    }

    @Test
    public void testSapinPvInitiaux()
    {
        CardLogic sapin = CardFactory.createSapin();
        assertEquals("Sapin", sapin.getName());
        assertEquals(3, sapin.getHp());
    }

    // ─── takeDamage ─────────────────────────────────────────────────────────────

    @Test
    public void testPrendDegat()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(2);
        assertEquals(3, rocher.getHp());
    }

    @Test
    public void testPrendDegatExact()
    {
        CardLogic sapin = CardFactory.createSapin();
        sapin.takeDamage(3);
        assertEquals(0, sapin.getHp());
        assertTrue(sapin.isDead());
    }

    @Test
    public void testPrendDegatSuperieurPvResteA0()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(100);
        assertEquals(0, rocher.getHp());
    }

    // ─── isDead ─────────────────────────────────────────────────────────────────

    @Test
    public void testNEstPasMortAvantDegatSuffisants()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(4);
        assertFalse(rocher.isDead());
    }

    @Test
    public void testEstMortApresDegatSuffisants()
    {
        CardLogic sapin = CardFactory.createSapin();
        sapin.takeDamage(3);
        assertTrue(sapin.isDead());
    }

    // ─── canBeSacrify / attack ───────────────────────────────────────────────────

    @Test
    public void testObstacleNePeutPasEtreSacrifie()
    {
        CardLogic rocher = CardFactory.createRocher();
        assertFalse(rocher.canBeSacrify());
    }

    @Test
    public void testObstacleAttaquePasPasDeScoreDirect()
    {
        CardLogic rocher = CardFactory.createRocher();
        // Un obstacle ne doit pas apporter de dégâts directs
        assertEquals(0, rocher.attack(Optional.empty()));
    }

    @Test
    public void testCopyObstacle()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(2);
        CardLogic copy = rocher.copy();
        assertEquals(rocher.getName(), copy.getName());
        assertEquals(rocher.getHp(), copy.getHp());
    }
}