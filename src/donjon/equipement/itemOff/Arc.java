package donjon.equipement.itemOff;

import donjon.ennemi.Dragon;
import donjon.ennemi.Ennemi;

public class Arc extends Arme{
    /**
     * Construct for all offensive equipment
     *
     * @param type
     * @param nom
     * @param atk
     */
    public Arc(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    public boolean damageBoost(Ennemi ennemi) {
        if (ennemi instanceof Dragon) {
            // add +2 damage
            return true;
        } return false;
    }
}
