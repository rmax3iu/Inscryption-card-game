package logic.gameLogic;

import logic.cardLogic.CardLogic;

public class GameBoardLogic {
    public static final int BOARD_SIZE = 4;     //Nombre de cartes par ligne

    private final CardLogic[] m_previewLine = new CardLogic[BOARD_SIZE];        //Là où le bot pose ses cartes
    private final CardLogic[] m_botLine = new CardLogic[BOARD_SIZE];            //Là où les cartes du bot vont à la fin du tour
    private final CardLogic[] m_playerLine = new CardLogic[BOARD_SIZE];         //Là où le joueur pose ses cartes

    public GameBoardLogic() {}

    // ----Partie playerLine-----
    //Renvoie la carte à l'indice donné
    public CardLogic getPlayerLine(int index) {
        return m_playerLine[index];
    }

    //Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setPlayerLine(int index, CardLogic card) { 
        m_playerLine[index] = card; 
    }

    //Renvoie la carte à l'indice donné
    public CardLogic removePlayerLine(int index) { 
        CardLogic c = m_playerLine[index]; 
        m_playerLine[index] = null; 
        return c; 
    }

    //-------Partie botLine------
    //Renvoie la carte à l'indice donné
    public CardLogic getBotLine(int index) {
        return m_botLine[index]; 
    }

    //Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setBotLine(int index, CardLogic card) {
        m_botLine[index] = card; 
    }

    //Renvoie la carte à l'indice donné
    public CardLogic removeBotLine(int index) {
        CardLogic c = m_botLine[index]; 
        m_botLine[index] = null; 
        return c; 
    }

    //------Partie previewLine--------
    //Renvoie la carte à l'indice donné
    public CardLogic getPreviewLine(int index) {
        return m_previewLine[index]; 
    }

    //Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setPreviewLine(int index, CardLogic card) {
        m_previewLine[index] = card; 
    }

    //Renvoie la carte à l'indice donné
    public CardLogic removePreviewLine(int index) {
        CardLogic c = m_previewLine[index]; 
        m_previewLine[index] = null; 
        return c; 
    }


    //Renvoie le nombre de cartes présente sur la botLine et la previewLine
    public int countBotCard() {
        int count = 0;
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE ; i++) {
            if (m_botLine[i] != null) {
                count++;
            }
            if(m_previewLine[i] != null){
                count++;
            }
        }
        return count;
    }

    //Renvoie le nombre de cartes présente sur la playerLine
    public int countPlayerCards() {
        int count = 0;
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++) {
            if (m_playerLine[i] != null){
                count++;
            }
        }
        return count;
    }
}