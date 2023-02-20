package donjon.equipement.buff;

abstract public class Buff {
//    boolean isActiv;
    int duration;
//    int atkBoost;

    public Buff(int duration) {
        this.duration = duration;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
