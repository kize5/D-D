package donjon.personnage;
import donjon.KindClass;

abstract public class Personnage {

    private final String nom;
    private final KindClass type;
    private final int hp;
    private final int atk;
    private final int offItem;
    private final int defItem;

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
    public int getAtk() {
        return atk;
    }

    @Override
    public String toString() {
        return "donjon.personnage.Personnage{" +
                "nom='" + nom + '\'' +
                ", type=" + type +
                ", hp=" + hp +
                ", atk=" + atk +
                '}';
    }

    /*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
