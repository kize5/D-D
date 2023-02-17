package donjon.ennemi;

import donjon.personnage.Mage;
import donjon.personnage.Personnage;
import donjon.personnage.War;

public class Spectre extends Ennemi{
    public Spectre(KindEnnemi type, String nom, int hp, int atk) {
        super(type, nom, hp, atk);
    }

    public Spectre() {
        super(KindEnnemi.Spectre, "Spectre", 13, 4);
    }

    public boolean canHit(Personnage player){
        if (player instanceof War) {
            return false;
        }  return true;
    }
}
