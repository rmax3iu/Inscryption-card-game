package logic.gameLogic;

import logic.cardLogic.*;
import logic.cardLogic.powers.*;

import java.util.*;


public class StackLogic {
    private static final List<AnimalLogic> m_deck = new ArrayList<>();  //La liste de carte dont la pioche est composé
    public static final int DECK_SIZE = 15;
    private List<AnimalLogic> m_draw;

    public StackLogic() {

        
        //On ajoute 7 écureuils et 7 cartes aléatoires
        for (int i = 0; i < 7; i++) {
            m_deck.add(CardFactory.createEcureuil());    //Ajout de l'écureuil
            m_deck.add(randomeCard());       //Ajout d'une carte aléatoire
        }

        //Pour l'instant on a que 14(7*2) cartes donc on ajoute un écureuil supplémentaire pour atteindre 15
        m_deck.add(CardFactory.createEcureuil());        //Un écureuil

        copyDeck();
    }

    public void copyDeck(){
        m_draw = new ArrayList<AnimalLogic>();
        //On ajoute chacune des cartes du deck dans la pioche
        for(AnimalLogic animal : m_deck){
            m_draw.add(animal.copy());
        }
        Collections.shuffle(m_draw);    //Et on mélange pour pas avoir un ordre prévisible (un écurueil puis un autre animal comme dans deck)
    }

    public static AnimalLogic randomeCard(){
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

    public void changeCard(int index, AnimalLogic animal){
        m_deck.set(index,animal);   //Remplace la nouvelle carte et retire l'ancienne
        copyDeck();                 //Copie et mélange le deck pour le mettre dans la pioche
    }

    public static AnimalLogic getCard(int index){
        return m_deck.get(index);
    }

    //Renvoie un entier qui dit si la pioche est vide
    public boolean isEmpty() {
        return m_draw.isEmpty();
    }

    //Renvoie le nombre de cartes dans la pioche
    public int size() {
        return m_draw.size();
    }

    //Renvoie la dernière carte de la liste donc la carte du dessus de la pioche (si la pioche n'est pas vide un peu compliqué sinon)
    public AnimalLogic draw() {
        if (isEmpty()) {
            throw new IllegalStateException("La pioche est vide !");
        }
        return m_draw.removeLast();
    }
}