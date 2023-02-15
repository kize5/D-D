package donjon;

import donjon.personnage.Personnage;

public class EmptyCase implements Case{

    public EmptyCase () {
        System.out.println("Je suis une case vide");
    }

    @Override
    public void apply(Personnage player) {
        System.out.println("Empty cell nothing to do...");
    }
}
