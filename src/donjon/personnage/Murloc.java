package donjon.personnage;

import donjon.equipement.Item;

/**
 * Class to create a new murloc
 */
public class Murloc extends Personnage {
    /**
     * Construct to create a new murloc
     * @param nom Nom of new murloc
     * @param type Type of new murloc
     * @param hp Heal points of new murloc
     * @param atk Attack points of new murloc
     */
    public Murloc(String nom, KindClass type, int hp, int atk) {
        super(nom, type, hp, atk);
    }

    @Override
    public boolean isUsableEquipementOff(Item item) {
        return true;
    }
    public boolean isUsableEquipementDeff(Item item) {
        return true;
    }


}
