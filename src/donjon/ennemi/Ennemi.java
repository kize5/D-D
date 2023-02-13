package donjon.ennemi;
import donjon.KindClass;

abstract public class Ennemi {

    private final String nom;
    private final KindClass type;
    private final int hp ,atk;


    /**
     * Construct for all ennemi
     * @param nom Name of the ennemi
     * @param type type of the ennemi
     * @param hp Health points of the enemy
     * @param atk Attack points of the enemy
     */
    public Ennemi(String nom, KindClass type, int hp, int atk) {
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
}
