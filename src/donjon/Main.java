package donjon;

import donjon.personnage.Personnage;
import javamysql.JavaMySql;

import java.util.Scanner;

/**
 * Where the game start to run
 */
public class Main {
    static boolean continuer = true;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (continuer) {
            JavaMySql javaDB = new JavaMySql();
            //Initialise le donjon
            Game game = new Game(scanner);
            // Lance la création d'un nouveau personnage ou permet de quitter
            Menu newPerso = new Menu(scanner, game, javaDB);
            newPerso.start();
            Personnage player = game.run();
            if (player.isAlive()) {
                javaDB.savePerso(player, scanner);
            }
        }
        scanner.close();
    }
}