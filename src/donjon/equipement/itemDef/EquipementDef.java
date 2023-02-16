package donjon.equipement.itemDef;

import donjon.equipement.Item;

abstract public class EquipementDef extends Item {

    KindItemDef type;
    String nom;
    int levelDef;

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
    }

    public KindItemDef getType() {
        return type;
    }

    public void setType(KindItemDef type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLevelDef(int levelDef) {
        this.levelDef = levelDef;
    }

    public int getLevelDef() {
        return levelDef;
    }
}
