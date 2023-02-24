package donjon.personnage;

import donjon.equipement.Item;
import donjon.equipement.buff.Buff;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemOff.EquipementOff;

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
    public Murloc(String nom, KindClass type, int hp, int atk, EquipementOff off, EquipementDef def) {
        super(nom, type, hp, atk, off, def);
    }
    public Murloc(String nom, KindClass type, int hp, int atk) {
        super(nom, type, hp, atk);
    }
    public Murloc(String nom, KindClass type, int hp, int atk, EquipementOff offItem, EquipementOff offItem2, EquipementDef defItem, Buff buff, Boolean isAlive) {
        super(nom, type, hp, atk, offItem, offItem2,defItem,buff,isAlive);
    }

    @Override
    public boolean isUsableEquipementOff(Item item) {
        return true;
    }
    public boolean isUsableEquipementDeff(Item item) {
        return true;
    }


}
