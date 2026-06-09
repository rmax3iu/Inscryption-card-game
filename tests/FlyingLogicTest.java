import logic.cardLogic.*;
import logic.cardLogic.powers.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FlyingLogicTest
{
    // Construction

    @Test
    public void testMoineauEstVolant()
    {
        FlyingLogic moineau = CardFactory.createMoineau();
        assertTrue(moineau.isFlying());
        assertEquals("Moineau", moineau.getName());
        assertEquals(2, moineau.getHp());
        assertEquals(1, moineau.getAttack());
    }

    @Test
    public void testCorbeauEstVolant()
    {
        FlyingLogic corbeau = CardFactory.createCorbeau();
        assertTrue(corbeau.isFlying());
        assertEquals("Corbeau", corbeau.getName());
        assertEquals(3, corbeau.getHp());
        assertEquals(2, corbeau.getAttack());
    }

    @Test
    public void testAnimalTerrestreNestPasVolant()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        assertFalse(grizzly.isFlying());
    }

    // attack – score direct (ignore la carte en face)

    @Test
    public void testAttaqueSansCibleRenvoieAttaque()
    {
        FlyingLogic moineau = CardFactory.createMoineau();
        assertEquals(1, moineau.attack(Optional.empty()));
    }

    @Test
    public void testAttaqueIgnoreCarteTerrestreNormale()
    {
        FlyingLogic corbeau = CardFactory.createCorbeau();
        AnimalLogic grizzly = CardFactory.createGrizzly();   // PV 6, pas de pouvoir
        // Un volant ignore le terrestre : renvoie son ATT comme score direct
        int score = corbeau.attack(Optional.of(grizzly));
        assertEquals(2, score);
        // Le Grizzly ne prend pas de dégâts
        assertEquals(6, grizzly.getHp());
    }

    @Test
    public void testAttaqueIgnoreObstacle()
    {
        FlyingLogic moineau = CardFactory.createMoineau();
        CardLogic rocher = CardFactory.createRocher();
        int score = moineau.attack(Optional.of(rocher));
        assertEquals(1, score);
        assertEquals(5, rocher.getHp());    // Obstacle intact
    }

    // Interaction avec Stinking

    @Test
    public void testAttaqueReduitePuanteur()
    {
        FlyingLogic corbeau = CardFactory.createCorbeau();          // ATT 2
        AnimalLogic punaise = CardFactory.createPunaise();          // A Stinking : réduit ATT de 1
        // Le volant ne contourne pas Stinking
        int score = corbeau.attack(Optional.of(punaise));
        assertEquals(1, score);     // 2 - 1 = 1
        assertEquals(2, punaise.getHp());   // La Punaise n'est pas touchée directement
    }

    @Test
    public void testAttaqueNonReduiteSansPuanteur()
    {
        FlyingLogic corbeau = CardFactory.createCorbeau();  // ATT 2
        AnimalLogic hermine = CardFactory.createHermine();  // Pas de pouvoir
        int score = corbeau.attack(Optional.of(hermine));
        assertEquals(2, score);     // ATT non réduite
    }

    // canBeSacrify

    @Test
    public void testVolantPeutEtreSacrifie()
    {
        FlyingLogic moineau = CardFactory.createMoineau();
        assertTrue(moineau.canBeSacrify());
    }

    // copy

    @Test
    public void testCopyVolantConserveLesDonnees()
    {
        FlyingLogic corbeau = CardFactory.createCorbeau();
        corbeau.takeDamage(1);
        AnimalLogic copy = corbeau.copy();
        assertEquals("Corbeau", copy.getName());
        assertEquals(2, copy.getHp());
        assertEquals(2, copy.getAttack());
        assertTrue(copy.isFlying());
    }
}
