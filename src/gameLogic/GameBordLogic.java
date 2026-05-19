package gameLogic;

import card.Card;

public class GameBordLogic {

    private Card[] m_playerLine;
    private Card[] m_botLine;
    private Card[] m_previewLine;

    public GameBordLogic(){
        m_playerLine = new Card[4];
        m_botLine = new Card[4];
        m_previewLine = new Card[4];
    }


}
