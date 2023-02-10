package donjon;

import donjon.personnage.Mage;
import donjon.personnage.Murloc;
import donjon.personnage.Personnage;
import donjon.personnage.War;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Menu {
    private final Scanner scanner;
    private final Game game;

    public Menu(Scanner scanner, Game game) {
        this.scanner = scanner;
        this.game = game;
    }

    //Choose hero class
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
        System.out.println("Ha ! Tu ne sais pas écrire mage ou war ... Tu dois donc être un murloc");  // Output user input
        return KindClass.Murloc;
    }

    //Create hero name
    private String inputName() {
        System.out.println("Quel est ton nom : ");
        String nom = scanner.nextLine();  // Read user input
        if (Objects.equals(nom, "exit")) {
            leave();
        }
        System.out.println(nom + " je vois... sacré nom ça " + "'" + nom + "'");  // Output user input
        return nom;
    }

    // Enter of this game
    public void start() {
        System.out.println("Tape 'a' puis 'entrée' pour entrer dans un nouveau Donjon");
        System.out.println("Tape 'exit' pour quitter à tout moment");
        String go = scanner.nextLine();  // Read user input
        if (Objects.equals(go, "a")) {
            System.out.println("Alors c'est partie, créons ton perso");
            createPersonnage();
            return;
        }
        if (Objects.equals(go, "exit")) {
            leave();
        } else {
            System.out.println("Saisie incorrecte(menu)");
            start();
        }
    }

    //Create your hero here
    public void createPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        //random method for class and hp here
        int hp = generateRandomHP(pClass);
        int atk = generateRandomAtk(pClass);
        Personnage newP = chooseClass(pClass, name, hp, atk);
        welcomeNewHero(newP);
//        if (pClass == donjon.KindClass.donjon.personnage.Mage ) {
//            donjon.personnage.Mage newP = new donjon.personnage.Mage(name, pClass);
//            welcomeNewHero(newP);
//        }
//        if (pClass == donjon.KindClass.donjon.personnage.War ) {
//            donjon.personnage.War newP = new donjon.personnage.War(name, pClass);
//            welcomeNewHero(newP);
//        }
//        if (pClass == donjon.KindClass.donjon.personnage.Murloc ) {
//            donjon.personnage.Murloc newP = new donjon.personnage.Murloc(name, pClass);
//            welcomeNewHero(newP);
//        }
//        donjon.personnage.Personnage newP = new donjon.personnage.Personnage(name, pClass);

    }

    //Create hero by class
    public Personnage chooseClass(KindClass pClass, String name, int hp, int atk) {
        if (pClass == KindClass.Mage) {
            return new Mage(name, pClass, hp, atk);
        }
        if (pClass == KindClass.War) {
            return new War(name, pClass, hp, atk);
        }
        return new Murloc(name, pClass, hp, atk);
    }


    //Welcome to the new hero and allow reset hero
    public void welcomeNewHero(Personnage newP) {
        System.out.println("Bienvenue à toi " + newP.getNom());
        System.out.println("Tu es donc un " + newP.getType());
        System.out.println("Tu possèdes *" + newP.getHp() + "* point de vie");
        System.out.println("Et ton score d'attaque est de *" + newP.getAtk() + "*");
        System.out.println("Si tu es satisfait de ton personnage appuie sur a, sinon appuie sur b");
        String happy = scanner.nextLine();
        if (Objects.equals(happy, "a")) {
            this.game.setPlayer(newP);
        } else {
            modifyPersonnage();
            // modifyPersonnage() implementation modif stat à revoir
        }
    }

    //Modify hero and maybe make it OP if not satisfy by classique choice
    public void modifyPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        System.out.println("Combien de hp veux tu ? : ");
        int health = Integer.parseInt(scanner.nextLine());
        System.out.println("A combien s'élève ton attaque ? : ");
        int atk = Integer.parseInt(scanner.nextLine());
        Personnage newPmod = chooseClass(pClass, name, health, atk);
        welcomeNewHero(newPmod);
//        donjon.personnage.Personnage newPmod = new donjon.personnage.Personnage(name, pClass);
//        newPmod.atk = atk;
//        newPmod.hp = health;
//        System.out.println("Bienvenue à toi " + newPmod.nom);
//        System.out.println("Tu es donc un " + newPmod.type);
//        System.out.println("Tu possèdes *" + newPmod.hp + "* point de vie");
//        System.out.println("Et ton score d'attaque est de *" + newPmod.atk +"*");
//        this.game.setPlayer(newPmod);
    }

    public int generateRandomHP(KindClass pClass) {
        if (pClass == KindClass.Mage) {
            return ThreadLocalRandom.current().nextInt(6 + 1 - 3) + 3;
        }
        if (pClass == KindClass.War) {
            return ThreadLocalRandom.current().nextInt(10 + 1 - 5) + 5;
        }
        return 1;
    }

    public int generateRandomAtk(KindClass pClass) {
        if (pClass == KindClass.Mage) {
            return ThreadLocalRandom.current().nextInt(15 + 1 - 8) + 8;
        }
        if (pClass == KindClass.War) {
            return ThreadLocalRandom.current().nextInt(10 + 1 - 5) + 5;
        }
        return 1;
    }

    //Allow user to leave game
    protected void leave() {
        System.out.println("Tu es donc un lâche... Adieu (Mais merci quand même d'avoir joué !)");
        game.leave = true;
    }
}
