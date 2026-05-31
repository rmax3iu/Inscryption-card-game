package logic.gameLogic;

import logic.cardLogic.*;
import logic.cardLogic.powers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StackLogic {
    //Liste avec le nom de toutes les cartes du jeu
    public static final String[] TOUS_LES_NOMS_CARTES = {
            // --- Cartes de la phase 1 ---
            "Chat",
            "Grizzly",
            "Coyote",
            "Moineau",
            "Corbeau",
            "Ecureuil",
            "Hermine",
            "Louveteau",
            "Loup",
            "Punaise",

            // --- Nouvelles cartes de la phase 2 ---
            "Elan",
            "Vipère",
            "Porc-épic"
    };

    //Une méthode qui permet de créer n'importe qu'elle carte du jeu si on a son nom
    public static CardLogic allCarde(String nom) {
        CardLogic carteDemande = null;
        switch (nom) {
            // --- Cartes de la phase 1 (avec leurs pouvoirs) ---
            case "Chat":
                carteDemande = new TerrestrialLogic("Chat", 1, 0, SummonCostLogic.newBloodCost(1), new ManyLife());
                break;
            case "Grizzly":
                carteDemande = new TerrestrialLogic("Grizzly", 6, 4, SummonCostLogic.newBloodCost(3));
                break;
            case "Coyote":
                carteDemande = new TerrestrialLogic("Coyote", 1, 2, SummonCostLogic.newBonesCost(4));
                break;
            case "Moineau":
                carteDemande = new FlyingLogic("Moineau", 2, 1, SummonCostLogic.newBloodCost(1));
                break;
            case "Corbeau":
                carteDemande = new FlyingLogic("Corbeau", 3, 2, SummonCostLogic.newBloodCost(2));
                break;
            case "Ecureuil":
                carteDemande = new TerrestrialLogic("Ecureuil", 1, 0, SummonCostLogic.newFree());
                break;
            case "Hermine":
                carteDemande = new TerrestrialLogic("Hermine", 3, 1, SummonCostLogic.newBloodCost(1));
                break;
            case "Louveteau":
                carteDemande = new TerrestrialLogic("Louveteau", 1, 1, SummonCostLogic.newBloodCost(1), new Growth());
                break;
            case "Loup":
                carteDemande = new TerrestrialLogic("Loup", 2, 3, SummonCostLogic.newBloodCost(2));
                break;
            case "Punaise":
                carteDemande = new TerrestrialLogic("Punaise", 2, 1, SummonCostLogic.newBonesCost(2), new Stinking());
                break;

            // --- Nouvelles cartes de la phase 2 ---
            case "Elan":
                carteDemande = new TerrestrialLogic("Elan", 4, 2, SummonCostLogic.newBloodCost(2), new Runner());
                break;
            case "Vipère":
                carteDemande = new TerrestrialLogic("Vipère", 1, 1, SummonCostLogic.newBloodCost(2), new DeadlyContact());
                break;
            case "Porc-épic":
                carteDemande = new TerrestrialLogic("Porc-épic", 2, 1, SummonCostLogic.newBloodCost(1), new SharpSpikes());
                break;
        }
        return carteDemande;
    }

    private List<CardLogic> deck;

    public StackLogic() {
        Random rnd = new Random();      //Permet de donner un nombre aléatoire

        deck = new ArrayList<>();       //La liste de carte dont la pioche est composé

        //On ajoute 7 écureuils et 7 cartes aléatoires
        for (int i = 0; i < 7; i++) {
            deck.add(allCarde(TOUS_LES_NOMS_CARTES[5]));    //Ajout de l'écureuil
            deck.add(allCarde(TOUS_LES_NOMS_CARTES[rnd.nextInt(0, TOUS_LES_NOMS_CARTES.length)]));  //Ajout d'une carte aléatoire
        }

        //Pour l'instant on a que 14(7*2) cartes donc on ajoute un écureuil supplémentaire pour atteindre 15
        deck.add(allCarde(TOUS_LES_NOMS_CARTES[5]));        //Un écureuil

        //Maintenant on mélange les cartes pour pas avoir une carte sur 2 un écureil
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