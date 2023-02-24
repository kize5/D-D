package donjon.personnage;

import donjon.equipement.buff.Buff;
import donjon.equipement.itemDef.Bouclier;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemOff.Arme;
import donjon.equipement.Item;
import donjon.equipement.itemOff.EquipementOff;

/**
 * Class to create a new war
 */
public class War extends Personnage{

    /**
     * Construct to create a new war
     * @param nom Nom of new war
     * @param type Type of new war
     * @param hp Heal points of new war
     * @param atk Attack points of new war
     */
    public War(String nom, KindClass type, int hp, int atk, EquipementOff off, EquipementDef def) {
        super(nom, type, hp, atk, off, def);
    }
    public War(String nom, KindClass type, int hp, int atk) {
        super(nom, type, hp, atk);
    }

    public War(String nom, KindClass type, int hp, int atk, EquipementOff offItem, EquipementOff offItem2, EquipementDef defItem, Buff buff, Boolean isAlive) {
        super(nom, type, hp, atk, offItem, offItem2,defItem,buff,isAlive);
    }

    @Override
    public boolean isUsableEquipementOff(Item item) {
        if (item instanceof Arme) {
            return true;
        }
        return false;
    }
    public boolean isUsableEquipementDeff(Item item) {
        if (item instanceof Bouclier) {
            return true;
        }
        return false;
    }
}
