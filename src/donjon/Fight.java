package donjon;

import donjon.ennemi.Ennemi;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemOff.DefaultOff;
import donjon.equipement.itemOff.EquipementOff;
import donjon.personnage.Personnage;

import java.util.Scanner;

import static donjon.WaitSecAndASCII.slowPrint;

public class Fight {

    int playerHp;
    int playerAtk;
    int enemyHp;
    int enemyAtk;
    EquipementDef playerItemDef;
    EquipementOff playerItemOff;
    int playerPoseFight;

    Scanner scanner = new Scanner(System.in);

    public Fight(Personnage player, Ennemi enemy, int playerPose) {
        this.enemyHp = enemy.getHp();
        this.playerHp = player.getHp();
        this.enemyAtk = enemy.getAtk();
        this.playerAtk = player.getAtk();
        this.playerItemDef = player.getDefItem();
        this.playerItemOff = player.getOffItem();
        this.playerPoseFight = playerPose;

        useThunder(player);

        chooseItemOff(player);

        while (enemyHp > 0 && playerHp > 0) {
            playerTurn(enemy);
            if (enemyHp > 0 && enemyAtk > playerItemDef.getLevelDef()) {
                enemyTurn(player, enemy);
            }
            if (playerHp <= 0) {
                player.setAlive(false);
                return;
            } else if (enemyHp <= 0) {
                enemy.setAlive(false);
                slowPrint("Le cadavre de ton ennemi gît au sol, bien joué champion, continue ! \n");
                return;
            }
            if (stillAliveRun()) {
                return;
            }
        }
    }

    /**
     * Possibility for player to leave combat
     */
    private boolean stillAliveRun() {
        slowPrint("Il n'est pas mort ! Appuie sur 'Entrer' pour continuer le combat ou 'B' battre en retraite ? \n");
        String run = scanner.nextLine();
        if (run.equalsIgnoreCase("b")) {
            Game.playerPose = playerPoseFight - 2;
            slowPrint("Tu désengages le combat comme un champion, cours à une vitesse dingue, retourne 2 case en arrière dans la pièce " + Game.playerPose + ", voilà une fuite digne des plus grands lâches, bien joué ! \n");
            return true;
        }
        return false;
    }

    /**
     * Player turn during a fight
     */
    private void playerTurn(Ennemi enemy) {
        enemyHp = enemyHp - (playerAtk + playerItemOff.getPtsAtk() + playerItemOff.damageBoost(enemy));
        slowPrint("Tu frappes l'ennemi pour " + playerAtk + " + " + playerItemOff.getPtsAtk() + " + " + playerItemOff.damageBoost(enemy) + ", il a maintenant " + enemyHp + " point de vie \n");
        enemy.setHp(enemyHp);
    }

    /**
     * Enemy turn during fight
     */
    private void enemyTurn(Personnage player, Ennemi enemy) {
        if (enemy.canHit(player)) {
            playerHp = (playerHp - enemyAtk) + playerItemDef.getLevelDef();
            slowPrint("L'ennemi te frappe pour " + enemyAtk + ", tu es donc à " + playerHp + " point de vie \n");
            player.setHp(playerHp);
        } else {
            slowPrint(enemy.getType() + " : 'Oh non je ne peux pas taper les faibles " + player.getType() + ", stupide game-design ...' \n");
        }
    }

    private void chooseItemOff(Personnage player) {
        if (!(player.getOffItem() instanceof DefaultOff) || !(player.getOffItem2() instanceof DefaultOff)) {
//            slowPrint("Voici ce que tu as en ce moment comme arme : \n", 30);
            slowPrint("Appuie sur 'a' pour utiliser cette arme : " + player.getOffItem() + " \n");
            slowPrint("Appuie sur 'b' pour utiliser cette arme : " + player.getOffItem2() + " \n");
            String pick = scanner.nextLine();
            if (pick.equalsIgnoreCase("a")) {
                this.playerItemOff = player.getOffItem();
            } else if (pick.equalsIgnoreCase("b")) {
                this.playerItemOff = player.getOffItem2();
            } else {
                slowPrint("Saisie incorrecte \n");
                chooseItemOff(player);
            }
        }
    }

    private void useThunder(Personnage player) {
        if (player.getBuff().isActive() > 0) {
            slowPrint("Press 'a' pour utiliser ta " + player.getBuff().getNom() + " sur ce combat, sinon press 'b' \n");
            String buff = scanner.nextLine();
            if (buff.equalsIgnoreCase("a")) {
                playerAtk = player.getBuff().effect(playerAtk);
                player.getBuff().setDuration( player.getBuff().getDuration() - 1 );
            } else if (buff.equalsIgnoreCase("b")) {
                slowPrint("Elle sera conversé pour plus tard alors \n");
            } else {
                slowPrint("Saisie incorrecte");
                useThunder(player);
            }
        }
    }
}
