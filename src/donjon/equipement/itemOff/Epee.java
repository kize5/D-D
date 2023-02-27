package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class Epee extends Arme{

    public Epee(KindItemOff type, String nom, int atk, String desc) {
        super(type, nom, atk, desc);
    }

//    KindItemOff type;
//    String nom;
//    int atk;

    @Override
    public int damageBoost(Ennemi ennemi) {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
