package donjon.equipement.itemOff;

import donjon.ennemi.Dragon;
import donjon.ennemi.Ennemi;
import donjon.ennemi.Spectre;

public class ArcaneBlast extends Sort{
    public ArcaneBlast(KindItemOff type, String nom, int atk, String desc) {
        super(type, nom, atk, desc);
    }

    public ArcaneBlast(){
        super(KindItemOff.Spell, "arcane blast", 5, "Oooh l'arcane blast, tu vas pouvoir frapper tes ennemies avec la magie arcanique !  \n");
    }
    public int damageBoost(Ennemi ennemi) {
        if (ennemi instanceof Spectre) {
            // add +3 damage
            return 3;
        } return 0;
    }
}
