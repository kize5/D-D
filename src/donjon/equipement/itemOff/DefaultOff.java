package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class DefaultOff extends EquipementOff{
    public DefaultOff() {
        super(KindItemOff.Default, "main nue", 0);
    }

    @Override
    public boolean damageBoost(Ennemi ennemi) {
        return false;
    }
}
