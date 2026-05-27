package Logic;

import java.util.Scanner;

public class Ask {
    public static String Demande(String phrase){
        Scanner scanner = new Scanner(System.in);

        // 3. On affiche la petite phrase devant (sans saut de ligne avec print)
        System.out.print(phrase + " : ");

        // 4. On récupère ce que l'utilisateur écrit (jusqu'à ce qu'il appuie sur Entrée)
        return scanner.nextLine();
    }
}
