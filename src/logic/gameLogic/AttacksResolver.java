package logic.gameLogic;

import logic.cardLogic.CardLogic;
import logic.cardLogic.powers.Power;

import java.util.Optional;

public class AttacksResolver
{
    public AttacksResolver()
    {
    }

    public int resolveAll(GameBoardLogic board)
    {
        int score = 0;
        moveBotCards(board);        // On fait avancer les cartes de la ligne preview sur la ligne bot

        // Les cartes de playerLine attaquent celles de botLine et on modifie le score en conséquence
        score += resolvePlayerAttacks(board);
        removeDeadBotCards(board);          // Retire les cartes tuées par les cartes du joueur
        removeDeadPlayerCards(board);       // Retire les cartes tuées par les cartes du bot (si jamais la carte qu'on attaque a le pouvoir pique pointue)

        // Les cartes de botLine attaquent celles de playerLine et on modifie le score en conséquence
        score += resolveBotAttacks(board);
        removeDeadPlayerCards(board);       // Retire les cartes tuées par les cartes du bot
        removeDeadBotCards(board);          // Retire les cartes tuées par les cartes du player (si jamais la carte qu'on attaque a le pouvoir pique pointue)

        return score;
    }

    private int resolvePlayerAttacks(GameBoardLogic board)
    {
        int score = 0;
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardPlayer = board.getPlayerLine(index);
            Optional<CardLogic> cardBot = board.getBotLine(index);
            if (cardPlayer.isPresent())
            {             // On vérifie que la carte n'est pas vide
                score += cardPlayer.get().attack(cardBot);          // Renvoie l'impact sur le score ou attaque la carte en face s'il y en a une et que ce n'est pas un animal volant
            }
        }
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardPlayer = board.getPlayerLine(index);
            if (cardPlayer.isPresent())
            {             // On vérifie que la carte n'est pas vide
                Optional<Power> power = cardPlayer.get().getPower();    // On récupère son pouvoir
                if (power.isPresent())
                {         // On vérifie qu'elle en a un
                    // On initialise la carte de gauche et de droite à vide
                    Optional<CardLogic> cardRight = Optional.empty();
                    Optional<CardLogic> cardLeft = Optional.empty();

                    // On les remplace par les vraies cartes si c'est possible (donc pas dans les coins)
                    if (index < GameBoardLogic.BOARD_SIZE - 1)
                    {
                        cardRight = board.getPlayerLine(index + 1);
                    }
                    if (index > 0)
                    {
                        cardLeft = board.getPlayerLine(index - 1);
                    }

                    // On récupère la nouvelle position de la carte
                    int newIndex = power.get().onAttack(index, cardLeft, cardRight);
                    // On la déplace
                    board.setPlayerLine(newIndex, board.removePlayerLine(index));

                    if (newIndex > index)
                    {
                        index++;        // Au cas où la carte est allée à droite, on ne repasse pas dessus à la prochaine boucle
                    }
                }
            }
        }
        return score;
    }

    private int resolveBotAttacks(GameBoardLogic board)
    {
        int score = 0;
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardBot = board.getBotLine(index);
            Optional<CardLogic> cardPlayer = board.getPlayerLine(index);
            if (cardBot.isPresent())
            {             // On vérifie que la carte n'est pas vide
                score -= cardBot.get().attack(cardPlayer);          // Renvoie l'impact sur le score ou attaque la carte en face s'il y en a une et que ce n'est pas un animal volant
            }
        }
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardBot = board.getBotLine(index);
            if (cardBot.isPresent())
            {             // On vérifie que la carte n'est pas vide
                Optional<Power> power = cardBot.get().getPower();
                if (power.isPresent())
                {
                    Optional<CardLogic> cardRight = Optional.empty();
                    Optional<CardLogic> cardLeft = Optional.empty();

                    if (index < GameBoardLogic.BOARD_SIZE - 1)
                    {
                        cardRight = board.getBotLine(index + 1);
                    }
                    if (index > 0)
                    {
                        cardLeft = board.getBotLine(index - 1);
                    }
                    int newIndex = power.get().onAttack(index, cardLeft, cardRight);

                    board.setBotLine(newIndex, board.removeBotLine(index));

                    if (newIndex > index)
                    {
                        index++;        // Au cas où la carte est allée à droite, on ne repasse pas dessus à la prochaine boucle
                    }
                }
            }
        }
        return score;
    }

    private void moveBotCards(GameBoardLogic board)
    {
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardPreview = board.getPreviewLine(index);
            Optional<CardLogic> cardBot = board.getBotLine(index);
            // Donc s'il y a une carte dans la preview line et pas de carte dans la bot line
            if (cardPreview.isPresent() && cardBot.isEmpty())
            {
                board.setBotLine(index, cardPreview);
                board.removePreviewLine(index);
            }
        }
    }

    private void removeDeadBotCards(GameBoardLogic board)
    {
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardBot = board.getBotLine(index);
            if (cardBot.isPresent() && cardBot.get().isDead())
            {
                board.setBotLine(index, Optional.empty());
            }
        }
    }

    private void removeDeadPlayerCards(GameBoardLogic board)
    {
        for (int index = 0; index < GameBoardLogic.BOARD_SIZE; index++)
        {
            Optional<CardLogic> cardPlayer = board.getPlayerLine(index);
            if (cardPlayer.isPresent() && cardPlayer.get().isDead())
            {
                board.setPlayerLine(index, Optional.empty());
            }
        }
    }
}