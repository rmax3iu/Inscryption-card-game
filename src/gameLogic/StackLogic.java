package gameLogic;

import java.util.ArrayList;
import java.util.Random;

import card.Card;


public class StackLogic {
    private ArrayList<Card> m_cards;

    //cards c'est la liste de toute les cartes du jeu avec en 1er carte l'écureuil
    public StackLogic(ArrayList<Card> cards){
        m_cards = new ArrayList<Card>();
        Random rdn = new Random();
        for(int i = 1; i <= cards.size() ; i++){
            m_cards.add(cards.get(rdn.nextInt(0,i)));
        }
    }

    public void addCard(Card card){
        m_cards.add(card);
    }

    public Card drawCard(){
        return m_cards.removeLast();
    }

    public int length(){
        return m_cards.size();
    }

    public boolean isEmpty(){
        return m_cards.isEmpty();
    }
}
