package gameLogic;

import cardLogic.CardLogic;

public class GameBordLogic {

    private CardLogic[] m_playerLine;
    private CardLogic[] m_botLine;
    private CardLogic[] m_previewLine;

    public GameBordLogic() {
        m_playerLine = new CardLogic[4];
        m_botLine = new CardLogic[4];
        m_previewLine = new CardLogic[4];
    }

    public void setPlayerLine(CardLogic card, int position) {
        m_playerLine[position] = card;
    }

    public CardLogic getPlayerLine(int position) {
        return m_playerLine[position];
    }

    public CardLogic removePlayerLine(int position) {
        CardLogic card = m_playerLine[position];
        m_playerLine[position] = null;
        return card;
    }

    public void setBotLine(CardLogic card, int position) {
        m_botLine[position] = card;
    }

    public CardLogic getBotLine(int position) {
        return m_botLine[position];
    }

    public CardLogic removeBotLine(int position) {
        CardLogic card = m_botLine[position];
        m_botLine[position] = null;
        return card;
    }

    public void setPreviewLine(CardLogic card, int position) {
        m_previewLine[position] = card;
    }

    public CardLogic getPreviewLine(int position) {
        return m_previewLine[position];
    }

    public CardLogic removePreviewLine(int position) {
        CardLogic card = m_previewLine[position];
        m_previewLine[position] = null;
        return card;
    }
}
