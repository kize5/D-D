package donjon;

import donjon.board.Board;
import donjon.personnage.Personnage;

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
    private boolean running;
    private boolean playerAlive;
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
        running = false;
        playerAlive = false;
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
    public void run() {
        //Break infint loop of continuer to end this game
        if (leaveGame) {
            Main.continuer = false;
            return;
        }
        //Entrance
        entrance();
        //Player turn
        playerMove();
        //Win or lose ?
        if (player.isAlive()) {
            win();
        }
    }

    /**
     * Allow payer to move on board by using rollDice() method
     */
    private void playerMove() {
        while (playerPose != BoardCases && playerPose < 64) {
            if (player.isAlive()) {
                slowPrint("Appuie sur 'entrer' pour lancer les dés \n", 20);
                String go = scanner.nextLine(); // Read user input
                if (Objects.equals(go, "")) {
                    rollDice();
                    checkCase();
                }
                justwaitASec(250);
            } else {slowPrint(drawRip(),3);
                    slowPrint("Ha bas c'est con, t'es mort par terre ...", 30);
                    return;
            }
        }
        try {
            if (playerPose > 64) {
                throw new IllegalStateException("Player position cannot be greater than 64");
            }
        } catch (Exception e) {
            slowPrint(e + " : Huge bug btw", 30);
        }
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
        slowPrint("Tu as fait un " + randomNum + " \n",20);
        playerPose += randomNum;
        if (playerPose < 64) {
            slowPrint("Tu es dans la salle " + playerPose + " sur les " + BoardCases + " de ce donjon \n", 30);
        }
        if (playerPose >= 64) {
            playerPose = 64;
            slowPrint("Tu as cru pouvoir quitter ce donjon sans combattre le boss ? *rire diabolique* Jamais ! \n",30);
        }
    }

    /**
     * Output for victory or defeat
     */
    private void win() {
        justwaitASec(300);
        slowPrint(drawGG(), 3);
        justwaitASec(500);
        slowPrint("(づ｡◕‿‿◕｡)づ ***--^^-- GG --^^--*** (づ｡◕‿‿◕｡)づ \n", 30);
    }

    /**
     * Gestion de l'entrée du donjon
     */

    private void entrance() {
        justwaitASec(1000);
        slowPrint(WaitSecAndASCII.drawDungon(), 3);
        justwaitASec(2500);
        slowPrint("Te voilà face à un terrifiant donjon, de nuit, il pleut, tu es mouillé, il fait froid, tu es fatigué, tu entends les loups crier au loin, bref, bonne ambiance ... \n", 30);
        justwaitASec(3000);
        slowPrint("La porte du donjon s'ouvre d'elle même sous tes yeux, pour entrer, appuie sur A \n", 30);
        slowPrint("Pour chercher une autre ouverture appuie sur 'B' \n", 30);
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "a")) {
            justwaitASec(1000);
            slowPrint("Tu avances et passe la massive porte de bois, rien, juste un grand hall, personne n'est là, c'est bon signe ça ? \n", 30);
            justwaitASec(2000);
            slowPrint("Mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce sombre devant toi. \n", 30);
        } else {
            justwaitASec(1000);
            slowPrint("Tu te faufiles sur le côté du donjon, et trouve une petite ouverture, tu t'y glisses, il fait noir, tu rampes dans une matière inconnue, mais ça sent mauvais, très mauvais, c'est sûrement les égouts du donjon ... \n", 30);
            justwaitASec(3000);
            slowPrint("Après un petit temps, tu sors enfin dans une petite pièce, ouvre la porte de cette dernière, qui donne sur ... le hall d'entré, dont la porte est toujours ouverte pour toi visiblement. \n", 30);
            justwaitASec(3000);
            slowPrint("Tu sens franchement mauvais, mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce face à toi. \n", 30);
        }
    }

    public int getPlayerPose() {
        return playerPose;
    }

    public void setPlayerPose(int playerPose) {
        this.playerPose = playerPose;
    }
}
