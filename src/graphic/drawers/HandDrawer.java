package graphic.drawers;

import logic.actorLogic.ActorLogic;
import logic.cardLogic.CardLogic;
import logic.cardLogic.AnimalLogic;

public class HandDrawer
{
    // On déclare la méthode pour afficher la main du joueur
    public static void showHand(ActorLogic player)
    {
        System.out.println("Votre main :");

        // On ajoute la ligne dédiée pour afficher le nombre d'os disponibles
        System.out.println("     Nombre d'os disponibles : " + player.getBones());

        // On fait une boucle pour parcourir toutes les cartes dans la main du joueur
        for (int i = 0; i < player.handSize(); i++)
        {
            // On récupère la carte
            CardLogic card = player.getCard(i);

            // On prépare les variables
            int attack = 0;
            int bloodCost = 0;
            int bonesCost = 0;

            // Si la carte est un animal
            if (card.canBeSacrify())
            {
                // On peut extraire les données spécifiques de l'animal
                AnimalLogic animal = (AnimalLogic) card;
                attack = animal.getAttack();
                bloodCost = animal.getSummonCost().getBlood();
                bonesCost = animal.getSummonCost().getBones();
            }

            // On prépare la chaîne pour le pouvoir si la carte en possède un
            String powerStr = "";
            if (card.hasPower())
            {
                powerStr = "  [" + card.getPower().get().getName() + "]";
            }

            System.out.println("     " + (i + 1) + ". " + card.getName()
                    + "  PV: " + card.getHp()
                    + "  Att: " + attack
                    + "  Gouttes de sang: " + bloodCost
                    + "  Os : " + bonesCost
                    + powerStr);
        }
        System.out.println("");
    }
}