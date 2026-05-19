package actor;

import card.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> m_hand;

    public Hand(){
        m_hand = new ArrayList<Card>();
    }

    public void addCard(Card card){
        m_hand.add(card);
    }

    public Card getCard(int indice){
        return m_hand.get(indice);
    }

    public Card removeCard(int indice){
        return m_hand.remove(indice);
    }
}
