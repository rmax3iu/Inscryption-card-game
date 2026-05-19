package gameLogic;

import actorLogic.PlayerLogic;
import actorLogic.BotLogic;
import actorLogic.ActorLogic;
import cardLogic.AnimalLogic;
import cardLogic.CardLogic;

public class TurnLogic {
    private GameBordLogic m_gamebord;
    private StackLogic m_stack;

    public TurnLogic(GameBordLogic gamebord, StackLogic stack){
        m_gamebord = gamebord;
        m_stack = stack;
    }

    public void botTurn(BotLogic bot){

    }

    public void playerTurn(PlayerLogic player){

    }

    //Place les cartes du joueur ou du bot sur leur ligne respectif
    // (ne met pas les cartes du bot sur bot line se serra fait tout seul après resolveAttacks)
    public void placeCard(ActorLogic actor, int IndexHand, int position){
        if(actor instanceof PlayerLogic){
            m_gamebord.setPlayerLine(actor.removeCard(IndexHand), position);
        }else{
            m_gamebord.setPreviewLine(actor.removeCard(IndexHand), position);
        }
    }

    public void drawCard(ActorLogic actor){
        actor.addCard(m_stack.drawCard());
    }

    public int resolveAttacks(PlayerLogic player, BotLogic bot){
        int score = 0;
        for(int i = 0; i < 4; i++){
            if(m_gamebord.getPlayerLine(i) != null && m_gamebord.getPlayerLine(i) instanceof AnimalLogic animal){
                if(m_gamebord.getBotLine(i) != null) {
                    score += animal.attack(player, m_gamebord.getBotLine(i));
                } else {
                    score += animal.attack(player,m_gamebord.getPreviewLine(i));
                }
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getBotLine(i) != null && m_gamebord.getBotLine(i).isDead()){
                m_gamebord.removeBotLine(i);
            }
            if(m_gamebord.getPreviewLine(i) != null && m_gamebord.getPreviewLine(i).isDead()){
                m_gamebord.removePreviewLine(i);
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getPreviewLine(i) != null && m_gamebord.getBotLine(i) == null){
                m_gamebord.setBotLine(m_gamebord.removePreviewLine(i), i);
            }
        }

        for(int i = 0 ; i < 4 ; i++){
            if(m_gamebord.getBotLine(i) != null && m_gamebord.getBotLine(i) instanceof AnimalLogic animal){
                if(m_gamebord.getPlayerLine(i) != null) {
                    score += animal.attack(player, m_gamebord.getBotLine(i));
                } else {
                    score += animal.attack(player,m_gamebord.getPreviewLine(i));
                }
            }
        }

        for(int i = 0 ; i < 4 ; i++) {
            if(m_gamebord.getPlayerLine(i) != null && m_gamebord.getPlayerLine(i).isDead()){
                m_gamebord.removePlayerLine(i);
            }
        }
        return score;
    }

}
