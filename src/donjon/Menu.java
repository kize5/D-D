package donjon;

import donjon.equipement.itemDef.DefaultDef;
import donjon.equipement.itemOff.*;
import donjon.personnage.*;

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
    private SavePlayerMySql javaDB;

    /**
     * Constructor
     *
     * @param scanner Take scanner from main
     * @param game    Take a new game instance from main
     */
    public Menu(Scanner scanner, Game game, SavePlayerMySql javaDB) {
        this.scanner = scanner;
        this.game = game;
        this.javaDB = javaDB;
    }

    /**
     * Choose hero class
     */
    private KindClass inputClass() {
        slowPrint("As tu envie de jouer 'Mage' ou 'War' ? \n");
        String input = scanner.nextLine();// Read user input
        if (Objects.equals(input, "war") || Objects.equals(input, "War")) {
            justwaitASec();
            slowPrintForAscii(drawWarrior());
            slowPrint("Oh tiens, un " + input + " ça faisait longtemps ! \n");  // Output user input
            return KindClass.War;
        } else if (Objects.equals(input, "mage") || Objects.equals(input, "Mage")) {
            justwaitASec();
            slowPrintForAscii(drawMage());
            slowPrint("Oh tiens, un " + input + " ça faisait longtemps ! \n");  // Output user input
            return KindClass.Mage;
        } else if (Objects.equals(input, "exit")) {
            leave();
        }
        justwaitASec();
        slowPrintForAscii(drawMurloc());
        slowPrint("Ha ! Tu ne sais pas écrire mage ou war ... Tu dois donc être un murloc \n");  // Output user input
        return KindClass.Murloc;
    }

    /**
     * Create hero name
     */
    private String inputName() {
        slowPrint("Quel sera ton nom : \n");
        String nom = scanner.nextLine();  // Read user input
        if (Objects.equals(nom, "exit")) {
            leave();
        }
        justwaitASec();
        slowPrint("Ha oui ? Étrange comme nom ça " + "'" + nom + "'" + ", mais bon, tu n'as pas choisi, si ? ... (•_•\") \n");  // Output user input
        return nom;
    }

    /**
     * Enter of this game
     */
    public void start() {
        slowPrintForAscii(WaitSecAndASCII.drawDadDonjon());
        WaitSecAndASCII.justwaitASec();
        slowPrint("Tape 'A' puis 'entrée' pour entrer dans un nouveau bad Donjon \n");
        slowPrint("Tape 'exit' pour quitter à tout moment \n");
        String go = scanner.nextLine();  // Read user input
        if (Objects.equals(go, "a")) {
            justwaitASec();
            saveOrNewPerso();
//            createPersonnage();
            return;
        }
        if (Objects.equals(go, "exit")) {
            leave();
        } else {
            slowPrint("Saisie incorrecte \n");
            start();
        }
    }

    private void saveOrNewPerso() {
        slowPrint("Alors c'est partie, créons ton perso ! \n");
        slowPrint("Appuie sur 'a' pour récupérer ton perso sauvegardé \n");
        slowPrint("Appuie sur 'b' pour créer un nouveau perso \n");
        String persoSaveOrNot = scanner.nextLine();
        if (persoSaveOrNot.equalsIgnoreCase("a")) {
            Personnage newP = javaDB.getPersoFromDB(scanner);
            welcomeNewHero(newP);
        } else if (persoSaveOrNot.equalsIgnoreCase("b")) {
            createPersonnage();
        } else {
            slowPrint("Saisie incorrect \n");
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
        slowPrint("Si voulez vous commencer la partie avec une arme équipé press 'a' sinon press 'b' (Plus équilibré sans) \n");
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
            slowPrint("Si tu veux commencer avec Frost bolt press 'a' ou avec Arcane blast press 'b', jeune tricheur ? \n");
            String spell = scanner.nextLine();
            if (spell.equalsIgnoreCase("a")) {
                return new Mage(name, pClass, hp, atk, new FrostBolt(KindItemOff.Spell, "Frost bolt", 3, "Tu viens de trouver un sort de givre, pas mal ça, tu vas les geler sur place !  \n"), new DefaultDef());
            } else if (spell.equalsIgnoreCase("b")) {
                return new Mage(name, pClass, hp, atk, new ArcaneBlast(KindItemOff.Spell, "Arcane blast", 4, "Oooh l'arcane blast, tu vas pouvoir frapper tes ennemies avec la magie arcanique !  \n"), new DefaultDef());
            } else chooseClass(pClass, name, hp, atk);
        }
        if (pClass == KindClass.War) {
            slowPrint("Si tu veux commencer avec Lépéquipik press 'a' ou avec les Wargalives press 'b', jeune tricheur ? \n");
            String sword = scanner.nextLine();
            if (sword.equalsIgnoreCase("a")) {
                return new War(name, pClass, hp, atk, new Epee(KindItemOff.Sword, "Lépéquipik", 2, "Wow tu viens de trouver lépékipique, attention, ça pique ... \n "), new DefaultDef());
            } else if (sword.equalsIgnoreCase("b")) {
                return new War(name, pClass, hp, atk, new Epee(KindItemOff.Sword, "Warglaives", 4, "Tu viens de loot les warglaives ! Ils ne sont pas prêts ! \n"), new DefaultDef());
            } else chooseClass(pClass, name, hp, atk);
        }
        return new Murloc(name, pClass, hp, atk);
    }


    /**
     * Welcome to the new hero and allow reset hero
     */
    private void welcomeNewHero(Personnage newP) {
        justwaitASec();
        slowPrint("Bienvenue à toi " + newP.getNom() + ", tu es donc un " + newP.getType() + ", débutant visiblement ... \n");
        justwaitASec();
//        slowPrint();("Tu es donc un " + newP.getType() + " je vois ...");
//        justwaitASec(500);
        slowPrint("Tu possèdes *" + newP.getHp() + "* point de vie." + " Et ton score d'attaque est de *" + newP.getAtk() + "*, pas fou tout ça ... (ㆆ_ㆆ) \n");
//        slowPrint();("Et ton score d'attaque est de *" + newP.getAtk() + "*");
        justwaitASec();
        slowPrint("Si tu es satisfait de ton personnage appuie sur a et c'est partie, sinon appuie sur b (Seul les faibles appuient sur b) \n");
        String happy = scanner.nextLine();
        if (Objects.equals(happy, "a")) {
            slowPrint("Alors c'est partie, GL HF ! ᕙ(`▿´)ᕗ \n");
            this.game.setPlayer(newP);
        } else if (happy.equalsIgnoreCase("b")) {
            modifyPersonnage();
        } else {
            slowPrint("Erreur de saisie, try again, 'a' ou 'b' \n");
            welcomeNewHero(newP);
        }
    }

    /**
     * Modify hero and maybe make it OP if not satisfy by classique choice
     */
    private void modifyPersonnage() {
        String name = this.inputName();
        KindClass pClass = this.inputClass();
        slowPrint("Combien de hp veux tu ? : \n");
        int health = Integer.parseInt(scanner.nextLine());
        slowPrint("A combien s'élève ton attaque ? : \n");
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
        slowPrint("Tu es donc un lâche... Adieu (Mais merci quand même d'avoir joué !) \n");
        game.leaveGame = true;
    }
}

