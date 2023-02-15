package donjon;

import donjon.equipement.Arme;
import donjon.equipement.KindItemOff;
import donjon.personnage.Murloc;
import donjon.personnage.Personnage;

import java.util.ArrayList;
import java.util.List;
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
    private int PlayerPose;
    Personnage player;
    private KindCase typeCase;
    private boolean running;
    private boolean playerAlive;
    public boolean leaveGame;

    List<Case> myCaseList;

    /**
     * init a new game
      */
    public Game(Scanner scanner) {
        this.scanner = scanner;
        BoardCases = 64;
        PlayerPose = 0;
        leaveGame = false;
        running = false;
        playerAlive = false;
        this.myCaseList = new ArrayList<>();
        myCaseList.add(new EmptyCase());
        myCaseList.add(new EnemiCase());
        myCaseList.add(new LootCase());
        myCaseList.add(new EmptyCase());
        myCaseList.add(new EnemiCase());
        myCaseList.add(new LootCase());

//        myCaseList.add(KindCase.Ennemie);
//        myCaseList.add(KindCase.Vide);
//        myCaseList.add(KindCase.Loot);
//        myCaseList.add(KindCase.Ennemie);
//        myCaseList.add(KindCase.Vide);
//        System.out.println(myCaseList.get(1));
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
        winOrRip();
    }

    /**
     * Allow payer to move on board by using rollDice() method
     */
    private void playerMove() {
        while (PlayerPose != BoardCases && PlayerPose < 64) {
            rollDice();
            checkCase();
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

    public void checkCase() {
        System.out.println("check case implement");
        myCaseList.get(PlayerPose).apply(player);
//        if ( == KindCase.Loot) {
//            new LootCase(player);
//        }
//        if (myCaseList.get(PlayerPose) == KindCase.Ennemie) {
//            new EnemiCase(player);
//        }
//        if (myCaseList.get(PlayerPose) == KindCase.Vide) {
//            new EmptyCase();
//        }

//        switch (typeCase) {
//            case Loot -> new LootCase(player);
//            case Ennemie -> System.out.println("enemy");
//            case Vide -> System.out.println("safe");
//        }
    }

    private void fight() {
    }
    private void loot() {
    }
    private void safe() {
    }

    /**
     * Roll the dice to make player move
     */
    private void rollDice() {
//        Random r = new Random();
//        int random = r.nextInt((6 - 1) + 1) + 1;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        PlayerPose += 1;
        if (PlayerPose >= 64) {
            PlayerPose = 64;
        }
    }

    /**
     * Output for victory or defeat
      */
    private void winOrRip() {
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
