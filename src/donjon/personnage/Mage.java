package donjon.personnage;

import donjon.equipement.Item;
import donjon.equipement.itemDef.Barriere;
import donjon.equipement.itemDef.Bouclier;
import donjon.equipement.itemOff.Sort;

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
    public Mage(String nom, KindClass type, int hp, int atk) {
        super(nom, type, hp, atk);
    }

    @Override
    public boolean isUsableEquipementOff(Item item) {
        if (item instanceof Sort) {
            return true;
        }
        return false;
    }

    public boolean isUsableEquipementDeff(Item item) {
        if (item instanceof Barriere) {
            return true;
        }
        return false;
    }
}

