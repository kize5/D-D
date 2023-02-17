package donjon.board;

import donjon.equipement.itemDef.Barriere;
import donjon.equipement.itemDef.Bouclier;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemDef.KindItemDef;
import donjon.equipement.itemOff.*;
import donjon.equipement.potion.*;
import donjon.personnage.Personnage;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

public class LootCase implements Case {
    EquipementOff lootOff;
    EquipementDef lootDeff;
    Potion potion;
    Personnage player;
    int rng;

    @Override
    public void apply(Personnage player, int playerPose) {
        this.player = player;
        randomLine();
        setRng();
        if (playerPose < 35) {
            generateNmLoot(rng);
        } else {
            generateHmLoot(rng);
        }

        slowPrint(player.toString(), 30);
    }


    public void generateNmLoot(int rng) {
        switch (rng) {
            case 0, 1 -> {
                lootOff = new Epee(KindItemOff.Sword, "lépékipique", 2);
                slowPrint(drawEpekipique(), 3);
                slowPrint("Wow tu viens de trouver lépékipique, attention, ça pique ...  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 2 -> {
                lootOff = new Epee(KindItemOff.Sword, "warglaives", 5);
                slowPrint(drawWarglaive(), 3);
                slowPrint("Tu viens de loot les warglaives ! Ils ne sont pas prêts ! \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 3 -> {
                lootOff = new FireBall(KindItemOff.Spell, "fire ball", 5);
                slowPrint(drawFireBall(), 3);
                slowPrint("Tu viens d'apprendre la fameuse boule de feu ! Attention à l'aoe \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 4 -> {
                lootOff = new FrostBolt(KindItemOff.Spell, "frost bolt", 3);
                slowPrint(drawLightningBlot(), 3);
                slowPrint("Tu viens de trouver un sort de givre, pas mal ça, tu vas les geler sur place !  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 5 -> {
                lootOff = new ArcaneBlast(KindItemOff.Spell, "arcane blast", 5);
                slowPrint(drawArcaneBlast(), 3);
                slowPrint("Oooh l'arcane blast, tu vas pouvoir frapper tes ennemies avec la magie arcanique !  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 6 -> {
                lootDeff = new Bouclier(KindItemDef.Bouclier, "rempart d'azzinoth", 2);
                slowPrint(drawShield(), 3);
                slowPrint("Tu viens de trouver le rempart d'azzinoth ! Bas ça c'est du solide ! \n", 30);
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 7 -> {
                lootDeff = new Barriere(KindItemDef.IceBarrier, "Ice barrier", 1);
                slowPrint(drawIceShield(), 3);
                slowPrint("Oh tu viens d'apprendre à faire une solide barrière de glace pour te protéger \n", 30);
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 8, 9, 10 -> {
                potion = new smallPotion(KindPotion.smallPotion, "Petit potion de soin");
                slowPrint(drawSmallPotion(), 3);
                slowPrint("Oh une petite potion de soin, tu gagnes 2 point de vie \n", 30);
                player.setHp(player.getHp() + 2);
            }
            case 11, 12 -> {
                potion = new mediumPotion(KindPotion.mediumPotion, "Moyenne potion de soin");
                slowPrint(drawMediumPotion(), 3);
                slowPrint("Oh une moyenne potion de soin, tu gagnes 3 point de vie \n", 30);
                player.setHp(player.getHp() + 3);
            }
            case 13 -> {
                potion = new largePotion(KindPotion.largePotion, "Grande potion de soin");
                slowPrint(drawLargePotion(), 3);
                slowPrint("Oh une grande potion de soin, tu gagnes 5 point de vie \n", 30);
                player.setHp(player.getHp() + 5);
            }
        }
    }

    public void generateHmLoot(int rng) {
        switch (rng) {
            case 0, 1 -> {
                lootOff = new Epee(KindItemOff.Sword, "lépékipique", 4);
                slowPrint(drawEpekipique(), 3);
                slowPrint("Wow tu viens de trouver lépékipique, attention, ça pique ...  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 2 -> {
                lootOff = new Epee(KindItemOff.Sword, "warglaives", 7);
                slowPrint(drawWarglaive(), 3);
                slowPrint("Tu viens de loot les warglaives ! Ils ne sont pas prêts ! \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 3 -> {
                lootOff = new FireBall(KindItemOff.Spell, "fire ball", 7);
                slowPrint(drawFireBall(), 3);
                slowPrint("Tu viens d'apprendre la fameuse boule de feu ! Attention à l'aoe \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 4 -> {
                lootOff = new FrostBolt(KindItemOff.Spell, "forst bolt", 4);
                slowPrint(drawLightningBlot(), 3);
                slowPrint("Tu viens de trouver un sort de givre, pas mal ça, tu vas les geler sur place !  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 5 -> {
                lootOff = new ArcaneBlast(KindItemOff.Spell, "arcane blast", 6);
                slowPrint(drawArcaneBlast(), 3);
                slowPrint("Oooh l'arcane blast, tu vas pouvoir frapper tes ennemies avec la magie arcanique !  \n", 30);
                if (player.isUsableEquipementOff(lootOff)) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 6 -> {
                lootDeff = new Bouclier(KindItemDef.Bouclier, "rempart d'azzinoth", 2);
                slowPrint(drawShield(), 3);
                slowPrint("Tu viens de trouver le rempart d'azzinoth ! Bas ça c'est du solide ! \n", 30);
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 7 -> {
                lootDeff = new Barriere(KindItemDef.IceBarrier, "Ice barrier", 2);
                slowPrint(drawIceShield(), 3);
                slowPrint("Oh tu viens d'apprendre à faire une solide barrière de glace pour te protéger \n", 30);
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 8, 9, 10 -> {
                potion = new smallPotion(KindPotion.smallPotion, "Petit potion de soin");
                slowPrint(drawSmallPotion(), 3);
                slowPrint("Oh une petite potion de soin, tu gagnes 2 point de vie \n", 30);
                player.setHp(player.getHp() + 2);
            }
            case 11, 12 -> {
                potion = new mediumPotion(KindPotion.mediumPotion, "Moyenne potion de soin");
                slowPrint(drawMediumPotion(), 3);
                slowPrint("Oh une moyenne potion de soin, tu gagnes 3 point de vie \n", 30);
                player.setHp(player.getHp() + 3);
            }
            case 13 -> {
                potion = new largePotion(KindPotion.largePotion, "Grande potion de soin");
                slowPrint(drawLargePotion(), 3);
                slowPrint("Oh une grande potion de soin, tu gagnes 5 point de vie \n", 30);
                player.setHp(player.getHp() + 5);
            }
        }
    }

    private void checkIfBetterOff() {
        if (lootOff.getPtsAtk() > player.getoffItem().getPtsAtk()) {
            player.setoffItem((lootOff));
        } else slowPrint("Mais tu as déjà mieux d'équipé pour tapper, pas grave tu pourras revendre ça à l'hv \n", 30);
    }

    private void checkIfBetterDef() {
        if (lootDeff.getLevelDef() > player.getDefItem().getLevelDef()) {
            player.setDefItem((lootDeff));
        } else
            slowPrint("Mais tu as déjà mieux d'équipé pour te défendre, pas grave tu pourras revendre ça à l'hv \n", 30);
    }

    private void notForYou() {
        slowPrint("Oups, tu as trouvé un truc, cool, mais tu ne sais pas t'en servir, dommage pour toi ... \n", 30);
    }

    public void setRng() {
        rng = ThreadLocalRandom.current().nextInt(0, 14);
    }

    private void randomLine() {
        Random random = new Random();
        int randomNum = random.nextInt(1, 5 + 1);
        switch (randomNum) {
            case 1 -> slowPrint("Il y a un truc brillant au sol ... Tu vas bien sûr voir ce que c'est ! \n", 30);
            case 2 ->
                    slowPrint("En arrivant la pièce, une créature difforme t'aperçois et prend rapidement la fuite, laissant un objet derrière elle \n", 30);
            case 3 -> {
                slowPrint("Au centre de la pièce, un bruit attire ton attention, sur les hauteurs, une chose ressemblant plus ou moins à un gobelin te regarde... \n", 30);
                slowPrint("Et d'un coup il te jete un objet que tu évites de justesse, suivie d'un bruit de coup sur l'arrière de la tête ... \n", 30);
                slowPrint("'Mais pourquoi tu lui as jeté ça abruti !' \n", 30);
                slowPrint("'Bas je voulais l'assommer ou je sais pas moi ...' \n", 30);
                slowPrint("'Avec ça !? Le patron va te tuer !' \n", 30);
            }
            case 4 ->
                    slowPrint("La pièce est sombre et humide, et dans un coin, le cadavre d'une femme, sans doute une aventurière malchanceuse, tu regardes ce qu'elle a sur elle qui pourrait te servir et ...  \n", 30);
            case 5 -> {
                slowPrint("Au détour d'un couloir, tu vois une pièce éclairé,, tu y passes et t'aperçois qu'elle est richement meublé, avec un beau lit, un bureau ... Mais surtout un coffre. \n", 30);
                slowPrint("Tu entres, fouille et ouvre le coffre ou tu trouves ... \n", 30);
            }
        }
    }
}
