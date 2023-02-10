package donjon.equipement;
import donjon.KindItemDef;

abstract public class EquipementDef {

    KindItemDef type;
    String nom;
    int levelDef;

    //Define defensive equipment
    public EquipementDef(KindItemDef type, String nom, int def) {
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
