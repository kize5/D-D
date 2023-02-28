package donjon;

import donjon.board.Board;
import donjon.personnage.Personnage;
import donjon.AllTalk.*;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

/**
 * Where the logic of the game is created
 */
public class Game {
    private final int BoardCases;
    private final Scanner scanner;

    public static int playerPose;
    Personnage player;
    public boolean leaveGame;
    Board board;

    /**
     * init a new game
     */
    public Game(Scanner scanner) {
        this.scanner = scanner;
        BoardCases = 64;
        playerPose = 0;
        leaveGame = false;
        board = new Board();
    }


    /**
     * Set a new player
     */
    public void setPlayer(Personnage newP) {
        player = newP;
    }

    /**
     * Main method, call the other to make player move and win or not
     */
    public Personnage run() {
        //Break infint loop of continuer to end this game
        if (leaveGame) {
            Main.continuer = false;
            return player;
        }
        //Entrance
        AllTalk.entrence(scanner);
        //Player turn
        playerMove();
        //Win or lose ?
        if (player.isAlive()) {
            win();
        }
        return player;
    }

    /**
     * Allow player to move on board by using rollDice() method
     */
    private void playerMove() {
        while (playerPose != BoardCases && playerPose < 64) {
            if (player.isAlive()) {
                slowPrint("Appuie sur 'entrer' pour lancer les dés \n");
                String go = scanner.nextLine(); // Read user input
                if (Objects.equals(go, "")) {
                    rollDice();
                    checkCase();
                    if (!(player.isAlive())) {
                        justwaitASec();
                        slowPrint(drawRip());
                        slowPrint("Ha bas c'est con, t'es mort par terre ... \n");
                        return;
                    }
                }
                justwaitASec();
            }
        }
//        try {
//            if (playerPose > 64) {
//                throw new IllegalStateException("Player position cannot be greater than 64");
//            }
//        } catch (Exception e) {
//            slowPrint(e + " : Huge bug btw", 30);
//        }
    }

    public void checkCase() {
        board.leBoard.get(playerPose).apply(player, playerPose, scanner);
    }


    /**
     * Roll the dice to make player move
     */
    private void rollDice() {
//        Random r = new Random();
//        int random = r.nextInt((6 - 1) + 1) + 1;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        slowPrint("Tu as fait un " + randomNum + " \n");
        playerPose += randomNum;
        if (playerPose < 64) {
            slowPrint("Tu es dans la salle " + playerPose + " sur les " + BoardCases + " de ce donjon \n");
        }
        if (playerPose >= 64) {
            playerPose = 64;
            slowPrint("Tu as cru pouvoir quitter ce donjon sans combattre le boss ? *rire diabolique* Jamais ! \n");
        }
    }

    /**
     * Output for victory or defeat
     */
    private void win() {
        justwaitASec();
        slowPrintForAscii(drawGG());
        justwaitASec();
        slowPrint("(づ｡◕‿‿◕｡)づ ***--^^-- GG --^^--*** (づ｡◕‿‿◕｡)づ \n");
    }

    /**
     * Gestion de l'entrée du donjon
     */

    private void entrance() {
        justwaitASec();
        slowPrintForAscii(WaitSecAndASCII.drawDungon());
        justwaitASec();
        slowPrint("Te voilà face à un terrifiant donjon, de nuit, il pleut, tu es mouillé, il fait froid, tu es fatigué, tu entends les loups crier au loin, bref, bonne ambiance ... \n");
        justwaitASec();
        slowPrint("La porte du donjon s'ouvre d'elle même sous tes yeux, pour entrer, appuie sur A \n");
        slowPrint("Pour chercher une autre ouverture appuie sur 'B' \n");
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "a")) {
            justwaitASec();
            slowPrint("Tu avances et passe la massive porte de bois, rien, juste un grand hall, personne n'est là, c'est bon signe ça ? \n");
            justwaitASec();
            slowPrint("Mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce sombre devant toi. \n");
        } else {
            justwaitASec();
            slowPrint("Tu te faufiles sur le côté du donjon, et trouve une petite ouverture, tu t'y glisses, il fait noir, tu rampes dans une matière inconnue, mais ça sent mauvais, très mauvais, c'est sûrement les égouts du donjon ... \n");
            justwaitASec();
            slowPrint("Après un petit temps, tu sors enfin dans une petite pièce, ouvre la porte de cette dernière, qui donne sur ... le hall d'entré, dont la porte est toujours ouverte pour toi visiblement. \n");
            justwaitASec();
            slowPrint("Tu sens franchement mauvais, mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce face à toi. \n");
        }
    }
}
