package logic.gameLogic;

import logic.Ask;
import logic.actorLogic.PlayerLogic;
import logic.actorLogic.BotLogic;
import logic.actorLogic.ActorLogic;
import logic.cardLogic.AnimalLogic;
import logic.cardLogic.CardLogic;
import logic.cardLogic.ObstacleLogic;

import java.util.Random;

public class TurnLogic {
    private GameBordLogic m_gamebord;
    private StackLogic m_stack;

    public TurnLogic(GameBordLogic gamebord, StackLogic stack){
        m_gamebord = gamebord;
        m_stack = stack;
    }

    public void botTurn(BotLogic bot){
        Random rnd = new Random();
        int nb = rnd.nextInt(0,100);

        //5% de chance qu'il skip son tour
        if(nb < 95){
            //15% de chance qu'il ne pioche pas
            if(nb < 85){
                drawCard(bot);
            }

            //On regarde si on peut poser chaque carte
            for (int i= 0; i < bot.lengthHand(); i ++){

                int cardIndex = rnd.nextInt(0, bot.lengthHand());
                CardLogic card = bot.getCard(cardIndex);

                boolean peutPoser = false;
                //si c'est un animal on vérifie qu'on peut la poser
                if(card instanceof AnimalLogic animal) {
                    if(animal.isBlood() && animal.getCost() <= m_gamebord.getNbBotCard()){
                        for(int j=0; j < animal.getCost(); j ++){
                            if(m_gamebord.getPreviewLine(j) != null){
                                m_gamebord.removePreviewLine(j);
                            }else if(m_gamebord.getBotLine(j) != null){
                                m_gamebord.removeBotLine(j);
                            }
                        }
                        peutPoser = true;
                    }else if(animal.isBonnes() && animal.getCost() <= bot.getBonnes()){
                        bot.addBonnes(-animal.getCost());
                        peutPoser = true;
                    }
                }else{
                    peutPoser = true;
                }
                if(peutPoser) {
                    boolean poser = false;
                    int j = 0;
                    while(!poser && j < 4) {
                        if (m_gamebord.getPreviewLine(j) == null) {
                            m_gamebord.setPreviewLine(bot.removeCard(i), j);
                            poser = true;
                        }
                        j++;
                    }
                }
            }


        }
    }

    public void playerTurn(PlayerLogic player) {
        boolean hasDrawn = false;
        boolean turnOver = false;

        while (!turnOver) {
            String action = "\nActions possibles :";
            if (!hasDrawn && !m_stack.isEmpty()) {
                action += "\n  [piocher] Piocher une carte";
            }
            action += "\n  [placer <numero carte> <position (1-4)>] Placer une carte";
            action += "\n  [fin] Terminer votre tour";

            String input = Ask.Demande(action).trim();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "piocher" :
                    if (hasDrawn) {
                        System.out.println("Vous avez déjà pioché ce tour !");
                    } else {
                        drawCard(player);
                        hasDrawn = true;
                        System.out.println("Carte piochée !");
                    }
                    break;

                case "placer" :
                    if (parts.length != 3) {
                        System.out.println("Format invalide. Exemple : placer 1 3");
                        break;
                    }
                    try {
                        int indexCarte = Integer.parseInt(parts[1]) - 1;
                        int position = Integer.parseInt(parts[2]) - 1;
                        placeCard(player, indexCarte, position);
                        System.out.println("Carte placée !");
                    } catch (NumberFormatException e) {
                        System.out.println("Format invalide. Exemple : placer 1 3");
                    }
                    break;

                case "fin" :
                    turnOver = true;
                    break;

                default :
                    System.out.println("Commande inconnue.");
                    break;
            }
        }
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
