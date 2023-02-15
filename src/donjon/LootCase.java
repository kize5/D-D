package donjon;

import donjon.equipement.*;
import donjon.personnage.Personnage;

import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.slowPrint;

public class LootCase implements Case{
    EquipementOff lootOff;
    EquipementDef lootDeff;
    Potion potion;
    private boolean canUseIt = true;
    Personnage player;
    int rng;

    public LootCase () {
        slowPrint("je suis dans loot case \n", 30);
    }
    @Override
    public void apply(Personnage player) {
        this.player = player;
        setRng();
        generateLoot(rng);
        slowPrint("FROM Apply, fat need loot please \n", 30);
        System.out.println(player);
    }


    public void generateLoot(int rng) {
        switch (rng) {
            case 0, 1 -> {
                lootOff = new Arme(KindItemOff.Sword, "lépékipique", 3);
                slowPrint("Wow tu viens de trouver lépékipique, attention, ça pique ...  \n", 30);
                if (player.getType() == KindEnnemi.War || player.getType() == KindEnnemi.Murloc) {
                checkIfBetterOff();
                } else notForYou();
            }
            case 2 -> {
                lootOff = new Arme(KindItemOff.Sword, "warglaives", 6);
                slowPrint("Tu viens de loot les warglaives ! Ils ne sont pas prêts ! \n", 30);
                if (player.getType() == KindEnnemi.War || player.getType() == KindEnnemi.Murloc) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 3 -> {
                lootOff = new Sort(KindItemOff.Spell, "fire ball", 7);
                slowPrint("Tu viens d'apprendre la fameuse boule de feu ! Attention à l'aoe \n", 30);
                if (player.getType() == KindEnnemi.Mage || player.getType() == KindEnnemi.Murloc) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 4, 5 -> {
                lootOff = new Sort(KindItemOff.Spell, "lightning blot", 4);
                slowPrint("Tu viens de trouver le sort éclair, pas mal ça  \n", 30);
                if (player.getType() == KindEnnemi.Mage || player.getType() == KindEnnemi.Murloc) {
                    checkIfBetterOff();
                } else notForYou();
            }
            case 6 -> {
                lootDeff = new Bouclier(KindItemDef.Bouclier, "rempart d'azzinoth", 8);
                slowPrint("Tu viens de trouver le rempart d'azzinoth ! Bas ça c'est du solide ! \n", 30);
                if (player.getType() == KindEnnemi.War || player.getType() == KindEnnemi.Murloc) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 7 -> {
                lootDeff = new Bouclier(KindItemDef.IceBarrier, "Ice barrier", 5);
                slowPrint("Oh tu viens d'apprendre à faire une solide barrière de glace pour te protéger \n", 30);
                if (player.getType() == KindEnnemi.Mage || player.getType() == KindEnnemi.Murloc) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 8, 9, 10 -> {
                potion = new smallPotion(KindPotion.smallPotion, "Petit potion de soin");
                slowPrint("Oh une petite potion de soin, tu gagnes 2 point de vie", 30);
                player.setHp(player.getHp()+2);
            }
            case 11, 12 -> {
                potion = new mediumPotion(KindPotion.mediumPotion, "Moyenne potion de soin");
                slowPrint("Oh une moyenne potion de soin, tu gagnes 3 point de vie", 30);
                player.setHp(player.getHp()+3);
            }
            case 13 -> {
                potion = new largePotion(KindPotion.largePotion, "Grande potion de soin");
                slowPrint("Oh une grande potion de soin, tu gagnes 5 point de vie", 30);
                player.setHp(player.getHp()+5);
            }
        }
    }

    private void checkIfBetterOff() {
        if (lootOff.getPtsAtk() > player.getoffItem()) {
            player.setoffItem((lootOff.getPtsAtk()));
        } else slowPrint("Mais tu as déjà mieux d'équipé pour tapper, pas grave tu pourras revendre ça à l'hv \n", 30);
    }
    private void checkIfBetterDef() {
        if (lootDeff.getLevelDef() > player.getDefItem()) {
            player.setDefItem((lootDeff.getLevelDef()));
        } else slowPrint("Mais tu as déjà mieux d'équipé pour te défendre, pas grave tu pourras revendre ça à l'hv \n", 30);
    }

    private void notForYou() {
        slowPrint("Oups, tu as trouvé un truc, cool, mais tu ne sais pas t'en servir, dommage pour toi ...", 30);
    }

    public void setRng() {
        rng = ThreadLocalRandom.current().nextInt(0 ,14);
    }
}
