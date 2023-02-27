package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class FireBall extends Sort{
    public FireBall(KindItemOff type, String nom, int atk, String desc) {
        super(type, nom, atk, desc);
    }

    @Override
    public int damageBoost(Ennemi ennemi) {
        return 0;
    }
}
