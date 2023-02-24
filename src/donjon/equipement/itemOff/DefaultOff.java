package donjon.equipement.itemOff;

import donjon.ennemi.Ennemi;

public class DefaultOff extends EquipementOff{
    public DefaultOff() {
        super(KindItemOff.Default, "main nue", 0);
    }

    @Override
    public int damageBoost(Ennemi ennemi) {
        return 0;
    }
}
