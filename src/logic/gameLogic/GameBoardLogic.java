package logic.gameLogic;

import logic.cardLogic.CardLogic;

import java.util.Optional;

public class GameBoardLogic
{
    public static final int BOARD_SIZE = 4;     // Nombre de cartes par ligne

    private final Optional<CardLogic>[] m_previewLine = new Optional[BOARD_SIZE];        // Là où le bot pose ses cartes
    private final Optional<CardLogic>[] m_botLine = new Optional[BOARD_SIZE];            // Là où les cartes du bot vont à la fin du tour
    private final Optional<CardLogic>[] m_playerLine = new Optional[BOARD_SIZE];         // Là où le joueur pose ses cartes

    public GameBoardLogic()
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            m_previewLine[i] = Optional.empty();
            m_botLine[i]     = Optional.empty();
            m_playerLine[i]  = Optional.empty();
        }
    }

    // Partie playerLine
    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> getPlayerLine(int index)
    {
        return m_playerLine[index];
    }

    // Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setPlayerLine(int index, Optional<CardLogic> card)
    {
        m_playerLine[index] = card;
    }

    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> removePlayerLine(int index)
    {
        Optional<CardLogic> c = m_playerLine[index];
        m_playerLine[index] = Optional.empty();
        return c;
    }

    // Partie botLine
    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> getBotLine(int index)
    {
        return m_botLine[index];
    }

    // Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setBotLine(int index, Optional<CardLogic> card)
    {
        m_botLine[index] = card;
    }

    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> removeBotLine(int index)
    {
        Optional<CardLogic> c = m_botLine[index];
        m_botLine[index] = Optional.empty();
        return c;
    }

    // Partie previewLine
    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> getPreviewLine(int index)
    {
        return m_previewLine[index];
    }

    // Met la carte donnée en paramètre à la position donnée sur le plateau
    public void setPreviewLine(int index, Optional<CardLogic> card)
    {
        m_previewLine[index] = card;
    }

    // Renvoie la carte à l'indice donné
    public Optional<CardLogic> removePreviewLine(int index)
    {
        Optional<CardLogic> c = m_previewLine[index];
        m_previewLine[index] = Optional.empty();
        return c;
    }

    // Renvoie le nombre de cartes présentes sur la botLine et la previewLine
    public int countBotCard()
    {
        int count = 0;
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE ; i++)
        {
            Optional<CardLogic> botCard = m_botLine[i];
            Optional<CardLogic> previewCard = m_previewLine[i];
            if (botCard.isPresent() && botCard.get().canBeSacrify())
            {
                count++;
            }
            if (previewCard.isPresent() && previewCard.get().canBeSacrify())
            {
                count++;
            }
        }
        return count;
    }

    // Renvoie le nombre de cartes présentes sur la playerLine
    public int countPlayerCards()
    {
        int count = 0;
        for (int i = 0; i < GameBoardLogic.BOARD_SIZE; i++)
        {
            Optional<CardLogic> card = m_playerLine[i];
            if (card.isPresent() && card.get().canBeSacrify())
            {
                count++;
            }
        }
        return count;
    }

    // Renvoie l'indice qui correspond à la position (B1:0,B2:1,...)
    public int getIndex(String position)
    {
        int index;
        switch (position)
        {
            case "B1":
                index = 0;
                break;
            case "B2":
                index = 1;
                break;
            case "B3":
                index = 2;
                break;
            case "B4":
                index = 3;
                break;
            default:
                index = -1;     // Quand le joueur écrit une position inexistante
                break;
        }
        return index;
    }

    @Override
    public String toString(){
        String result = "Plateau { \n" +
                         "\tPreviewLine [";
        //PreviewLine
        for(int i = 0; i < BOARD_SIZE - 1; i++){
            Optional<CardLogic> card = getPreviewLine(i);
            if(card.isPresent()){
                result += card.get().toString() + ", ";
            }else{
                result += "vide, ";
            }
        }
        Optional<CardLogic> card = m_previewLine[BOARD_SIZE -1];
        if(card.isPresent()){
            result += card.get().toString();
        }else{
            result += "vide";
        }
        result += "],\n\tBotLine [";

        //BotLine
        for(int i = 0; i < BOARD_SIZE - 1; i++){
            card = getBotLine(i);
            if(card.isPresent()){
                result += card.get().toString() + ", ";
            }else{
                result += "vide, ";
            }
        }
        card = m_botLine[BOARD_SIZE -1];
        if(card.isPresent()){
            result += card.get().toString();
        }else{
            result += "vide";
        }
        result += "],\n\tPlayerLine [";

        //PlayerLine
        for(int i = 0; i < BOARD_SIZE - 1; i++){
            card = getPlayerLine(i);
            if(card.isPresent()){
                result += card.get().toString() + ", ";
            }else{
                result += "vide, ";
            }
        }
        card = m_playerLine[BOARD_SIZE -1];
        if(card.isPresent()){
            result += card.get().toString() + ", ";
        }else{
            result += "vide, ";
        }
        result += "]\n}";

        return result;
    }
}