package donjon;

import donjon.ennemi.Ennemi;
import donjon.personnage.Personnage;

public class Fight {

    int playerHp;
    int playerAtk;
    int enemyHp;
    int enemyAtk;
    int playerItemDef;
    int playerItemOff;

    public Fight(Personnage player, Ennemi enemy) {
        this.enemyHp = enemy.getHp();
        this.playerHp = player.getHp();
        this.enemyAtk = enemy.getAtk();
        this.playerAtk = player.getAtk();
        this.playerItemDef = player.getDefItem();
        this.playerItemOff = player.getoffItem();

        while (enemyHp > 0 && playerHp > 0) {
            enemyHp = enemyHp - (playerAtk + playerItemOff);
            enemy.setHp(enemyHp);
            if (enemyHp > 0 && enemyAtk > playerItemDef) {
                playerHp = (playerHp - enemyAtk) + playerItemDef;
                player.setHp(playerHp);
            }
        }
        if (playerHp < 0) {
            player.setAlive(false);
        } else enemy.setAlive(false);
    }
}
