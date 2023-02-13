package donjon.equipement;

abstract public class EquipementOff {

    KindItemOff type;
    String nom;
    int ptsAtk;

    /**
     * Construct for all offensive equipment
     */
    protected EquipementOff(KindItemOff type, String nom, int atk) {
        this.type = type;
        this.nom = nom;
        this.ptsAtk = atk;
//        switch (type) {
//            case Arme -> this.ptsAtk = 4;
//            case Sort -> this.ptsAtk = 5;
//            case Default -> this.ptsAtk = 0;
//        }
    }

    protected String getOffEquipment() {
        return type + nom + ptsAtk;
    }
}
