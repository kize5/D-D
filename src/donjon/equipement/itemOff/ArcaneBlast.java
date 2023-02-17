package donjon.equipement.itemOff;

import donjon.ennemi.Dragon;
import donjon.ennemi.Ennemi;
import donjon.ennemi.Spectre;

public class ArcaneBlast extends Sort{
    public ArcaneBlast(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    public ArcaneBlast(){
        super(KindItemOff.Spell, "arcane blast", 5);
    }
    public boolean damageBoost(Ennemi ennemi) {
        if (ennemi instanceof Spectre) {
            // add +3 damage
            return true;
        } return false;
    }
}
