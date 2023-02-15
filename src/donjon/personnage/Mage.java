package donjon.personnage;

import donjon.KindEnnemi;

/**
 * Class to create a new mage
 */
public class Mage extends Personnage{

//    public Mage(String nom, KindClass type) {
//        super(nom, type, 6, 15);
//    }

    /**
     * Construct to create a new mage
     * @param nom Nom of new mage
     * @param type Type of new mage
     * @param hp Heal points of new mage
     * @param atk Attack points of new mage
     */
    public Mage(String nom, KindEnnemi type, int hp, int atk) {
        super(nom, type, hp, atk);
    }
}

