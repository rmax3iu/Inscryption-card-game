package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public interface Power {

    public String getName();

    // Appelé quand cette carte attaque une autre carte
    default int onAttack(int position, CardLogic left, CardLogic right) {
        return position;
    }

    // Appelé au début du tour si la carte est sur le plateau
    default AnimalLogic onTurnStart() {
        return null;
    }

    // Appelé quand cette carte reçoit des dégâts
    default void onDamageReceived(AnimalLogic attacker) {}

    default int attackModifierOnFacing() {
        return 0;
    }

    default boolean canDeath() {
        return true;
    }

    default boolean killsOnHit(CardLogic card) {
        return false;
    }
}