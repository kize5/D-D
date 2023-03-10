package donjon.board;

import donjon.equipement.buff.Buff;
import donjon.equipement.buff.ThunderPotion;
import donjon.equipement.itemDef.Barriere;
import donjon.equipement.itemDef.Bouclier;
import donjon.equipement.itemDef.EquipementDef;
import donjon.equipement.itemDef.KindItemDef;
import donjon.equipement.itemOff.*;
import donjon.equipement.potion.*;
import donjon.personnage.Personnage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static donjon.WaitSecAndASCII.*;

public class LootCase implements Case {
    EquipementDef lootDeff;
    Buff buff;
    Potion potion;
    Personnage player;
    int rng;
    int rngTestForList;
    Scanner scanner;

    ArrayList<EquipementOff> listLootOff;

    @Override
    public void apply(Personnage player, int playerPose, Scanner scanner) {
        this.player = player;
        this.scanner = scanner;
        this.listLootOff = new ArrayList<>();
        callDBForLoot();
        randomLine();
        setRng();
//        if (playerPose < 35) {
        generateNmLoot(rng);
//        } else {
//            generateHmLoot(rng);
//        }
        slowPrint(player.toString());
    }

    private void callDBForLoot() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjon", "root", "");
            String requeteOff = "SELECT * FROM item_off";
            PreparedStatement statement = conn.prepareStatement(requeteOff);
//            statement.setString(1, player.getOffItem().getNom());
            ResultSet resultatOff = statement.executeQuery();
            while (resultatOff.next()) {
//                int idItemOff = resultatOff.getInt("id");
                String classItemOff = resultatOff.getString("item_class");
                String typeItemOff = resultatOff.getString("type");
                KindItemOff type = KindItemOff.valueOf(typeItemOff);
                String nomItemOff = resultatOff.getString("nom");
                String descItemOff = resultatOff.getString("drop_description");
                int atkItemOff = resultatOff.getInt("atk");

                Class<?> itemOffClass = Class.forName(classItemOff);
                Object itemOffObject = null;
                try {
                    itemOffObject = itemOffClass.getConstructor(KindItemOff.class, String.class, int.class, String.class).newInstance(type, nomItemOff, atkItemOff,descItemOff);
                } catch (NoSuchMethodException e) {
                    itemOffObject = itemOffClass.getDeclaredConstructor().newInstance();
                }
                if (itemOffObject instanceof EquipementOff) {
                    listLootOff.add((EquipementOff) itemOffObject);
                }
//                System.out.println(classItemOff + " " + nomItemOff  + " " + type + " " + atkItemOff);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void lootOffTableFromDB() {
        setRngTestForLIist();
        EquipementOff itemOffFromDB = listLootOff.get(rngTestForList);
        asciiFactory(itemOffFromDB);
        slowPrint(itemOffFromDB.getDesc());
        if (player.isUsableEquipementOff(itemOffFromDB)) {
            findLootOff(itemOffFromDB);
        } else notForYou();
    }

    private void asciiFactory(EquipementOff offitem) {
        if (offitem.getNom().equalsIgnoreCase("L??p??kipique")) {
            slowPrintForAscii(drawEpekipique());
        } else if (offitem.getNom().equalsIgnoreCase("Warglaives")) {
            slowPrintForAscii(drawWarglaive());
        } else if (offitem.getNom().equalsIgnoreCase("Fire ball")) {
            slowPrintForAscii(drawFireBall());
        } else if (offitem.getNom().equalsIgnoreCase("Frost bolt")) {
            slowPrintForAscii(drawFrostBlot());
        } else if (offitem.getNom().equalsIgnoreCase("Arcane blast")) {
            slowPrintForAscii(drawArcaneBlast());
        } else {
            slowPrintForAscii(drawBow());
        }
    }

    public void generateNmLoot(int rng) {
        switch (rng) {
            case 0,1,2,3,4,5,14 -> lootOffTableFromDB();
            case 6 -> {
                lootDeff = new Bouclier(KindItemDef.Bouclier, "Rempart d'azzinoth", 2);
                slowPrintForAscii(drawShield());
                slowPrint("Tu viens de trouver le rempart d'azzinoth ! Bas ??a c'est du solide ! \n");
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 7 -> {
                lootDeff = new Barriere(KindItemDef.IceBarrier, "Ice barrier", 1);
                slowPrintForAscii(drawIceShield());
                slowPrint("Oh tu viens d'apprendre ?? faire une solide barri??re de glace pour te prot??ger \n");
                if (player.isUsableEquipementDeff(lootDeff)) {
                    checkIfBetterDef();
                } else notForYou();
            }
            case 8, 9, 10 -> {
                potion = new smallPotion(KindPotion.smallPotion, "Petit potion de soin");
                slowPrintForAscii(drawSmallPotion());
                slowPrint("Oh une petite potion de soin, tu gagnes 2 point de vie \n");
                player.setHp(player.getHp() + 2);
            }
            case 11, 12 -> {
                potion = new mediumPotion(KindPotion.mediumPotion, "Moyenne potion de soin");
                slowPrintForAscii(drawMediumPotion());
                slowPrint("Oh une moyenne potion de soin, tu gagnes 3 point de vie \n");
                player.setHp(player.getHp() + 3);
            }
            case 13 -> {
                potion = new largePotion(KindPotion.largePotion, "Grande potion de soin");
                slowPrintForAscii(drawLargePotion());
                slowPrint("Oh une grande potion de soin, tu gagnes 5 point de vie \n");
                player.setHp(player.getHp() + 5);
            }
            case 15 -> {
                buff = new ThunderPotion(1, "Thunder Potion");
                slowPrintForAscii(drawThunderPotion());
                slowPrint("Tu viens de trouver une ThunderPotion ! Tes d??gats seront doubl?? au prochain combat");
                player.setBuff(buff);
            }
        }
    }

//    public void generateHmLoot(int rng) {
//        switch (rng) {
//            case 0, 1 -> {
//                lootOff = new Epee(KindItemOff.Sword, "L??p??kipique", 4);
//                slowPrint(drawEpekipique(), 3);
//                slowPrint("Wow tu viens de trouver L??p??kipique version hero, attention, ??a pique ...  \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//            case 2 -> {
//                lootOff = new Epee(KindItemOff.Sword, "Warglaives", 7);
//                slowPrint(drawWarglaive(), 3);
//                slowPrint("Tu viens de loot les Warglaives version hero ! Ils ne sont pas pr??ts ! \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//            case 3 -> {
//                lootOff = new FireBall(KindItemOff.Spell, "Fire ball", 7);
//                slowPrint(drawFireBall(), 3);
//                slowPrint("Tu viens d'apprendre la fameuse boule de feu version hero ! Attention ?? l'aoe \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//            case 4 -> {
//                lootOff = new FrostBolt(KindItemOff.Spell, "Forst bolt", 4);
//                slowPrint(drawFrostBlot(), 3);
//                slowPrint("Tu viens de trouver un sort de givre version hero, pas mal ??a, tu vas les geler sur place !  \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//            case 5 -> {
//                lootOff = new ArcaneBlast(KindItemOff.Spell, "Arcane blast", 6);
//                slowPrint(drawArcaneBlast(), 3);
//                slowPrint("Oooh l'arcane blast version hero, tu vas pouvoir frapper tes ennemies avec la magie arcanique !  \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//            case 6 -> {
//                lootDeff = new Bouclier(KindItemDef.Bouclier, "Rempart d'azzinoth", 2);
//                slowPrint(drawShield(), 3);
//                slowPrint("Tu viens de trouver le rempart d'azzinoth version hero ! Bas ??a c'est du solide ! \n", 30);
//                if (player.isUsableEquipementDeff(lootDeff)) {
//                    checkIfBetterDef();
//                } else notForYou();
//            }
//            case 7 -> {
//                lootDeff = new Barriere(KindItemDef.IceBarrier, "Ice barrier", 2);
//                slowPrint(drawIceShield(), 3);
//                slowPrint("Oh tu viens d'apprendre ?? faire une solide barri??re de glace version hero pour te prot??ger \n", 30);
//                if (player.isUsableEquipementDeff(lootDeff)) {
//                    checkIfBetterDef();
//                } else notForYou();
//            }
//            case 8, 9, 10 -> {
//                potion = new smallPotion(KindPotion.smallPotion, "Petit potion de soin");
//                slowPrint(drawSmallPotion(), 3);
//                slowPrint("Oh une petite potion de soin, tu gagnes 2 point de vie \n", 30);
//                player.setHp(player.getHp() + 2);
//            }
//            case 11, 12 -> {
//                potion = new mediumPotion(KindPotion.mediumPotion, "Moyenne potion de soin");
//                slowPrint(drawMediumPotion(), 3);
//                slowPrint("Oh une moyenne potion de soin, tu gagnes 3 point de vie \n", 30);
//                player.setHp(player.getHp() + 3);
//            }
//            case 13 -> {
//                potion = new largePotion(KindPotion.largePotion, "Grande potion de soin");
//                slowPrint(drawLargePotion(), 3);
//                slowPrint("Oh une grande potion de soin, tu gagnes 5 point de vie \n", 30);
//                player.setHp(player.getHp() + 5);
//            }
//            case 14 -> {
//                lootOff = new Arc(KindItemOff.Arc, "Rae???shalare", 5);
//                slowPrint(drawBow(), 3);
//                slowPrint("Tu viens de mettre la main sur un bel arc version hero, il a peut ??tre appartenu ?? un personnage embl??matique au destin tragique ? Qui sait ..  \n", 30);
//                if (player.isUsableEquipementOff(lootOff)) {
//                    findLootOff();
//                } else notForYou();
//            }
//        }
//    }

    private void findLootOff(EquipementOff lootOffFromDB) {
        slowPrint("Tu viens de trouver " + lootOffFromDB + " ! \n");
        slowPrint("Voici ce que tu as en ce moment : \n");
        slowPrint("Appuie sur 'a' pour remplacer cet ??quipement : " + player.getOffItem() + " \n");
        slowPrint("Appuie sur 'b' pour remplacer cet ??quipement : " + player.getOffItem2() + " \n");
        slowPrint("Sinon tape 'drop' pour le laisser au sol \n");
        String pick = scanner.nextLine();
        if (pick.equalsIgnoreCase("a")) {
            player.setOffItem((lootOffFromDB));
        } else if (pick.equalsIgnoreCase("b")) {
            player.setOffItem2(lootOffFromDB);
        } else if (pick.equalsIgnoreCase("drop")) {
            slowPrint("Tu laisses l'item au sol, quel g??chis ... \n");
        } else {
            slowPrint("Saisie incorrecte \n");
            findLootOff(lootOffFromDB);
        }
    }

    private void checkIfBetterDef() {
        if (lootDeff.getLevelDef() > player.getDefItem().getLevelDef()) {
            player.setDefItem((lootDeff));
        } else
            slowPrint("Mais tu as d??j?? mieux d'??quip?? pour te d??fendre, pas grave tu pourras revendre ??a ?? l'hv \n");
    }

    private void notForYou() {
        slowPrint("Oups, tu as trouv?? un truc, cool, mais tu ne sais pas t'en servir, dommage pour toi ... \n");
    }

    public void setRng() {
        rng = ThreadLocalRandom.current().nextInt(0, 16);
    }

    private void setRngTestForLIist() {
        rngTestForList = ThreadLocalRandom.current().nextInt(0, listLootOff.size());
    }

    private void randomLine() {
        Random random = new Random();
        int randomNum = random.nextInt(1, 5 + 1);
        switch (randomNum) {
            case 1 -> slowPrint("Il y a un truc brillant au sol ... Tu vas bien s??r voir ce que c'est ! \n");
            case 2 ->
                    slowPrint("En arrivant la pi??ce, une cr??ature difforme t'aper??ois et prend rapidement la fuite, laissant un objet derri??re elle \n");
            case 3 -> {
                slowPrint("Au centre de la pi??ce, un bruit attire ton attention, sur les hauteurs, une chose ressemblant plus ou moins ?? un gobelin te regarde... \n");
                slowPrint("Et d'un coup il te jete un objet que tu ??vites de justesse, suivie d'un bruit de coup sur l'arri??re de la t??te ... \n");
                slowPrint("'Mais pourquoi tu lui as jet?? ??a abruti !' \n");
                slowPrint("'Bas je voulais l'assommer ou je sais pas moi ...' \n");
                slowPrint("'Avec ??a !? Le patron va te tuer !' \n");
            }
            case 4 ->
                    slowPrint("La pi??ce est sombre et humide, et dans un coin, le cadavre d'une femme, sans doute une aventuri??re malchanceuse, tu regardes ce qu'elle a sur elle qui pourrait te servir et ...  \n");
            case 5 -> {
                slowPrint("Au d??tour d'un couloir, tu vois une pi??ce ??clair??,, tu y passes et t'aper??ois qu'elle est richement meubl??, avec un beau lit, un bureau ... Mais surtout un coffre. \n");
                slowPrint("Tu entres, fouille et ouvre le coffre ou tu trouves ... \n");
            }
        }
    }
}
