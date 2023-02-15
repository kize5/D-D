package donjon;

import donjon.ennemi.Ennemi;
import donjon.ennemi.KindEnnemi;
import donjon.personnage.Personnage;

import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

public class EnemiCase implements Case {

    int rng;
    Ennemi ennemi;

    public EnemiCase() {
        System.out.println("Je suis la case des méchants");
    }

    @Override
    public void apply(Personnage player) {
        setRng();
        generateEnemy();
        System.out.println("FROM Apply Je suis la case des méchants");
    }

    private void generateEnemy() {
        switch (rng) {
            case 0 -> {
                ennemi = new Ennemi(KindEnnemi.Gobelin, "kobold", 6, 1);
                slowPrint(drawGobelin(),3);
                slowPrint("Une lumière dans l'obscurité, une pioche.. Oh non un vieux kobold moche t'attaque ! \n", 30);
            }
            case 1 -> {
                ennemi = new Ennemi(KindEnnemi.Wizard, "mage noir", 9, 2);
                slowPrint(drawBadWizard(),3);
                slowPrint("L'air est plus lourd, une silhouette encapuchonné apparait depuis les ombres toi, un mage, un mage noir même ...  \n", 30);
            }
            case 2 -> {
                ennemi = new Ennemi(KindEnnemi.Dragon, "Dragon noir aux yeux rouges", 15, 4);
                slowPrint(drawDragon(),3);
                slowPrint("Une lumière dans l'obscurité, une pioche.. Oh non un vieux kobold moche t'attaque ! \n", 30);
            }
        }
    }

    public void setRng() {
        rng = ThreadLocalRandom.current().nextInt(0, 3);
    }
}
