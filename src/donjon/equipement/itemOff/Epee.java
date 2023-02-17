package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class Epee extends Arme{
    
    KindItemOff type;
    String nom;
    int atk;
    public Epee(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    @Override
    public boolean damageBoost(Ennemi ennemi) {
        return false;
    }

    public Epee warGlaives() {
        return new Epee(KindItemOff.Sword, "warglaives", 6);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
