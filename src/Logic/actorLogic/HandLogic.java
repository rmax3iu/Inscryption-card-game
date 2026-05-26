package Logic.actorLogic;

import Logic.cardLogic.CardLogic;

import java.util.ArrayList;

public class HandLogic {
    private ArrayList<CardLogic> m_hand;

    public HandLogic(){
        m_hand = new ArrayList<CardLogic>();
    }

    public void addCard(CardLogic cardLogic){
        m_hand.add(cardLogic);
    }

    public CardLogic getCard(int indice){
        return m_hand.get(indice);
    }

    public CardLogic removeCard(int indice){
        return m_hand.remove(indice);
    }

    public int getLengthHand(){
        return m_hand.size();
    }
}
