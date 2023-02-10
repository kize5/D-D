package donjon;

import donjon.personnage.Personnage;

import java.util.concurrent.ThreadLocalRandom;


public class Game {
    private final int BoardCases;
    private int PlayerPose;
    public boolean leave;
    Personnage player;

    // init a new game
    public Game() {
        BoardCases = 64;
        PlayerPose = 1;
        leave = false;
//        System.out.println("Tape 'a' puis 'entrée' pour recommencer");
//        System.out.println("Tape 'exit' pour quitter");
//        String go = scanner.nextLine();  // Read user input
//        while (Objects.equals(go, "a")) {

//        }
//        if (Objects.equals(go, "exit")) {
//            donjon.Menu newp = new donjon.Menu(scanner);
//            newp.leave();
//        } else {
//            System.out.println("Saise incorrecte (game)");
//            donjon.Menu newp = new donjon.Menu(scanner);
//            newp.createPersonnage();
//        }
    }

    //Set a new player
    public void setPlayer (Personnage newP) {
        player = newP;
//        System.out.println(newP);
    }


    public void run() {
        //Break infint loop of continuer to end this game
        if (leave)
        {Main.continuer = false; return;}
        //Player turn
        playerMove();
        //Win or lose ?
        winOrRip();
    }

    // Allow payer to move on board by using rollDice() method
    public void playerMove(){
        while (PlayerPose != BoardCases && PlayerPose < 64) {
            rollDice();
            System.out.println(PlayerPose + " / " + BoardCases);
        }
//        if (PlayerPose > 64) {
//            throw new IllegalStateException("Player position cannot be greater than 64");
//        }
//    }
    }

    //Roll the dice
    public void rollDice() {
//        Random r = new Random();
//        int random = r.nextInt((6 - 1) + 1) + 1;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        PlayerPose += randomNum;
        if (PlayerPose >= 64) {
            PlayerPose = 64;
        }
    }

    // Victory or defeat
    public void winOrRip () {
        System.out.println("Bien joué, tu es survécu au donjon");
        System.out.println("-------GG-------");
    }
}
