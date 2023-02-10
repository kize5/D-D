package donjon.personnage;
import donjon.KindClass;

abstract public class Personnage {

    private final String nom;
    private final KindClass type;
    private final int hp;
    private final int atk;

    public Personnage(String nom, KindClass type, int hp, int atk) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
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
