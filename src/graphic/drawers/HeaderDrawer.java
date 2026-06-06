package graphic.drawers;

public class HeaderDrawer
{
    public static void showRoundAndTurn(int round, int turn, int score)
    {
        String info = "Partie n° " + round + "   |   Tour n° " + turn + "   |   Score : " + score;

        // Largeur totale fixe pour aligner le bandeau avec le reste du plateau graphique
        int totalWidth = 59;

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║" + StackDrawer.center(info, totalWidth) + "║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}