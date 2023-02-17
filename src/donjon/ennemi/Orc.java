package donjon.ennemi;

import donjon.personnage.Mage;
import donjon.personnage.Personnage;

public class Orc extends Ennemi{
    public Orc(KindEnnemi type, String nom, int hp, int atk) {
        super(type, nom, hp, atk);
    }

    public Orc() {
        super(KindEnnemi.Orc, "Orc", 10, 5);
    }

    @Override
    public boolean canHit(Personnage player){
        if (player instanceof Mage) {
            return false;
        }  return true;
    }
}
