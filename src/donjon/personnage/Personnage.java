package donjon.personnage;
import donjon.equipement.Item;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemOff.Epee;
import donjon.equipement.itemOff.EquipementOff;
import donjon.equipement.itemOff.KindItemOff;

abstract public class Personnage {

    private final String nom;
    private final KindClass type;
    private int hp;
    private final int atk;
    private EquipementOff offItem;
    private EquipementDef defItem;
    private boolean isAlive;

    /**
     * Construct for set up a new personnage
     * @param nom Nom du perso
     * @param type Type du perso
     * @param hp Health points
     * @param atk Attack points
     */
//    protected Personnage(String nom, KindClass type, int hp, int atk) {
//        this.type = type;
//        this.nom = nom;
//        this.hp = hp;
//        this.atk = atk;
//        this.offItem = null;
//        this.defItem = null;
//        this.isAlive = true;
//    }

    protected Personnage(String nom, KindClass type, int hp, int atk, EquipementOff offItem, EquipementDef defItem) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.defItem = defItem;
        this.offItem = offItem;
        this.isAlive = true;
    }

    public String getNom() {
        return nom;
    }
    public KindClass getType() {
        return type;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setoffItem (EquipementOff newOffItem) {
        this.offItem = newOffItem;
    }

    public EquipementOff getoffItem (){
        return offItem;
    }

    public EquipementDef getDefItem() {
        return defItem;
    }

    public void setDefItem(EquipementDef defItem) {
        this.defItem = defItem;
    }

    public abstract boolean isUsableEquipementOff(Item item);
    public abstract boolean isUsableEquipementDeff(Item item);

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Ton personnage " +
                nom +
                " de type " + type +
                " a " + hp +
                " point de vie, " +
                atk + " point d'attaque, " +
                "un bonus offensif de " + offItem.getPtsAtk() +
                " et un bonus défensif de " + defItem.getLevelDef() +
                " \n";
    }
/*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
