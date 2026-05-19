package actor;

import card.Card;

public abstract class Actor {
    private int m_bonnes;
    private Hand m_hand;

    public Actor(int bonnes){
        m_bonnes = bonnes;
        m_hand = new Hand();
    }

    public int getBonnes(){
        return m_bonnes;
    }

    public void addBonnes(int nbBonnes){
        m_bonnes += nbBonnes;
    }

    public void addCard(Card card){
        m_hand.addCard(card);
    }

    public Card getCard(int indice){
        return m_hand.getCard(indice);
    }

    public Card removeCard(int indice){
        return m_hand.removeCard(indice);
    }


}
