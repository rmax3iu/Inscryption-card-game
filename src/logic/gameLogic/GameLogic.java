package logic.gameLogic;

import graphic.Message;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardFactory;
import logic.cardLogic.CardLogic;

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
            score = round();
            if(score > 0){
                m_nbVictory++;
            }
            newCard();
        }

        if(m_nbVictory >= 2){
            Message.tell("Victoire");
        }else {
            Message.tell("Défaite");
        }
    }

    private int round(){
        int score = 0;
        boolean isEnd = false;

        ActorLogic bot = ActorLogic.newBotLogic();
        ActorLogic player = ActorLogic.newPlayerLogic();

        GameBoardLogic board = new GameBoardLogic();

        TurnLogic turn;

        while(!isEnd){
            turn = new TurnLogic(board,m_stack);

            turn.botTurn(bot);
            turn.playerTurn(player);

            score = turn.resolveAttacks();


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

        String input = Message.demandeCard();
        String[] cards = input.split(" ");      //Le résultat attendu est de la forme <Gauche/Droite> <numero ancienne carte>

        try {
            int index = Integer.parseInt(cards[1]);

            if(index > 0 && index < CardFactory.NB_CARD){
                if(cards[0].equals("Gauche")){
                    m_stack.changeCard(index - 1,cardLeft);
                } else if (cards[0].equals("Droite")) {
                    m_stack.changeCard(index - 1,cardRight);
                }else {
                    Message.tell("Numéro de nouvelle carte est incorrect");
                }
            }else{
                Message.tell("Numéro de card n'existe pas.");
            }

        }catch (NumberFormatException e){
            Message.tell("Format invalide (ex : 1 5)");
        }

    }
}

