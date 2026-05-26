package Logic.actorLogic;

import Logic.cardLogic.CardLogic;

public abstract class ActorLogic {
    private int m_bonnes;
    private HandLogic m_handLogic;

    public ActorLogic(int bonnes){
        m_bonnes = bonnes;
        m_handLogic = new HandLogic();
    }

    public int getBonnes(){
        return m_bonnes;
    }

    public void addBonnes(int nbBonnes){
        m_bonnes += nbBonnes;
    }

    public void addCard(CardLogic cardLogic){
        m_handLogic.addCard(cardLogic);
    }

    public CardLogic getCard(int indice){
        return m_handLogic.getCard(indice);
    }

    public CardLogic removeCard(int indice){
        return m_handLogic.removeCard(indice);
    }

    public int lengthHand(){
        return m_handLogic.getLengthHand();
    }

}
