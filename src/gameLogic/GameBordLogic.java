package gameLogic;

import cardLogic.CardLogic;

public class GameBordLogic {

    private CardLogic[] m_playerLine;
    private CardLogic[] m_botLine;
    private CardLogic[] m_previewLine;

    public GameBordLogic(){
        m_playerLine = new CardLogic[4];
        m_botLine = new CardLogic[4];
        m_previewLine = new CardLogic[4];
    }


}
