import java.util.Objects;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final Game game;

    public Menu(Scanner scanner, Game game) {
        this.scanner = scanner;
        this.game = game;
    }

    private KindClass inputClass() {
        System.out.println("Mage ou War");
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "war") || Objects.equals(input, "War")) {
            System.out.println("Oh tiens, un " + input + " ça faisait longtemps !");  // Output user input
            return KindClass.War;
        }
        if (Objects.equals(input, "mage") || Objects.equals(input, "Mage")) {
            System.out.println("Oh tiens, un " + input + " ça faisait longtemps !");  // Output user input
            return KindClass.Mage;
        }
        if (Objects.equals(input, "exit")) {
            leave();
        }
        System.out.println("Ha ! Tu ne sais pas écrire mage ou war, tu dois donc être un murloc");  // Output user input
        return KindClass.Murloc;
    }

    private String inputName() {
        System.out.println("Quel est ton nom : ");
        String nom = scanner.nextLine();  // Read user input
        if (Objects.equals(nom, "exit")) {
            leave();
        }
        System.out.println(nom + " je vois... sacré nom ça " + "'" + nom + "'");  // Output user input
        return nom;
    }

    public Object start() {
        System.out.println("Tape 'a' puis 'entrée' pour entrer dans un nouveau Donjon");
        System.out.println("Tape 'exit' pour quitter à tout moment");
        String go = scanner.nextLine();  // Read user input
        if (Objects.equals(go, "a")) {
            System.out.println("Alors c'est partie, créons ton perso");
            return createPersonnage();
        }
        if (Objects.equals(go, "exit")) {
            return leave();
        } else {
            System.out.println("Saise incorrecte(menu)");
            return start();
        }
    }

    public Personnage createPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        Personnage newP = new Personnage(name, pClass);
        System.out.println(newP.nom);
        System.out.println(newP.type);
        System.out.println(newP.hp);
        System.out.println(newP.atk);
        System.out.println("Si tu es satisfait de ton personnage appuie sur a, sinon appuie sur b");
        String happy = scanner.nextLine();

        if (Objects.equals(happy, "a")) {
            this.game.setPlayer(newP);
            return newP;
        } else {
            return modifyPersonnage();
        }
    }

    public Personnage modifyPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        System.out.println("Combien de hp veux tu ? : ");
        int health = Integer.parseInt(scanner.nextLine());
        System.out.println("A combien s'élève ton attaque ? : ");
        int atk = Integer.parseInt(scanner.nextLine());
        Personnage newPmod = new Personnage(name, pClass);
        newPmod.atk = atk;
        newPmod.hp = health;
        System.out.println(newPmod.nom);
        System.out.println(newPmod.type);
        System.out.println(newPmod.hp);
        System.out.println(newPmod.atk);
        this.game.setPlayer(newPmod);
        return newPmod;
    }

    protected Object leave() {
        System.out.println("Tu es donc un lâche... Adieu");
        return game.leave = true;
    }
}
