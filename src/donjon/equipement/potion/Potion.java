package donjon.equipement.potion;

abstract public class Potion{
    KindPotion type;
    String nom;
    int hpWin;

    /**
     * Construct for all potion
     */
    protected Potion(KindPotion type, String nom, int hpBack) {
        this.type = type;
        this.nom = nom;
        this.hpWin = hpBack;
    }
}
