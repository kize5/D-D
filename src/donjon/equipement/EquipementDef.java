package donjon.equipement;

abstract public class EquipementDef {

    KindItemDef type;
    String nom;
    int levelDef;

    /**
     *
     */

    /**
     * Construct for all defensive equipment
     * @param type Type of defensive equipment
     * @param nom Name of defensive equipment
     * @param def Point of def gain with this defensive equipment
     */
    protected EquipementDef(KindItemDef type, String nom, int def) {
        this.type = type;
        this.nom = nom;
        this.levelDef = def;
//        switch (type) {
//            case Bouclier -> this.levelDef = 4;
//            case IceBarrier -> this.levelDef = 5;
//            case Default -> this.levelDef = 0;
//        }
    }
}
