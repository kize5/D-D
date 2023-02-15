package donjon.personnage;
import donjon.KindEnnemi;

abstract public class Personnage {

    private final String nom;
    private final KindEnnemi type;
    private int hp;
    private final int atk;
    private int offItem;
    private int defItem;

    /**
     * Construct for set up a new personnage
     * @param nom Nom du perso
     * @param type Type du perso
     * @param hp Health points
     * @param atk Attack points
     */
    protected Personnage(String nom, KindEnnemi type, int hp, int atk) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.offItem = 0;
        this.defItem = 0;
    }

    protected Personnage(String nom, KindEnnemi type, int hp, int atk, int offItem, int defItem) {
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
    public KindEnnemi getType() {
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

    @Override
    public String toString() {
        return "Personnage{" +
                "nom='" + nom + '\'' +
                ", type=" + type +
                ", hp=" + hp +
                ", atk=" + atk +
                ", offItem=" + offItem +
                ", defItem=" + defItem +
                '}';
    }
/*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
