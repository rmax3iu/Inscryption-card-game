package logic.gameLogic;

import logic.cardLogic.*;
import logic.cardLogic.powers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StackLogic {
    // --- Cartes de la phase 1 (avec leurs pouvoirs) ---
    public static final CardLogic CHAT = new TerrestrialLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new ManyLife());
    public static final CardLogic GRIZZLY = new TerrestrialLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3));
    public static final CardLogic COYOTE = new TerrestrialLogic("Coyote", 1, 2, SummonCostLogic.newBonesCost(4));
    public static final CardLogic MOINEAU = new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1));
    public static final CardLogic CORBEAU = new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2));
    public static final CardLogic ECUREUIL = new TerrestrialLogic("Ecureuil", 1, 0, SummonCostLogic.newFree());
    public static final CardLogic HERMINE = new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1));
    public static final CardLogic LOUVETEAU = new TerrestrialLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Growth());
    public static final CardLogic LOUP = new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2));
    public static final CardLogic PUNAISE = new TerrestrialLogic("Punaise", 2, 1, SummonCostLogic.newBonesCost(2), new Stinking());
    // --- Nouvelles cartes de la phase 2 ---
    public static final CardLogic ELAN = new TerrestrialLogic("Elan", 4, 2, SummonCostLogic.newBloodCost(2), new Runner());
    public static final CardLogic VIPERE = new TerrestrialLogic("Vipère", 1, 1, SummonCostLogic.newBloodCost(2), new DeadlyContact());
    public static final CardLogic PORC_EPIC = new TerrestrialLogic("Porc-épic", 2, 1, SummonCostLogic.newBloodCost(1), new SharpSpikes());
    public static final int NB_CARD = 13;


    private List<CardLogic> deck;

    public StackLogic() {
        deck = new ArrayList<>();       //La liste de carte dont la pioche est composé

        //On ajoute 7 écureuils et 7 cartes aléatoires
        for (int i = 0; i < 7; i++) {
            deck.add(ECUREUIL.copie());    //Ajout de l'écureuil
            deck.add(randomeCard());       //Ajout d'une carte aléatoire
        }

        //Pour l'instant on a que 14(7*2) cartes donc on ajoute un écureuil supplémentaire pour atteindre 15
        deck.add(ECUREUIL.copie());        //Un écureuil

        //Maintenant on mélange les cartes pour pas avoir une carte sur 2 un écureil
        Random rnd = new Random();      //Permet de donner un nombre aléatoire
        for(int i = 0; i < size(); i++){
            //on choisit 2 cartes aléatoirement
            int indice1 = rnd.nextInt(0, size());
            int indice2 = rnd.nextInt(0, size());

            //On stocke la 1er carte temporairement
            CardLogic tempo = deck.get(indice1);

            //Puis on les échange de place
            deck.set(indice1, deck.get(indice2));
            deck.set(indice2, tempo);
        }
    }

    public CardLogic randomeCard(){
        Random rnd = new Random();      //Permet de donner un nombre aléatoire
        //Chaque nombre correspond à une carte
        switch (rnd.nextInt(0,NB_CARD)){
            case 0 :
                return CHAT.copie();
            case 1 :
                return GRIZZLY.copie();
            case 2 :
                return COYOTE.copie();
            case 3 :
                return MOINEAU.copie();
            case 4 :
                return CORBEAU.copie();
            case 5 :
                return ECUREUIL.copie();
            case 6 :
                return HERMINE.copie();
            case 7 :
                return LOUVETEAU.copie();
            case 8 :
                return LOUP.copie();
            case 9 :
                return PUNAISE.copie();
            case 10 :
                return ELAN.copie();
            case 11 :
                return VIPERE.copie();
            case 12 :
                return PORC_EPIC.copie();
        }
        return ECUREUIL.copie();    //Pour qu'il ne râle pas je met un écureuil car c'est pas grave de piocher un écureuil au pire
    }

    //Renvoie un entier qui dit si la pioche est vide
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    //Renvoie le nombre de cartes dans la pioche
    public int size() {
        return deck.size();
    }

    //Renvoie la dernière carte de la liste donc la carte du dessus de la pioche (si la pioche n'est pas vide un peu compliqué sinon)
    public CardLogic draw() {
        if (isEmpty()) {
            throw new IllegalStateException("La pioche est vide !");
        }
        return deck.removeLast();
    }
}