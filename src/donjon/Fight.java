package donjon;

import donjon.ennemi.Ennemi;
import donjon.personnage.Personnage;

public class Fight {

    int playerHp;
    int playerAtk;
    int enemyHp;
    int enemyAtk;

    public Fight(Personnage player, Ennemi enemy) {
        this.enemyHp = enemy.getHp();
        this.playerHp = player.getHp();
        this.enemyAtk = enemy.getAtk();
        this.playerAtk = player.getAtk();

        while (enemyHp > 0 && playerHp > 0) {
            if (enemyHp > 0) {
                enemyHp = enemyHp - playerAtk;
                enemy.setHp(enemyHp);
                if (enemyHp > 0) {
                    playerHp = playerHp - enemyAtk;
                    player.setHp(playerHp);
                }
            }
        }
        if (playerHp < 0) {
            player.setAlive(false);
        } else enemy.setAlive(false);
    }
}
