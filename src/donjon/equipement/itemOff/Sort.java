package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

abstract public class Sort extends EquipementOff{
    int atk;
    public Sort(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
        this.atk = atk;
    }

    @Override
    public String toString() {
        return nom + " qui frappe pour " + atk + " de dégats";
    }
}
