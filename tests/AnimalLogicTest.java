import logic.cardLogic.*;
import logic.cardLogic.powers.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class AnimalLogicTest
{
    // Construction

    @Test
    public void testNomEtPvGrizzly()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        assertEquals("Grizzly", grizzly.getName());
        assertEquals(6, grizzly.getHp());
        assertEquals(4, grizzly.getAttack());
    }

    @Test
    public void testNomEtPvCoyote()
    {
        AnimalLogic coyote = CardFactory.createCoyote();
        assertEquals("Coyote", coyote.getName());
        assertEquals(1, coyote.getHp());
        assertEquals(2, coyote.getAttack());
    }

    @Test
    public void testCanBeSacrify()
    {
        AnimalLogic hermine = CardFactory.createHermine();
        assertTrue(hermine.canBeSacrify());
    }

    // attack – sans cible (score direct)

    @Test
    public void testAttaqueSansCibleRenvoieAttaque()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        assertEquals(4, grizzly.attack(Optional.empty()));
    }

    @Test
    public void testAttaqueSansCibleCoyote()
    {
        AnimalLogic coyote = CardFactory.createCoyote();
        assertEquals(2, coyote.attack(Optional.empty()));
    }

    @Test
    public void testAttaqueAvecAttaqueZeroNeFaitPasScore()
    {
        AnimalLogic ecureuil = CardFactory.createEcureuil();
        assertEquals(0, ecureuil.attack(Optional.empty()));
    }

    // attack – avec cible

    @Test
    public void testAttaqueAvecCibleReduitPv()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        AnimalLogic hermine = CardFactory.createHermine();
        grizzly.attack(Optional.of(hermine));
        assertEquals(0, hermine.getHp());   // 3 PV - 4 ATT → 0
    }

    @Test
    public void testAttaqueAvecCibleRetourneZeroScore()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        AnimalLogic hermine = CardFactory.createHermine();
        int score = grizzly.attack(Optional.of(hermine));
        assertEquals(0, score);             // Pas de score quand on attaque une cible
    }

    @Test
    public void testAttaqueNeTuePasCiblePvSuperieure()
    {
        AnimalLogic louveteau = CardFactory.createLouveteau();  // ATT 1
        AnimalLogic grizzly = CardFactory.createGrizzly();       // PV 6
        louveteau.attack(Optional.of(grizzly));
        assertEquals(5, grizzly.getHp());
        assertFalse(grizzly.isDead());
    }

    @Test
    public void testAttaqueObstacleReduitPvObstacle()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        CardLogic rocher = CardFactory.createRocher();
        grizzly.attack(Optional.of(rocher));
        assertEquals(1, rocher.getHp());    // 5 PV - 4 ATT
    }

    // Pouvoir : DeadlyContact

    @Test
    public void testDeadlyContactTueEnUnCoup()
    {
        AnimalLogic vipere = CardFactory.createVipere();    // A le pouvoir DeadlyContact
        AnimalLogic grizzly = CardFactory.createGrizzly();  // 6 PV normalement invulnérable
        vipere.attack(Optional.of(grizzly));
        assertTrue(grizzly.isDead());
    }

    @Test
    public void testDeadlyContactNeTuePasObstacle()
    {
        AnimalLogic vipere = CardFactory.createVipere();
        CardLogic rocher = CardFactory.createRocher();       // obstacle : canBeSacrify() == false
        vipere.attack(Optional.of(rocher));
        // L'obstacle ne doit pas être tué instantanément (1 ATT vs 5 PV)
        assertFalse(rocher.isDead());
        assertEquals(4, rocher.getHp());
    }

    // Pouvoir : SharpSpikes

    @Test
    public void testSharpSpikesRenvoieDegat()
    {
        AnimalLogic porcEpic = CardFactory.createPorcEpic(); // A le pouvoir SharpSpikes
        AnimalLogic grizzly = CardFactory.createGrizzly();   // 6 PV, ATT 4
        grizzly.attack(Optional.of(porcEpic));
        // Le Grizzly prend 1 dégât retour
        assertEquals(5, grizzly.getHp());
    }

    @Test
    public void testSharpSpikesNeRenvoiePasDegatsObstacle()
    {
        // Un obstacle qui attaque n'a pas d'animal pour recevoir le retour
        AnimalLogic porcEpic = CardFactory.createPorcEpic();
        CardLogic rocher = CardFactory.createRocher();
        // Rocher n'a pas le pouvoir, donc pas de dégâts retour sur porcEpic
        rocher.attack(Optional.of(porcEpic));
        assertEquals(2, porcEpic.getHp());  // porcEpic non touché
    }

    // Pouvoir : ManyLife

    @Test
    public void testManyLifeCarteSurvitAuSacrifice()
    {
        AnimalLogic chat = CardFactory.createChat();            // A le pouvoir ManyLife
        Optional<CardLogic> result = chat.sacrify();
        // La carte doit rester sur le plateau (non vide)
        assertTrue(result.isPresent());
    }

    @Test
    public void testSansPouvourSacrificeDetruitsLaCarte()
    {
        AnimalLogic hermine = CardFactory.createHermine();     // Pas de pouvoir
        Optional<CardLogic> result = hermine.sacrify();
        assertTrue(result.isEmpty());
    }

    // Pouvoir : Growth

    @Test
    public void testGrowthPasDeTransformationAuPremierTour()
    {
        AnimalLogic louveteau = CardFactory.createLouveteau(); // A le pouvoir Growth
        Optional<CardLogic> result = louveteau.getPower().get().onTurnStart();
        assertTrue(result.isEmpty());   // Rien au 1er tour
    }

    @Test
    public void testGrowthTransformeEnLoupAuDeuxiemeTour()
    {
        AnimalLogic louveteau = CardFactory.createLouveteau();
        Power growth = louveteau.getPower().get();
        growth.onTurnStart();                       // Tour 1 : rien
        Optional<CardLogic> result = growth.onTurnStart();  // Tour 2 : transformation
        assertTrue(result.isPresent());
        assertEquals("Loup", result.get().getName());
    }

    // copy

    @Test
    public void testCopyAnimalConserveLesDonnees()
    {
        AnimalLogic grizzly = CardFactory.createGrizzly();
        grizzly.takeDamage(2);
        AnimalLogic copy = grizzly.copy();
        assertEquals("Grizzly", copy.getName());
        assertEquals(4, copy.getHp());
        assertEquals(4, copy.getAttack());
    }

    @Test
    public void testCopyEstIndependante()
    {
        AnimalLogic hermine = CardFactory.createHermine();
        AnimalLogic copy = hermine.copy();
        copy.takeDamage(3);
        assertEquals(3, hermine.getHp());  // L'original n'est pas touché
    }

    @Test
    public void testCopyAvecPouvoirCopieLesPouvoir()
    {
        AnimalLogic chat = CardFactory.createChat();    // ManyLife
        AnimalLogic copy = chat.copy();
        assertTrue(copy.hasPower());
        assertEquals(ManyLife.NAME, copy.getPower().get().getName());
    }
}
