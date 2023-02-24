package donjon.equipement.itemOff;

import donjon.ennemi.Dragon;
import donjon.ennemi.Ennemi;

public class Arc extends Arme{
    /**
     * Construct for all offensive equipment
     */
    public Arc(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    public int damageBoost(Ennemi ennemi) {
        if (ennemi instanceof Dragon) {
            // add +2 damage
            return 2;
        } return 0;
    }
}
