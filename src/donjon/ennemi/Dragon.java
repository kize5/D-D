package donjon.ennemi;

import donjon.personnage.Personnage;

public class Dragon extends Ennemi{

    public Dragon(KindEnnemi type, String nom, int hp, int atk) {
        super(type, nom, hp, atk);
    }

    @Override
    public boolean canHit(Personnage player) {
        return true;
    }

    public Dragon() {
        super(KindEnnemi.Dragon, "Dragon noir aux yeux rouges", 15, 4);
    }
}
