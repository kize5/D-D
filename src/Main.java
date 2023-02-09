import java.util.Scanner;

public class Main {
    static boolean continuer = true;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (continuer) {
            //Initialise le donjon
            Game game = new Game(scanner);
            // Lance la création d'un nouveau personnage ou permet de quitter
            Menu newPerso = new Menu(scanner, game);
            newPerso.start();
            game.run(scanner);
        }
        scanner.close();
    }
}