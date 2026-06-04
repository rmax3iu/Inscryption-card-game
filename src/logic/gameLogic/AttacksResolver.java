package logic.gameLogic;

import logic.cardLogic.CardLogic;
import logic.cardLogic.powers.Power;

import javax.smartcardio.Card;
import java.util.Optional;

public class AttacksResolver {
    public AttacksResolver(){}

    public int resolveAll(GameBoardLogic board){
        int score = 0;
        moveBotCards(board);        //On fait avancer les cartes de la ligne preview sur la ligne bot
        //Les cartes de playerLine attaque celle de botLine et on modifie le score en conséquence
        score += resolvePlayerAttacks(board);
        removeDeadBotCards(board);          //Retire les cartes tuées par les cartes du joueur
        removeDeadPlayerCards(board);       //Retire les cartes tuées par les cartes du bot(si jamais la carte qu'on attaque a le pouvoir pique pointue)

        //Les cartes de botLine attaque celle de playerLine et on modifie le score en conséquence
        score += resolveBotAttacks(board);
        removeDeadPlayerCards(board);       //Retire les cartes tuées par les cartes du bot
        removeDeadBotCards(board);          //Retire les cartes tuées par les cartes du player(si jamais la carte qu'on attaque a le pouvoir pique pointue)
        return score;
    }

    private int resolvePlayerAttacks(GameBoardLogic board){
        int score = 0;
        for(int index = 0; index < GameBoardLogic.BOARD_SIZE; index++){
            Optional<CardLogic> cardPlayer = board.getBotLine(index);
            Optional<CardLogic> cardBot = board.getBotLine(index);
            if(cardPlayer.isPresent()){             //On vérifie que la carte n'est pas vide
                score += cardPlayer.get().attack(cardBot);          //Renvoie l'impacte sur le score ou attaque la carte en face s'il y en a une et que c'est pas un animal volant
                Optional<Power> power = cardPlayer.get().getPower();
                if(power.isPresent()) {
                    Optional<CardLogic> CardRight = Optional.empty();
                    Optional<CardLogic> CardLeft = Optional.empty();
                    if(index < GameBoardLogic.BOARD_SIZE - 1) {
                        CardRight = board.getPlayerLine(index + 1);
                    }
                    if(index > 0){
                        CardLeft = board.getPlayerLine(index - 1);                        
                    }
                    int newIndex = power.get().onAttack(index,CardLeft,CardRight);
                }
            }
        }

        return score;
    }

    private int resolveBotAttacks(GameBoardLogic board){
        int score = 0;
        for(int index = 0; index < GameBoardLogic.BOARD_SIZE; index++){
            Optional<CardLogic> cardBot = board.getBotLine(index);
            Optional<CardLogic> cardPlayer = board.getBotLine(index);
            if(cardBot.isPresent()){             //On vérifie que la carte n'est pas vide
                score -= cardBot.get().attack(cardPlayer);          //Renvoie l'impacte sur le score ou attaque la carte en face s'il y en a une et que c'est pas un animal volant
            }
        }

        return score;
    }

    private void moveBotCards(GameBoardLogic board){
        for(int index = 0; index < GameBoardLogic.BOARD_SIZE; index++){
            Optional<CardLogic> cardPreview = board.getPreviewLine(index);
            Optional<CardLogic> cardBot = board.getBotLine(index);
            //Donc s'il y a une carte dans la preview line et pas de carte dans la bot line
            if(cardPreview.isPresent() && cardBot.isEmpty()){
                board.setBotLine(index,cardPreview);
            }
        }
    }

    private void removeDeadBotCards(GameBoardLogic board){
        for(int index = 0; index < GameBoardLogic.BOARD_SIZE; index++){
            Optional<CardLogic> cardBot = board.getBotLine(index);
            if(cardBot.isPresent() && cardBot.get().isDead()){
                board.setBotLine(index, Optional.empty());
            }
        }
    }

    private void removeDeadPlayerCards(GameBoardLogic board){
        for(int index = 0; index < GameBoardLogic.BOARD_SIZE; index++){
            Optional<CardLogic> cardPlayer = board.getPlayerLine(index);
            if(cardPlayer.isPresent() && cardPlayer.get().isDead()){
                board.setPlayerLine(index, Optional.empty());
            }
        }
    }
}
