package donjon.equipement;

import donjon.KindPotion;

abstract public class Potion{
    KindPotion type;
    String nom;
    int hpWin;

    //Define defensive equipment
    public Potion(KindPotion type, String nom, int hpBack) {
        this.type = type;
        this.nom = nom;
        this.hpWin = hpBack;
    }
}
