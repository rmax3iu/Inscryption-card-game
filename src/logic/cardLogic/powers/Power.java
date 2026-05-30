package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

public interface Power {

    //Méthode qui serra Override dans les classes filles et renverra leur nom
    public String getName();

    //Appelé quand la carte attaque une autre carte    (pour coureur)
    default int onAttack(int position, CardLogic left, CardLogic right) {
        return position;
    }

    //Appelé au début du tour si la carte est sur le plateau   (pour croissance)
    default AnimalLogic onTurnStart() {
        return null;
    }

    //Appelé quand la carte reçoit des dégâts   (pour pique pointue)
    default void onDamageReceived(AnimalLogic attacker) {}

    //Appelé quand une carte attaque en face d'elle (même si c'est une volante) (pour puant)
    default int attackModifierOnFacing() {
        return 0;
    }

    //Appelé quand on veut sacrifier la carte (pour nombreuses vies)
    default boolean canDeath() {
        return true;
    }

    //Appelé quand la carte attaque et dit si elle one shot la carte d'en face (DeadlyContact)
    default boolean killsOnHit(CardLogic card) {
        return false;
    }
}