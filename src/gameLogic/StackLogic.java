package gameLogic;

import cardLogic.CardLogic;
import java.util.ArrayList;
import java.util.Collections;


public class StackLogic {
    private ArrayList<CardLogic> m_cardLogics;

    //cards c'est la liste de toute les cartes du jeu avec en 1er carte l'écureuil
    public StackLogic(ArrayList<CardLogic> cardLogics) {
        m_cardLogics = new ArrayList<>(cardLogics);
        Collections.shuffle(m_cardLogics);
    }

    public void addCard(CardLogic cardLogic){
        m_cardLogics.add(cardLogic);
    }

    public CardLogic drawCard(){
        return m_cardLogics.removeLast();
    }

    public int length(){
        return m_cardLogics.size();
    }

    public boolean isEmpty(){
        return m_cardLogics.isEmpty();
    }
}
