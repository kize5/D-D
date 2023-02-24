package donjon;

import donjon.equipement.itemDef.DefaultDef;
import donjon.equipement.itemOff.*;
import donjon.personnage.*;
import javamysql.JavaMySql;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

/**
 * Where the menu display information and personnage are set up by user
 */
public class Menu {
    private final Scanner scanner;
    private final Game game;
    private JavaMySql javaDB;

    /**
     * Constructor
     *
     * @param scanner Take scanner from main
     * @param game    Take a new game instance from main
     */
    public Menu(Scanner scanner, Game game, JavaMySql javaDB) {
        this.scanner = scanner;
        this.game = game;
        this.javaDB = javaDB;
    }

    /**
     * Choose hero class
     */
    private KindClass inputClass() {
        slowPrint("As tu envie de jouer 'Mage' ou 'War' ? \n", 30);
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "war") || Objects.equals(input, "War")) {
            justwaitASec(500);
            slowPrint(drawWarrior(), 3);
            slowPrint("Oh tiens, un " + input + " ça faisait longtemps ! \n", 30);  // Output user input
            return KindClass.War;
        } else if (Objects.equals(input, "mage") || Objects.equals(input, "Mage")) {
            justwaitASec(500);
            slowPrint(drawMage(), 3);
            slowPrint("Oh tiens, un " + input + " ça faisait longtemps ! \n", 30);  // Output user input
            return KindClass.Mage;
        } else if (Objects.equals(input, "exit")) {
            leave();
        }
        justwaitASec(500);
        slowPrint(drawMurloc(), 3);
        slowPrint("Ha ! Tu ne sais pas écrire mage ou war ... Tu dois donc être un murloc \n", 30);  // Output user input
        return KindClass.Murloc;
    }

    /**
     * Create hero name
     */
    private String inputName() {
        slowPrint("Quel sera ton nom : \n", 30);
        String nom = scanner.nextLine();  // Read user input
        if (Objects.equals(nom, "exit")) {
            leave();
        }
        justwaitASec(500);
        slowPrint("Ha oui ? Étrange comme nom ça " + "'" + nom + "'" + ", mais bon, tu n'as pas choisi, si ? ... (•_•\") \n", 30);  // Output user input
        return nom;
    }

    /**
     * Enter of this game
     */
    public void start() {
        slowPrint(WaitSecAndASCII.drawDadDonjon(), 5);
        WaitSecAndASCII.justwaitASec(1000);
        slowPrint("Tape 'A' puis 'entrée' pour entrer dans un nouveau bad Donjon \n", 30);
        slowPrint("Tape 'exit' pour quitter à tout moment \n", 30);
        String go = scanner.nextLine();  // Read user input
        if (Objects.equals(go, "a")) {
            justwaitASec(500);
            saveOrNewPerso();
//            createPersonnage();
            return;
        }
        if (Objects.equals(go, "exit")) {
            leave();
        } else {
            slowPrint("Saisie incorrecte \n", 30);
            start();
        }
    }

    private void saveOrNewPerso() {
        slowPrint("Alors c'est partie, créons ton perso ! \n", 30);
        slowPrint("Appuie sur 'a' pour récupérer ton perso sauvegardé \n",30);
        slowPrint("Appuie sur 'b' pour créer un nouveau perso \n",30);
        String persoSaveOrNot = scanner.nextLine();
        if (persoSaveOrNot.equalsIgnoreCase("a")) {
            Personnage newP = javaDB.getPersoFromDB(scanner);
            welcomeNewHero(newP);
        } else if (persoSaveOrNot.equalsIgnoreCase("b")) {
            createPersonnage();
        } else {
            slowPrint("Saisie incorrect \n",30);
            saveOrNewPerso();
        }
    }

    /**
     * Create your hero here
     */
    private void createPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        //random method for class and hp here
        int hp = generateRandomHP(pClass);
        int atk = generateRandomAtk(pClass);
        slowPrint("Si voulez vous commencer la partie avec une arme équipé press 'a' sinon press 'b' (Plus équilibré sans) \n", 30);
        String weaponOrNot = scanner.nextLine();
        if (weaponOrNot.equalsIgnoreCase("a")) {
            Personnage newP = chooseClasseWithWeapon(pClass, name, hp, atk);
            welcomeNewHero(newP);
        }
        if (weaponOrNot.equalsIgnoreCase("b")) {
            Personnage newP = chooseClass(pClass, name, hp, atk);
            welcomeNewHero(newP);
        }
    }

    /**
     * Create hero by class
     */
    private Personnage chooseClass(KindClass pClass, String name, int hp, int atk) {
        String weaponOrNot = scanner.nextLine();
        if (pClass == KindClass.Mage) {
            return new Mage(name, pClass, hp, atk);
        }
        if (pClass == KindClass.War) {
            return new War(name, pClass, hp, atk);
        }
        return new Murloc(name, pClass, hp, atk);
    }

    private Personnage chooseClasseWithWeapon(KindClass pClass, String name, int hp, int atk) {
        if (pClass == KindClass.Mage) {
            slowPrint("Si tu veux commencer avec Frost bolt press 'a' ou avec Arcane blast press 'b', jeune tricheur ? \n", 30);
            String spell = scanner.nextLine();
            if (spell.equalsIgnoreCase("a")) {
                return new Mage(name, pClass, hp, atk, new FrostBolt(KindItemOff.Spell, "Frost bolt", 3), new DefaultDef());
            } else if (spell.equalsIgnoreCase("b")) {
                return new Mage(name, pClass, hp, atk, new ArcaneBlast(KindItemOff.Spell, "Arcane blast", 4), new DefaultDef());
            } else chooseClass(pClass, name, hp, atk);
        }
        if (pClass == KindClass.War) {
            slowPrint("Si tu veux commencer avec Lépéquipik press 'a' ou avec les Wargalives press 'b', jeune tricheur ? \n", 30);
            String sword = scanner.nextLine();
            if (sword.equalsIgnoreCase("a")) {
                return new War(name, pClass, hp, atk, new Epee(KindItemOff.Sword, "Lépéquipik", 2), new DefaultDef());
            } else if (sword.equalsIgnoreCase("b")) {
                return new War(name, pClass, hp, atk, new Epee(KindItemOff.Sword, "Warglaives", 4), new DefaultDef());
            } else chooseClass(pClass, name, hp, atk);
        }
        return new Murloc(name, pClass, hp, atk);
    }


    /**
     * Welcome to the new hero and allow reset hero
     */
    private void welcomeNewHero(Personnage newP) {
        justwaitASec(1500);
        slowPrint("Bienvenue à toi " + newP.getNom() + ", tu es donc un " + newP.getType() + ", débutant visiblement ... \n", 30);
        justwaitASec(1500);
//        slowPrint();("Tu es donc un " + newP.getType() + " je vois ...");
//        justwaitASec(500);
        slowPrint("Tu possèdes *" + newP.getHp() + "* point de vie." + " Et ton score d'attaque est de *" + newP.getAtk() + "*, pas fou tout ça ... (ㆆ_ㆆ) \n", 30);
//        slowPrint();("Et ton score d'attaque est de *" + newP.getAtk() + "*");
        justwaitASec(1500);
        slowPrint("Si tu es satisfait de ton personnage appuie sur a et c'est partie, sinon appuie sur b (Seul les faibles appuient sur b) \n", 30);
        String happy = scanner.nextLine();
        if (Objects.equals(happy, "a")) {
            slowPrint("Alors c'est partie, GL HF ! ᕙ(`▿´)ᕗ \n", 30);
            this.game.setPlayer(newP);
        } else {
            modifyPersonnage();
        }
    }

    /**
     * Modify hero and maybe make it OP if not satisfy by classique choice
     */
    private void modifyPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        slowPrint("Combien de hp veux tu ? : \n", 30);
        int health = Integer.parseInt(scanner.nextLine());
        slowPrint("A combien s'élève ton attaque ? : \n", 30);
        int atk = Integer.parseInt(scanner.nextLine());
        Personnage newPmod = chooseClass(pClass, name, health, atk);
        welcomeNewHero(newPmod);
    }

    private int generateRandomHP(KindClass pClass) {
        if (pClass == KindClass.Mage) {
            return ThreadLocalRandom.current().nextInt(6 + 1 - 3) + 3;
        }
        if (pClass == KindClass.War) {
            return ThreadLocalRandom.current().nextInt(10 + 1 - 5) + 5;
        }
        return 1;
    }

    private int generateRandomAtk(KindClass pClass) {
        if (pClass == KindClass.Mage) {
            return ThreadLocalRandom.current().nextInt(15 + 1 - 8) + 8;
        }
        if (pClass == KindClass.War) {
            return ThreadLocalRandom.current().nextInt(10 + 1 - 5) + 5;
        }
        return 1;
    }

    /**
     * Allow user to leave game
     */
    protected void leave() {
        slowPrint("Tu es donc un lâche... Adieu (Mais merci quand même d'avoir joué !) \n", 30);
        game.leaveGame = true;
    }
}

