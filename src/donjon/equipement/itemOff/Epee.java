package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class Epee extends Arme{
    
//    KindItemOff type;
//    String nom;
//    int atk;
    public Epee(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    @Override
    public int damageBoost(Ennemi ennemi) {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
