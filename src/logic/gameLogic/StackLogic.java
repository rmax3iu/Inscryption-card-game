package logic.gameLogic;

import logic.cardLogic.*;
import logic.cardLogic.powers.*;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StackLogic {
    private List<AnimalLogic> deck;

    public StackLogic() {
        deck = new ArrayList<>();       //La liste de carte dont la pioche est composé

        //On ajoute 7 écureuils et 7 cartes aléatoires
        for (int i = 0; i < 7; i++) {
            deck.add(CardFactory.createEcureuil());    //Ajout de l'écureuil
            deck.add(randomeCard());       //Ajout d'une carte aléatoire
        }

        //Pour l'instant on a que 14(7*2) cartes donc on ajoute un écureuil supplémentaire pour atteindre 15
        deck.add(CardFactory.createEcureuil());        //Un écureuil

        //Maintenant on mélange les cartes pour pas avoir une carte sur 2 un écureil
        Random rnd = new Random();      //Permet de donner un nombre aléatoire
        for(int i = 0; i < size(); i++){
            //on choisit 2 cartes aléatoirement
            int indice1 = rnd.nextInt(0, size());
            int indice2 = rnd.nextInt(0, size());

            //On stocke la 1er carte temporairement
            AnimalLogic tempo = deck.get(indice1);

            //Puis on les échange de place
            deck.set(indice1, deck.get(indice2));
            deck.set(indice2, tempo);
        }
    }

    public AnimalLogic randomeCard(){
        Random rnd = new Random();      //Permet de donner un nombre aléatoire
        //Chaque nombre correspond à une carte
        switch (rnd.nextInt(0,CardFactory.NB_CARD)){
            case 0 :
                return CardFactory.createChat();
            case 1 :
                return CardFactory.createGrizzly();
            case 2 :
                return CardFactory.createCoyote();
            case 3 :
                return CardFactory.createMoineau();
            case 4 :
                return CardFactory.createCorbeau();
            case 5 :
                return CardFactory.createEcureuil();
            case 6 :
                return CardFactory.createHermine();
            case 7 :
                return CardFactory.createLouveteau();
            case 8 :
                return CardFactory.createLoup();
            case 9 :
                return CardFactory.createPunaise();
            case 10 :
                return CardFactory.createElan();
            case 11 :
                return CardFactory.createVipere();
            case 12 :
                return CardFactory.createPorcEpic();
        }
        return CardFactory.createEcureuil();            //Pour qu'il ne râle pas je met un écureuil car c'est pas grave de piocher un écureuil au pire
    }

    //Renvoie un entier qui dit si la pioche est vide
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    //Renvoie le nombre de cartes dans la pioche
    public int size() {
        return deck.size();
    }

    //Renvoie la dernière carte de la liste donc la carte du dessus de la pioche (si la pioche n'est pas vide un peu compliqué sinon)
    public AnimalLogic draw() {
        if (isEmpty()) {
            throw new IllegalStateException("La pioche est vide !");
        }
        return deck.removeLast();
    }
}