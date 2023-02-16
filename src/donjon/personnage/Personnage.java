package donjon.personnage;
import donjon.equipement.Item;

abstract public class Personnage {

    private final String nom;
    private final KindClass type;
    private int hp;
    private final int atk;
    private int offItem;
    private int defItem;
    private boolean isAlive;

    /**
     * Construct for set up a new personnage
     * @param nom Nom du perso
     * @param type Type du perso
     * @param hp Health points
     * @param atk Attack points
     */
    protected Personnage(String nom, KindClass type, int hp, int atk) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.offItem = 0;
        this.defItem = 0;
        this.isAlive = true;
    }

    protected Personnage(String nom, KindClass type, int hp, int atk, int offItem, int defItem) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.defItem = defItem;
        this.offItem = offItem;
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

    public void setoffItem (int newOffItem) {
        this.offItem = newOffItem;
    }

    public int getoffItem (){
        return offItem;
    }

    public int getOffItem() {
        return offItem;
    }

    public void setOffItem(int offItem) {
        this.offItem = offItem;
    }

    public int getDefItem() {
        return defItem;
    }

    public void setDefItem(int defItem) {
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
                "un bonus offensif de " + offItem +
                " et un bonus défensif de " + defItem +
                " \n";
    }
/*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
