package logic.cardLogic.powers;

import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public abstract class Power {

    //Méthode qui serra Override dans les classes filles et renverra leur nom
    public abstract String getName();

    public abstract Power copy();

    //Appelé quand la carte attaque une autre carte    (pour coureur)
    public int onAttack(int position, Optional<CardLogic> left, Optional<CardLogic> right) {
        return position;
    }

    //Appelé au début du tour si la carte est sur le plateau   (pour croissance)
    public Optional<CardLogic> onTurnStart() {
        return Optional.empty();
    }

    //Appelé quand la carte reçoit des dégâts   (pour pique pointue)
    public void onDamageReceived(AnimalLogic attacker) {

    }

    //Appelé quand une carte attaque en face d'elle (même si c'est une volante) (pour puant)
    public int attackModifierOnFacing() {
        return 0;
    }

    //Appelé quand on veut sacrifier la carte (pour nombreuses vies)
    public boolean canDeath() {
        return true;
    }

    //Appelé quand la carte attaque et dit si elle one shot la carte d'en face (DeadlyContact)
    public boolean killsOnHit(){
        return false;
    }
}