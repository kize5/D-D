package donjon.personnage;
import donjon.equipement.Item;
import donjon.equipement.buff.Buff;
import donjon.equipement.buff.ThunderPotion;
import donjon.equipement.itemDef.DefaultDef;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemOff.DefaultOff;
import donjon.equipement.itemOff.EquipementOff;

abstract public class Personnage {

    private final String nom;
    private final KindClass type;
    private int hp;
    private final int atk;
    private EquipementOff offItem;
    private EquipementOff offItem2;
    private EquipementDef defItem;
    private boolean isAlive;
    private Buff buff;

    /**
     * Construct for set up a new personnage
     * @param nom Nom du perso
     * @param type Type du perso
     * @param hp Health points
     * @param atk Attack points
     */
    protected Personnage(String nom, KindClass type, int hp, int atk) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.offItem = new DefaultOff();
        this.offItem2 = new DefaultOff();
        this.defItem = new DefaultDef();
        this.buff = new ThunderPotion(0);
        this.isAlive = true;
    }

    protected Personnage(String nom, KindClass type, int hp, int atk, EquipementOff offItem, EquipementDef defItem) {
        this.type = type;
        this.nom = nom;
        this.hp = hp;
        this.atk = atk;
        this.offItem = offItem;
        this.offItem2 = new DefaultOff();
        this.defItem = defItem;
        this.buff = new ThunderPotion(0);
        this.isAlive = true;
    }

    public String getNom() {
        return nom;
    }
    public KindClass getType() {
        return type;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setOffItem(EquipementOff newOffItem) {
        this.offItem = newOffItem;
    }

    public EquipementOff getOffItem(){
        return offItem;
    }


    public EquipementOff getOffItem2() {
        return offItem2;
    }

    public void setOffItem2(EquipementOff offItem2) {
        this.offItem2 = offItem2;
    }

    public EquipementDef getDefItem() {
        return defItem;
    }

    public void setDefItem(EquipementDef defItem) {
        this.defItem = defItem;
    }

    public abstract boolean isUsableEquipementOff(Item item);
    public abstract boolean isUsableEquipementDeff(Item item);

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    @Override
    public String toString() {
        return "Ton personnage " +
                nom +
                " de classe " + type +
                " a " + hp +
                " point de vie, " +
                atk + " point d'attaque, " +
                "un bonus offensif de " + offItem.getPtsAtk() + " ou " + offItem2.getPtsAtk() +
                " et un bonus défensif de " + defItem.getLevelDef() +
                " \n";
    }
/*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
