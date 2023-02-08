public class Personnage {

    String nom;
    String type;
    int hp;
    int atk;

    protected Personnage() {

    }

    public Personnage(String nom) {
        this.nom = nom;
    }

    public Personnage(String nom, String type) {
        this.type = type;
        this.nom = nom;
        if (nom == "mage") {
            this.hp = 6;
            this.atk = 15;
        }
        if (nom == "war") {
            this.hp = 10;
            this.atk = 10;
        }
    }

    public void getNameClass(String name, String classe) {
        this.nom = name;
        this.type = classe;
    }

}
