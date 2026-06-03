package logic.actorLogic;

import logic.cardLogic.AnimalLogic;

import java.util.ArrayList;
import java.util.List;

public class ActorLogic {
    //2 String pour le nom soit Player soit Bot
    public static final String PLAYER = "Player";
    public static final String BOT = "Bot";

    private List<AnimalLogic> m_hand;     //Liste de carte, c'est la main de l'acteur
    private int m_bones;                //Le nombre d'os de l'acteur
    private String m_name;

    private ActorLogic(String name) {
        m_hand = new ArrayList<>();
        m_bones = 0;
        m_name = name;
    }

    public static ActorLogic newBotLogic(){
        return new ActorLogic(BOT);
    }

    public static ActorLogic newPlayerLogic(){
        return new ActorLogic(PLAYER);
    }

    //Renvoie le nom de l'acteur donc logiquement soit Player soit Bot si c'est bien fait
    public String getName(){
        return m_name;
    }

    //Ajoute une carte dans sa main
    public void addCard(AnimalLogic card) {
        m_hand.add(card);
    }

    //Retire et renvoie une carte de sa main à un indice donné
    public AnimalLogic removeCard(int index) {
        return m_hand.remove(index);
    }

    //Renvoie juste une carte de sa main à un indice donné
    public AnimalLogic getCard(int index) {
        return m_hand.get(index);
    }

    //Renvoie le nombre de carte dans sa main
    public int handSize() {
        return m_hand.size();
    }

    //Renvoie son nombre d'os
    public int getBones() {
        return m_bones;
    }

    //Incrément son nombre d'os
    public void addBones(int amount) {
        this.m_bones += amount;
    }
}