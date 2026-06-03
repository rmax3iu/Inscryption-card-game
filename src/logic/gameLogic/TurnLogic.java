package logic.gameLogic;

import graphic.Message;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.CardLogic;

import java.util.Optional;

public class TurnLogic {
    private GameBoardLogic m_gameboard;
    private StackLogic m_stack;
    private final PlayerAction m_playerAction = new PlayerAction();
    private final BotStrategy m_botStrategy = new BotStrategy();
    private final AttacksResolver m_attacksResolver = new AttacksResolver();

    public TurnLogic(GameBoardLogic gameboard, StackLogic stack){
        m_gameboard = gameboard;
        m_stack = stack;
    }

    public void botTurn(ActorLogic bot){
        m_botStrategy.drawIfPossible(bot, m_stack);
        m_botStrategy.placeCards(bot,m_gameboard);
    }

    public void playerTurn(ActorLogic player) {
        boolean hasDraw = false;
        boolean turnOver = false;

        while(!turnOver){
            String input = Message.basicChoice();
            String[] action = input.split(" ");

            switch (action[0]){
                case "fin" :
                    turnOver = true;
                    break;
                case "piocher" :
                    if(!hasDraw){
                        m_playerAction.drawCard(player,m_stack);
                        hasDraw = true;
                    }else{
                        Message.tell("Tu as déjà pioché fait autre chose");
                    }
                    break;
                case "placer" :
                    if(action.length == 3){
                        try {
                            // On récupère le chiffre action[1] et on le convertit en entier
                            int numeroCarte = Integer.parseInt(action[1]);

                            // On récupère la chaîne de caractères action[2]
                            String position = action[2];

                            if ((numeroCarte >= 0 && numeroCarte < player.handSize())){
                                int index;
                                switch (position){
                                    case "B1" :
                                        index = 0;
                                        break;
                                    case "B2" :
                                        index = 1;
                                        break;
                                    case "B3":
                                        index = 2;
                                        break;
                                    case "B4":
                                        index = 3;
                                        break;
                                    default:
                                        index = -1;     //Quand le joueur écrit une position inexistante
                                        break;
                                }
                                if(index != -1) {
                                    Optional<CardLogic> card = m_gameboard.getPlayerLine(numeroCarte);
                                    if(card.isEmpty()) {
                                        m_gameboard.setPlayerLine(index, Optional.of(player.removeCard(numeroCarte)));
                                        Message.tell("Tu as joué la carte " + numeroCarte + " sur la position " + position + ".");
                                    }else {
                                        Message.tell(("Cette position contient déjà une carte"));
                                    }
                                }else {
                                    Message.tell("Position incorrect (ex : B1,B2,B3,B4)");
                                }
                            }else {
                                Message.tell("Carte inexistante.");
                            }

                        } catch (NumberFormatException e) {
                            // Si "Integer.parseInt" échoue (ex: le joueur a tapé "placer A B2")
                            Message.tell("Le premier paramètre doit être un chiffre ! Exemple : placer 1 B2");
                        }
                    }else {
                        Message.tell("Format invalide. Exemple : placer 1 B3");
                    }
                    break;
                default :
                    Message.tell("Commande inconnue. Tapez [fin], [piocher] ou [placer <n> <pos>].");
                    break;
            }
        }
    }

    public int resolveAttacks(){
        return m_attacksResolver.resolveAll(m_gameboard);
    }

}
