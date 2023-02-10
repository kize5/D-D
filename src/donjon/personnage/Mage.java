package donjon.personnage;

import donjon.KindClass;

public class Mage extends Personnage{

    public Mage(String nom, KindClass type) {
        super(nom, type, 6, 15);
    }
    public Mage(String nom, KindClass type, int hp, int atk) {
        super(nom, type, hp, atk);
    }

}

