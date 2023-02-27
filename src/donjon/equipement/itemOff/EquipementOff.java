package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;
import donjon.equipement.Item;

abstract public class EquipementOff extends Item {

    KindItemOff type;
    String nom;
    int ptsAtk;
    String desc;

    /**
     * Construct for all offensive equipment
     */
    protected EquipementOff(KindItemOff type, String nom, int atk, String desc) {
        this.type = type;
        this.nom = nom;
        this.ptsAtk = atk;
        this.desc = desc;
    }


    public KindItemOff getType() {
        return type;
    }

    public void setType(KindItemOff type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPtsAtk() {
        return ptsAtk;
    }

    public void setPtsAtk(int ptsAtk) {
        this.ptsAtk = ptsAtk;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    abstract public int damageBoost(Ennemi ennemi);

    @Override
    public String toString() {
        return nom + " qui te donne un bonus de " + ptsAtk;
    }
}
