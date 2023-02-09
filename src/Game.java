
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Game {
    int BoardCases;
    int PlayerPose;
    public boolean leave;
    Personnage player;

    public Game(Scanner scanner) {
        BoardCases = 64;
        PlayerPose = 1;
        leave = false;
//        System.out.println("Tape 'a' puis 'entrée' pour recommencer");
//        System.out.println("Tape 'exit' pour quitter");
//        String go = scanner.nextLine();  // Read user input
//        while (Objects.equals(go, "a")) {

//        }
//        if (Objects.equals(go, "exit")) {
//            Menu newp = new Menu(scanner);
//            newp.leave();
//        } else {
//            System.out.println("Saise incorrecte (game)");
//            Menu newp = new Menu(scanner);
//            newp.createPersonnage();
//        }
    }

    public void setPlayer (Personnage newP) {
        player = newP;
//        System.out.println(newP);
    }
    
    public void run(Scanner scanner) {
        if (leave)
        {
            Main.continuer = false;
            return;
        }
        //Fait jouer le personnage
        playerMove();
        //Gère victoire ou défaite
        winOrRip();
    }

    public void playerMove() {
        while (PlayerPose != BoardCases && PlayerPose < 64) {
            rollDice();
            System.out.println(PlayerPose + " / " + BoardCases);
        }
    }

    public void rollDice() {
//        Random r = new Random();
//        int random = r.nextInt((6 - 1) + 1) + 1;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        PlayerPose += randomNum;
    }

    public void winOrRip () {
        System.out.println("Bien joué, tu es survécu au donjon");
        System.out.println("-------GG-------");
    }
}
