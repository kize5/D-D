package donjon;

import donjon.equipement.*;

import java.util.concurrent.ThreadLocalRandom;

public class LootCase implements Case{
    Object loot;
    @Override
    public Object apply(Object obj) {
        setLoot();
        return Case.super.apply(loot);
    }

    public void setLoot() {
        int nb = ThreadLocalRandom.current().nextInt(10 + 1 - 1) + 1;
        if (nb < 3) {
            loot = new Arme(KindItemOff.Sword, "lépékipique", 5);
        } else if (nb > 3 && nb < 6) {
            loot = new mediumPotion(KindPotion.mediumPotion, "Potion de vie moyenne");
        } else loot = new Sort(KindItemOff.Spell, "fire ball", 5);
    }
}
