package logic.actorLogic;

import logic.cardLogic.CardLogic;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class ActorLogic {
    // On définit les deux types d'acteurs possibles pour le jeu : le joueur humain et le bot
    public static final String PLAYER = "Player";
    public static final String BOT = "Bot";

    // On déclare les attributs de l'acteur
    private List<CardLogic> m_hand;
    private int m_bones;
    private String m_name;

    private ActorLogic(String name)
    {
        m_hand = new ArrayList<>();
        m_bones = 0;
        m_name = name;
    }

    // On génère un nouvel acteur configuré spécifiquement comme un bot
    public static ActorLogic newBotLogic(){
        return new ActorLogic(BOT);
    }

    // On génère un nouvel acteur configuré spécifiquement comme un joueur humain
    public static ActorLogic newPlayerLogic(){
        return new ActorLogic(PLAYER);
    }

    // On retourne le nom de l'acteur afin de savoir s'il s'agit du joueur ou du bot
    public String getName(){
        return m_name;
    }

    // On ajoute une nouvelle carte dans la main de l'acteur
    public void addCard(CardLogic card) {
        m_hand.add(card);
    }

    // On retire et on récupère la carte située à un indice précis dans la main
    public CardLogic removeCard(int index) {
        return m_hand.remove(index);
    }

    // On consulte et on renvoie la carte de la main correspondant à l'indice demandé sans la supprimer
    public CardLogic getCard(int index) {
        return m_hand.get(index);
    }

    // On récupère le nombre total de cartes que l'acteur a actuellement en main
    public int handSize() {
        return m_hand.size();
    }

    // On renvoie le nombre total d'os que l'acteur possède
    public int getBones() {
        return m_bones;
    }

    // On augmente le nombre d'os de l'acteur de la valeur reçue en paramètre
    public void addBones(int amount) {
        this.m_bones += amount;
    }
}