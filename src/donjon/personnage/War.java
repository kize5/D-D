package donjon.personnage;

import donjon.KindEnnemi;

/**
 * Class to create a new war
 */
public class War extends Personnage{

    /**
     * Construct to create a new war
     * @param nom Nom of new war
     * @param type Type of new war
     * @param hp Heal points of new war
     * @param atk Attack points of new war
     */
    public War(String nom, KindEnnemi type, int hp, int atk) {
        super(nom, type, hp, atk);
    }
}
