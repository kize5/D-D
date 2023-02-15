package donjon.equipement;

public class Arme extends  EquipementOff{

    int atk;
    public Arme(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
        this.atk = atk;
    }
}
