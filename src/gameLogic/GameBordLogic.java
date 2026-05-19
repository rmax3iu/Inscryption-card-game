package gameLogic;

import cardLogic.CardLogic;

import javax.smartcardio.Card;

public class GameBordLogic {

    private CardLogic[] m_playerLine;
    private CardLogic[] m_botLine;
    private CardLogic[] m_previewLine;

    public GameBordLogic()
    {
        m_playerLine = new CardLogic[4];
        m_botLine = new CardLogic[4];
        m_previewLine = new CardLogic[4];
    }

    public void setPlayerLine(CardLogic card, int position)
    {
        this.m_playerLine[position] = card;
    }

    public CardLogic getPlayerLine(int position)
    {
        return this.m_playerLine[position];
    }

    public void setBotLine(CardLogic card, int position)
    {
        this.m_botLine[position] = card;
    }

    public CardLogic getBotLine(int position)
    {
        return this.m_botLine[position];
    }

    public void setPreviewLine(CardLogic card, int position)
    {
        this.m_previewLine[position] = card;
    }

    public CardLogic getPreviewLine(int position)
    {
        return this.m_previewLine[position];
    }

    // faire une methode remove pr chaqsue ligne
}
