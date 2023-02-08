import java.util.Objects;
import java.util.Scanner;
public class Menu {

    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    private String inputClass () {
        System.out.println("Mage ou War");
        String classe = scanner.nextLine();  // Read user input
        System.out.println("Oh tiens, un " + classe + " ça faisait longtemps !");  // Output user input
        return classe;
    }

    private String inputName () {
        System.out.println("Quel est ton nom : ");
        String nom = scanner.nextLine();  // Read user input
        System.out.println(nom + " je vois... sacré nom ça " + "'" +nom+"'");  // Output user input
        return nom;
    }

    public void start () {
        System.out.println("Press a pour commencer");
        System.out.println("Press b pour quitter");
        String go = scanner.nextLine();  // Read user input
        if (Objects.equals(go, "a")) {
            System.out.println("Alors c'est partie, créons ton perso");
            createPersonnage();
        } else {
            System.out.println("OK...");
        }
    }

    public Personnage createPersonnage(){
        String name = this.inputName();
        String pClass = this.inputClass();
        Personnage newP = new Personnage(name,pClass);
        return newP;
    }
}
