package donjon;

import donjon.personnage.Personnage;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.justwaitASec;
import static donjon.WaitSecAndASCII.slowPrint;

/**
 * Where the logic of the game is created
 */
public class Game {
    private final int BoardCases;
    private final Scanner scanner;
    private int PlayerPose;
    public boolean leave;
    Personnage player;

    /**
     * init a new game
      */

    public Game(Scanner scanner) {
        this.scanner = scanner;
        BoardCases = 64;
        PlayerPose = 1;
        leave = false;
//        slowPrint();("Tape 'a' puis 'entrée' pour recommencer");
//        slowPrint();("Tape 'exit' pour quitter");
//        String go = scanner.nextLine();  // Read user input
//        while (Objects.equals(go, "a")) {

//        }
//        if (Objects.equals(go, "exit")) {
//            donjon.Menu newp = new donjon.Menu(scanner);
//            newp.leave();
//        } else {
//            slowPrint();("Saise incorrecte (game)");
//            donjon.Menu newp = new donjon.Menu(scanner);
//            newp.createPersonnage();
//        }
    }

    /**
     * Set a new player
     */
    public void setPlayer(Personnage newP) {
        player = newP;
//        slowPrint();(newP);
    }


    /**
     * Main method, call the other to make player move and win or not
     */
    public void run() {
        //Break infint loop of continuer to end this game
        if (leave) {
            Main.continuer = false;
            return;
        }
        //Entrance
        entrance();
        //Player turn
        playerMove();
        //Win or lose ?
        winOrRip();
    }

    /**
     * Allow payer to move on board by using rollDice() method
     */
    private void playerMove() {
        while (PlayerPose != BoardCases && PlayerPose < 64) {
            rollDice();
            justwaitASec(250);
            slowPrint(PlayerPose + " / " + BoardCases + " \n", 30);
        }
        try {
            if (PlayerPose > 64) {
                throw new IllegalStateException("Player position cannot be greater than 64");
            }
        }
        catch(Exception e) {slowPrint(e + " : Huge bug btw", 30);
        }
    }


    /**
     * Roll the dice to make player move
     */
    private void rollDice() {
//        Random r = new Random();
//        int random = r.nextInt((6 - 1) + 1) + 1;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        PlayerPose += randomNum;
        if (PlayerPose >= 64) {
            PlayerPose = 64;
        }
    }

    /**
     * Output for victory or defeat
      */
    private void winOrRip() {
        justwaitASec(300);
        slowPrint("Bien joué, tu as survécu au donjon (J'y croyais pas au début .. \n)", 30);
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
        }
        else  {
            justwaitASec(1000);
            slowPrint("Tu te faufiles sur le côté du donjon, et trouve une petite ouverture, tu t'y glisses, il fait noir, tu rampes dans une matière inconnue, mais ça sent mauvais, très mauvais, c'est sûrement les égouts du donjon ... \n", 30);
            justwaitASec(3000);
            slowPrint("Après un petit temps, tu sors enfin dans une petite pièce, ouvre la porte de cette dernière, qui donne sur ... le hall d'entré, dont la porte est toujours ouverte pour toi visiblement. \n", 30);
            justwaitASec(3000);
            slowPrint("Tu sens franchement mauvais, mais tu es un aventurier, c'est un donjon, alors en route ! Tu avances dans la pièce sombre devant toi. \n", 30);
            }
    }
}
