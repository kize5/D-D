package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class FrostBolt extends Sort{
    public FrostBolt(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    @Override
    public int damageBoost(Ennemi ennemi) {
        return 0;
    }
}