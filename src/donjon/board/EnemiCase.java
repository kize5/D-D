package donjon.board;

import donjon.Fight;
import donjon.ennemi.Ennemi;
import donjon.ennemi.KindEnnemi;
import donjon.personnage.Personnage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

public class EnemiCase implements Case {

    int rng;
    Ennemi ennemi;

    int playerposition;

    @Override
    public void apply(Personnage player, int playerPose) {
        playerposition = playerPose;
        randomLine();
        setRng();
        generateEnemy();
        new Fight(player, ennemi);
        slowPrint("Après ce combat : \n",30);
        slowPrint(player.toString(), 30);
    }

    private void generateEnemy() {
        if (playerposition < 40) {
            switch (rng) {
                case 0 -> spawnGobelin();
                case 1 -> spawnMage();
                case 2 -> spawnDragon();
            }
        } else if (playerposition < 62) {
            switch (rng) {
                case 0, 1 -> spawnMage();
                case 2 -> spawnDragon();
            }
        }
        else {
            ennemi = new Ennemi(KindEnnemi.Dragon, "Dragon blanc aux yeux bleus", 16, 5);
            slowPrint(drawDragon(), 3);
            slowPrint("Un grognement sourd raisonne dans la salle, une ombre massive s'avance dans un rugissement, te voilà face à un terrible dragon blanc aux yeux bleus ! \n", 30);
        }
    }

    public void setRng() {
        rng = ThreadLocalRandom.current().nextInt(0, 3);
    }
    private void randomLine() {
        Random random = new Random();
        int randomNum = random.nextInt(1, 5 + 1);
        switch (randomNum) {
            case 1 -> slowPrint("Une musique inquiétante démarre, ce n'est pas bon signe ça \n", 30);
            case 2 -> slowPrint("Cette pièce sent mauvais, mais pas que, elle sent aussi le danger ! \n", 30);
            case 3 ->
                    slowPrint("Cette pièce est vaste et haute de plafond, mais ce que tu y aperçois ne va pas te plaire ... \n", 30);
            case 4 ->
                    slowPrint("Un cri retentit provenant d'une salle juste à côté de toi, tu t'y préciptes, mais trop tard pour la victime, par contre ... \n", 30);
            case 5 ->
                    slowPrint("Après t'être faufilé dans un étroit passage, tu débarques dans une vaste pièce, un bruit au dessus de ta tête, tu lèves les yeux et ... \n", 30);
        }
    }
    private void spawnGobelin() {
        ennemi = new Ennemi(KindEnnemi.Gobelin, "kobold", 6, 1);
        slowPrint(drawGobelin(), 3);
        slowPrint("Une lumière dans l'obscurité, une pioche.. Oh non un vieux kobold moche t'attaque ! \n", 30);
    }
    private void spawnMage() {
        ennemi = new Ennemi(KindEnnemi.Wizard, "mage noir", 9, 3);
        slowPrint(drawBadWizard(), 3);
        slowPrint("L'air est plus lourd, une silhouette encapuchonné apparait depuis les ombres toi, un mage, un mage noir même ...  \n", 30);
    }
    private void spawnDragon() {
        ennemi = new Ennemi(KindEnnemi.Dragon, "Dragon noir aux yeux rouges", 15, 4);
        slowPrint(drawDragon(), 3);
        slowPrint("Un grognement sourd raisonne dans la salle, une ombre massive s'avance dans un rugissement, te voilà face à un terrible dragon noir aux yeux rouges ! \n", 30);
    }
}
