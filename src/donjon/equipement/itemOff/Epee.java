package donjon.equipement.itemOff;

public class Epee extends Arme{
    
    KindItemOff type;
    String nom;
    int atk;
    public Epee(KindItemOff type, String nom, int atk) {
        super(type, nom, atk);
    }

    public Epee warGlaives() {
        return new Epee(KindItemOff.Sword, "warglaives", 6);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
