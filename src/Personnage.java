public class Personnage {

    public String nom;
    public KindClass type;
    public int hp;
    public int atk;

    protected Personnage() {

    }

    public Personnage(String nom) {
        this.nom = nom;
    }

    public Personnage(String nom, KindClass type) {
        this.type = type;
        this.nom = nom;
        switch (type){
            case Mage -> {
                this.hp = 6;
                this.atk = 15;
            }
            case War -> {
                this.hp = 10;
                this.atk = 10;
            }
            case Murloc -> {
                this.hp = 1;
                this.atk = 1;
            }
        }
    }

    /*
    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }*/

}
