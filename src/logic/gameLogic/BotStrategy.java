package logic.gameLogic;

import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;

import java.util.Random;

public class BotStrategy {
    public BotStrategy() {}

    //Pioche tant que la pioche n'est pas vide
    public void drawIfPossible(ActorLogic bot, StackLogic stack) {
        if(!stack.isEmpty()){
            bot.addCard(stack.draw());
        }
    }

    //Pose tout les cartes qu'il peut poser
    public void placeCards(ActorLogic bot, GameBoardLogic board) {
        for(int indexCard = bot.handSize(); indexCard >= 0; indexCard--){
            CardLogic currentCard = bot.getCard(indexCard);
            if(canAfford(bot,board,currentCard)){
                payCost(bot,board,currentCard);
                int position = choosePlacementSlot(board);
                if(position != -1) {    //On vérifie qu'on puisse la poser
                    board.setPreviewLine(position, currentCard);
                }
            }
        }
    }

    //Renvoie un boolean qui dit si on peut payer le coup de la carte
    private boolean canAfford(ActorLogic bot, GameBoardLogic board, CardLogic card) {
        if(card instanceof AnimalLogic animal){
            if(animal.getSummonCost().isBonesCost() && bot.getBones() > animal.getSummonCost().getBones()){
                return true;
            }else if(animal.getSummonCost().isBloodCost() && board.countBotCard() > animal.getSummonCost().getBlood()){
                return true;
            }else if(animal.getSummonCost().isFree()){
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    //Fait payer au bot le coup de la carte
    private void payCost(ActorLogic bot, GameBoardLogic board, CardLogic card) {
        if(card instanceof AnimalLogic animal){
            if(animal.getSummonCost().isBonesCost() && bot.getBones() > animal.getSummonCost().getBones()){
                sacrificeBotCards(board,animal.getSummonCost().getBlood());
                bot.addBones(animal.getSummonCost().getBones());    //Vu qu'on sacrifie des cartes on gagne des os
            }else if(animal.getSummonCost().isBloodCost() && board.countBotCard() > animal.getSummonCost().getBlood()){
                bot.addBones(-animal.getSummonCost().getBones());
            }
        }
    }

    //Sacrifie le nombre de cartes nécessaire
    private void sacrificeBotCards(GameBoardLogic board, Integer count) {
        int nbCarteSacrifie = 0;
        int i = 0;
        while (nbCarteSacrifie < count && i < GameBoardLogic.BOARD_SIZE) {
            if (board.getBotLine(i) != null) {
                board.removeBotLine(i);
                nbCarteSacrifie++;
            }
            if (board.getPreviewLine(i) != null) {
                board.getPreviewLine(i);
                nbCarteSacrifie++;
            }
            i++;
        }
    }

    //Renvoie la position où l'on peut placer la carte et -1 si on peut pas
    private int choosePlacementSlot(GameBoardLogic board) {
        for(int i = 0; i < GameBoardLogic.BOARD_SIZE; i++){
            if(board.getPreviewLine(i) == null){
                return i;
            }
        }
        return -1;
    }
}
