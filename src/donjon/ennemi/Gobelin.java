package donjon.ennemi;

import donjon.personnage.Personnage;

public class Gobelin extends Ennemi{
    public Gobelin(KindEnnemi type, String nom, int hp, int atk) {
        super(type, nom, hp, atk);
    }

    @Override
    public boolean canHit(Personnage player) {
        return true;
    }

    public Gobelin() {
        super(KindEnnemi.Gobelin, "kobold", 6, 1);
    }
}
