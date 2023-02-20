package donjon.board;

import donjon.personnage.Personnage;

import java.util.Scanner;

public interface Case {

    void apply(Personnage player, int playerPose, Scanner scanner);
}
