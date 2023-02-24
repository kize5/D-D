package donjon.equipement.buff;

public class ThunderPotion extends Buff{

    public ThunderPotion(int duration, String nom) {
        super(duration, nom);
    }

    @Override
    public int effect(int playerAtk) {
        return playerAtk * 2;
    }
}
