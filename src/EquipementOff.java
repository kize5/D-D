public class EquipementOff {

    String type;
    String nom;
    int levelAtk;

    public EquipementOff(String type, String nom, int atk) {
        this.type = type;
        this.nom = nom;
        this.levelAtk = atk;
    }

    protected String getOffEquipment() {
        return type + nom + levelAtk;
    }
}
