package donjon.equipement.itemOff;

abstract public class Arme extends EquipementOff{


    public Arme(KindItemOff type, String nom, int atk, String desc) {
        super(type, nom, atk, desc);
    }

    @Override
    public String toString() {
        return nom + " qui frappe pour " + ptsAtk + " de dégats ";
    }
}
