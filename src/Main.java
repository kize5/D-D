import java.util.Scanner;

public class Main {

        public static void main (String [] args) {
            Scanner scanner = new Scanner(System.in);
            Menu newPerso = new Menu(scanner);
            newPerso.start();
            scanner.close();
    }
}