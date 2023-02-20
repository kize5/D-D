package donjon;

import donjon.ennemi.Dragon;
import donjon.ennemi.Ennemi;
import donjon.ennemi.Spectre;
import donjon.equipement.buff.ThunderPotion;
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

        chooseItemOff(player, enemy, playerPose);

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
                slowPrint("Le cadavre de ton ennemi gît au sol, bien joué champion, continue ! \n",30);
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
        slowPrint("Il n'est pas mort ! Appuie sur 'Entrer' pour continuer le combat ou 'B' battre en retraite ? \n", 30);
        String run = scanner.nextLine();
        if (run.equalsIgnoreCase("b")) {
            Game.playerPose = playerPoseFight - 2;
            slowPrint("Tu désengages le combat comme un champion, cours à une vitesse dingue, retourne 2 case en arrière dans la pièce " + Game.playerPose + ", voilà une fuite digne des plus grands lâches, bien joué ! \n",30);
            return true;
        } return false;
    }

    /**
     * Player turn during a fight
     */
    private void playerTurn(Ennemi enemy) {
        if (playerItemOff.damageBoost(enemy)) {
            if (enemy instanceof Dragon) {
                enemyHp = enemyHp - (playerAtk + playerItemOff.getPtsAtk() + 2);
                slowPrint("Tu frappes l'ennemi pour " + playerAtk + " + " + playerItemOff.getPtsAtk() + " + 2" + ", il a maintenant " + enemyHp + " point de vie \n", 30);
            } else if (enemy instanceof Spectre) {
                enemyHp = enemyHp - (playerAtk + playerItemOff.getPtsAtk() + 3);
                slowPrint("Tu frappes l'ennemi pour " + playerAtk+ " + " + playerItemOff.getPtsAtk() + " + 3" + ", il a maintenant " + enemyHp + " point de vie \n", 30);
            }
        } else {
            enemyHp = enemyHp - (playerAtk + playerItemOff.getPtsAtk());
            slowPrint("Tu frappes l'ennemi pour " + playerAtk+ " + " + playerItemOff.getPtsAtk() + ", il a maintenant " + enemyHp + " point de vie \n", 30);
        }
        enemy.setHp(enemyHp);
    }

    /**
     * Enemy turn during fight
     */
    private void enemyTurn(Personnage player, Ennemi enemy) {
        if (enemy.canHit(player)) {
            playerHp = (playerHp - enemyAtk) + playerItemDef.getLevelDef();
            slowPrint("L'ennemi te frappe pour " + enemyAtk + ", tu es donc à " + playerHp + " point de vie \n", 30);
            player.setHp(playerHp);
        } else {
            slowPrint(enemy.getType() + " : 'Oh non je ne peux pas taper les faibles " + player.getType() + ", stupide game-design ... \n",30);
        }
    }

    private void chooseItemOff (Personnage player, Ennemi enemy, int playerPose){
        if (!(player.getOffItem() instanceof DefaultOff) || !(player.getOffItem2() instanceof DefaultOff)) {
//            slowPrint("Voici ce que tu as en ce moment comme arme : \n", 30);
            slowPrint("Appuie sur 'a' pour utiliser cette arme : " + player.getOffItem() + " \n", 30);
            slowPrint("Appuie sur 'b' pour utiliser cette arme : " + player.getOffItem2() + " \n", 30);
            String pick = scanner.nextLine();
            if (pick.equalsIgnoreCase("a")) {
                this.playerItemOff = player.getOffItem();
            } else if (pick.equalsIgnoreCase("b")) {
                this.playerItemOff = player.getOffItem2();
            } else {
                slowPrint("Saisie incorrecte \n", 30);
                chooseItemOff(player, enemy, playerPose);
            }
        }
    }

    private void useThunder (Personnage player) {
        if (player.getBuff() instanceof ThunderPotion && player.getBuff().getDuration() > 0){
            slowPrint("Press 'a' pour utiliser ta ThunderPotion sur ce combat, sinon press 'b' \n",30);
            String buff = scanner.nextLine();
            if (buff.equalsIgnoreCase("a")) {
                playerAtk = playerAtk * 2;
                player.getBuff().setDuration(-1);
            } else if (buff.equalsIgnoreCase("b")) {
                slowPrint("Elle sera conversé pour plus tard alors \n",30);
            } else slowPrint("Saisie incorrecte",30);
             useThunder(player);
        }
    }
}
