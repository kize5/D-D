package donjon.ennemi;

import donjon.personnage.Personnage;

public class MageBad extends Ennemi{
    public MageBad(KindEnnemi type, String nom, int hp, int atk) {
        super(type, nom, hp, atk);
    }

    @Override
    public boolean canHit(Personnage player) {
        return true;
    }

    public MageBad() {
        super(KindEnnemi.Wizard, "mage noir", 9, 3);
    }
}
