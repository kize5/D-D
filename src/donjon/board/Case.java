package donjon.board;

import donjon.personnage.Personnage;

public interface Case {

    void apply(Personnage player, int playerPose);
}
