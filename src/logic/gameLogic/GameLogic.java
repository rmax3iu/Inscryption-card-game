package logic.gameLogic;

import graphic.GameGraphic;
import graphic.Message;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardFactory;
import logic.cardLogic.CardLogic;
import logic.cardLogic.powers.Power;

import java.util.Optional;

public class GameLogic
{
    public static final int NB_ROUND = 3;
    private int m_nbVictory;
    private StackLogic m_stack;

    public GameLogic()
    {
        m_nbVictory = 0;
        m_stack = new StackLogic();
    }

    public void play()
    {
        int score;
        for(int currentRound = 0; currentRound < GameLogic.NB_ROUND; currentRound++ ){
            score = round(currentRound);
            if(score > 0){
                m_nbVictory++;
            }
            if(currentRound == 1) {
                newCard();
            }
        }

        if(m_nbVictory >= 2){
            Message.tell("Victoire");
        }else {
            Message.tell("Défaite");
        }
    }

    private int round(int currentRound){
        int score = 0;
        boolean isEnd = false;
        int currentTurn = 0;

        m_stack.copyDeck();     //On réinitialise la pioche au début de chaque partie

        ActorLogic bot = ActorLogic.newBotLogic();
        ActorLogic player = ActorLogic.newPlayerLogic();

        // Le joueur prend 4 cartes en main au début de la partie
        for(int i = 0; i < 4 && !m_stack.isEmpty(); i++){
            player.addCard(m_stack.draw());
        }

        GameBoardLogic board = new GameBoardLogic();

        TurnLogic turn;

        while(!isEnd){
            currentTurn++;
            turn = new TurnLogic(board,m_stack);

            applidGrowth(board);

            turn.botTurn(bot);
            GameGraphic.showGame(board,m_stack,currentRound+1,currentTurn,score);
            turn.playerTurn(player);
            GameGraphic.showGame(board,m_stack,currentRound+1,currentTurn,score);
            score += turn.resolveAttacks();
            GameGraphic.showGame(board,m_stack,currentRound+1,currentTurn,score);

            if(score <= -5 || score >= 5){
                isEnd = true;
            }
        }

        return score;
    }

    //Consiste à remplacer une carte que le joueur choisi par une autre.
    public void newCard() {
        AnimalLogic cardLeft = StackLogic.randomeCard();
        AnimalLogic cardRight = StackLogic.randomeCard();

        Message.tell("Choisissez une carte à ajouter : Gauche (" + cardLeft.getName() + ") ou Droite (" + cardRight.getName() + ")");
        Message.tell("Format : <Gauche/Droite> <numéro de la carte à remplacer dans votre pioche (1-" + StackLogic.DECK_SIZE + ")>");

        boolean inputOk = false;

        while(!inputOk) {
            String input = Message.demandeCard(m_stack);
            String[] cards = input.split(" ");      //Le résultat attendu est de la forme <Gauche/Droite> <numero ancienne carte>

            if (cards.length < 2) {
                Message.tell("Format invalide (ex : Gauche 1)");
                continue;
            }

            try {
                int index = Integer.parseInt(cards[1]);

                if (index > 0 && index <= StackLogic.DECK_SIZE) {
                    if (cards[0].equals("Gauche")) {
                        m_stack.changeCard(index - 1, cardLeft);
                        inputOk = true;
                    } else if (cards[0].equals("Droite")) {
                        m_stack.changeCard(index - 1, cardRight);
                        inputOk = true;
                    } else {
                        Message.tell("Nom de nouvelle carte est incorrect (ex : Gauche/Droite)");
                    }
                } else {
                    Message.tell("Numéro de carte n'existe pas.");
                }

            } catch (NumberFormatException e) {
                Message.tell("Format invalide (ex : Gauche 1)");
            }
        }
    }

    private void applidGrowth(GameBoardLogic board){
        //Colonne par colonne
        for(int i = 0; i < GameBoardLogic.BOARD_SIZE; i++){
            Optional<CardLogic> cardBot = board.getBotLine(i);          //On récupère la carte du bot
            Optional<CardLogic> cardPlayer = board.getPlayerLine(i);    //On récupère la carte du joueur

            //On regarde si elle existe
            if(cardBot.isPresent()){
                Optional<Power> powerBot = cardBot.get().getPower();    //On récupère son pouvoir
                if(powerBot.isPresent()){       //On vérifie qu'elle en a un
                    Optional<CardLogic> transformed = powerBot.get().onTurnStart();
                    if(transformed.isPresent()){
                        board.setBotLine(i, transformed);   //et on remplace la carte par la nouvelle (donc si ça fait 2 tours quelle est sur le terrain on la remplace par un loup)
                    }
                }
            }

            //Même chose ici mais pour la carte du joueur
            if(cardPlayer.isPresent()){
                Optional<Power> powerPlayer = cardPlayer.get().getPower();
                if(powerPlayer.isPresent()){
                    Optional<CardLogic> transformed = powerPlayer.get().onTurnStart();
                    if(transformed.isPresent()){
                        board.setPlayerLine(i, transformed);
                    }
                }
            }
        }
    }
}

