package donjon.board;

import donjon.personnage.Personnage;
import static donjon.WaitSecAndASCII.slowPrint;
import java.util.concurrent.ThreadLocalRandom;

public class EmptyCase implements Case{


    @Override
    public void apply(Personnage player, int playerPose) {
        randomLine();
    }

    private void randomLine() {
        int randomNum = ThreadLocalRandom.current().nextInt(1,  6);
        switch (randomNum) {
            case 1 -> slowPrint("Une sourie passe et te fait sursauter, mais il n'y a rien ici \n", 30);
            case 2 -> slowPrint("La pièce est froide, une drôle d'odeur de cendre froide s'en dégage, mais il n'y a rien  \n",30);
            case 3 -> slowPrint("Entrant dans une nouvelle salle, tu entends une étrange musique, douce mais angoissante, provenant de nul part, dans cette pièce au mur de pière nu, avec simplement une bougie allumé en son centre, mais il n'y a personne \n", 30);
            case 4 -> slowPrint("En posant un pas dans cette salle vide, un grondement d'une créature inconnu raisonne au loin à travers tout le donjon \n", 30);
            case 5 -> slowPrint("Une salle sombre se présente devant toi, il n'y a rien au sol mais... En levant les yeux, tu distingues à travers l'obscurité que le plafond est grouillant d'araignée, plus grosse qu'une main, tu ferais mieux de ne pas trainer ici ...  \n", 30);
        }
    }
}
