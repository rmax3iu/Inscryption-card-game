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
        int pvOrigine = rocher.getHp() - 2;     //normalement 3
        rocher.takeDamage(2);
        assertEquals(pvOrigine, rocher.getHp());
    }

    @Test
    public void testPrendDegatSuperieurPv()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(100);
        assertEquals(0, rocher.getHp());            //On vérifie que les pv ne descend pas en dessous de 0
    }

    // ─── isDead ─────────────────────────────────────────────────────────────────

    @Test
    public void testDegatInsuffisantPourTuer()
    {
        CardLogic rocher = CardFactory.createRocher();
        rocher.takeDamage(4);
        assertFalse(rocher.isDead());
    }

    @Test
    public void testDegatSuffisantsPourTuer()
    {
        CardLogic sapin = CardFactory.createSapin();
        sapin.takeDamage(3);
        assertTrue(sapin.isDead());
    }

    // ─── canBeSacrify / attack ───────────────────────────────────────────────────

    @Test
    public void testSacrificeObstacle()
    {
        CardLogic rocher = CardFactory.createRocher();
        assertFalse(rocher.canBeSacrify());
    }

    @Test
    public void testObstacleAttaquePasScore()
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