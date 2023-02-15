package donjon.equipement;

public class Sort extends EquipementOff{
    int atk;
    public Sort(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
        this.atk = atk;
    }
}
