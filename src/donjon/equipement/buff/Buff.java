package donjon.equipement.buff;

abstract public class Buff {
//    boolean isActiv;
    int duration;
    //    int atkBoost;
    String nom;

    public Buff(int duration, String nom) {
        this.duration = duration;
        this.nom = nom;
    }

    public abstract int effect(int playerAtk);
    public int isActive() {
        return this.duration;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
