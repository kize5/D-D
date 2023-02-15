package donjon.ennemi;

public class Ennemi {

    private final String nom;
    private final KindEnnemi type;
    private int hp ,atk;


    /**
     * Construct for all ennemi
     * @param nom Name of the ennemi
     * @param type type of the ennemi
     * @param hp Health points of the enemy
     * @param atk Attack points of the enemy
     */
    public Ennemi(KindEnnemi type, String nom, int hp, int atk) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
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

    public int getAtk() {
        return atk;
    }
}
