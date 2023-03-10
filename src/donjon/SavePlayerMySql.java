package donjon;

import donjon.equipement.buff.Buff;
import donjon.equipement.buff.ThunderPotion;
import donjon.equipement.itemDef.*;
import donjon.equipement.itemOff.*;
import donjon.personnage.*;

import java.sql.*;
import java.util.Scanner;

import static donjon.WaitSecAndASCII.*;

public class SavePlayerMySql {
    //    public static void main(String[] args) throws Exception{
    //        Properties props = new Properties();
//        try ( FileInputStream fis = new FileInputStream("conf.properties")){
//            props.load( fis );
//        }
//        Class.forName( props.getProperty( "jdbc.driver.class" ) );
//
//        String url = props.getProperty( "jdbc.url");
//        String login = props.getProperty( "jdbc.login");
//        String password = props.getProperty( "jdbc.password");
//
//       Connection connection = DriverManager.getConnection(url,login,password);
    public void savePerso(Personnage player, Scanner scanner) {
        slowPrint("Voulez vous sauvegarder votre personnage ? \n");
        slowPrint("Oui : press 'a' \n");
        slowPrint("Non press 'b' \n");
        String saveOrNot = scanner.nextLine();
        if (saveOrNot.equalsIgnoreCase("a")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjon", "root", "");

                // ajouter item à la database, useless ...
//                String queryItemOff = "INSERT INTO item_off (type, nom, atk) VALUES (?, ?, ?)";
//                PreparedStatement pstmtItemoff = conn.prepareStatement(queryItemOff);
//                pstmtItemoff.setString(1, player.getOffItem().getType().toString()); //valueOf("Mage")
//                pstmtItemoff.setString(2, player.getOffItem().getNom());
//                pstmtItemoff.setInt(3, player.getOffItem().getPtsAtk());
//                pstmtItemoff.executeUpdate();

                //Récupère l'id en base de donnée de l'item off du joueur
                String requeteOff = "SELECT * FROM item_off WHERE nom = ?";
                PreparedStatement statement = conn.prepareStatement(requeteOff);
                statement.setString(1, player.getOffItem().getNom());
                ResultSet resultatOff = statement.executeQuery();
                resultatOff.next();
                int idItemOff = resultatOff.getInt("id");

                //Récupère l'id en base de donnée de l'item off "2" du joueur
                String requeteOff2 = "SELECT * FROM item_off WHERE nom = ?";
                PreparedStatement statementOff2 = conn.prepareStatement(requeteOff2);
                statementOff2.setString(1, player.getOffItem2().getNom());
                ResultSet resultatOff2 = statementOff2.executeQuery();
                resultatOff2.next();
                int idItemOff2 = resultatOff2.getInt("id");

                //Permets de boucler sur résultat si on cherche plusieurs éléments
//                while (resultat.next()) {
//                    int id = resultat.getInt("id");
//                    String type = resultat.getString("type");
//                    String nom = resultat.getString("nom");
//                    int atk = resultat.getInt("atk");
//                }

                //Récupère l'id en base de donnée de l'itemDef du joueur
                String requestDeff = "SELECT * FROM item_def WHERE nom = ?";
                PreparedStatement statementDeff = conn.prepareStatement(requestDeff);
                statementDeff.setString(1, player.getDefItem().getNom());
                ResultSet reslutatDeff = statementDeff.executeQuery();
                reslutatDeff.next();
                int idItemDeff = reslutatDeff.getInt("id");

                //Récupère l'id en base de donnée de l'item Buff du joueur
                String requestBuff = "SELECT * FROM buff WHERE duration = ?";
                PreparedStatement statementBuff = conn.prepareStatement(requestBuff);
                statementBuff.setInt(1, player.getBuff().getDuration());
                ResultSet resultatBuff = statementBuff.executeQuery();
                resultatBuff.next();
                int idItemBuff = resultatBuff.getInt("id");

                //insert if not exist
                deleteIfExist(conn, player);

                //Prépare la query qui va être envoyé à la base de donnée
                String queryPlayer = "INSERT INTO personnage (type, nom, hp, atk, is_alive, item_off_id, item_off2_id, item_def_id, buff_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmtPlayer = conn.prepareStatement(queryPlayer);

                //Set les valeurs de la query au-dessus en attente (Les ?,?,?,?)
                pstmtPlayer.setString(1, player.getType().toString()); //valueOf("Mage")
                pstmtPlayer.setString(2, player.getNom());
                pstmtPlayer.setInt(3, player.getHp());
                pstmtPlayer.setInt(4, player.getAtk());
                pstmtPlayer.setBoolean(5, player.isAlive());
                pstmtPlayer.setInt(6, idItemOff);
                pstmtPlayer.setInt(7, idItemOff2);
                pstmtPlayer.setInt(8, idItemDeff);
                pstmtPlayer.setInt(9, idItemBuff);

                //Execute la request
                pstmtPlayer.executeUpdate();
                slowPrint("Personnage sauvegardé ! \n");
            } catch (Exception ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            slowPrint("D'accord, alors bon chance pour la suite ! \n");
        }
    }

    private void deleteIfExist(Connection conn, Personnage player) {
        try {
            String request = "SELECT * FROM personnage WHERE nom = ?";
            PreparedStatement stat = conn.prepareStatement(request);
            stat.setString(1, player.getNom());
            ResultSet resultNom = stat.executeQuery();
            // add if plus tard ici
            if (resultNom.next()) {
                int nomtest = resultNom.getInt("id");

                String sql = "DELETE FROM personnage WHERE id = ?";
                PreparedStatement stati = conn.prepareStatement(sql);
                stati.setInt(1, nomtest);
                int rowsDeleted = stati.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("L'ancienne version du perso à était supprimé de la DB");
                }
            } else {
                System.out.println("Aucune ligne trouvé dans la DB avec ce pseudo");
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public Personnage getPersoFromDB(Scanner scanner) {
        slowPrint("Entrer le nom du personnage que tu veux récupérer : \n");
        String persoName = scanner.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/donjon", "root", "");


            String requete = "SELECT * FROM personnage WHERE nom = ?";
            PreparedStatement statement = conn.prepareStatement(requete);
            statement.setString(1, persoName);
            ResultSet result = statement.executeQuery();
            result.next();

            String typeStr = result.getString("type");
            KindClass type = KindClass.valueOf(typeStr);
            String nom = result.getString("nom");
            int hp = result.getInt("hp");
            int atk = result.getInt("atk");
            Boolean isAlive = result.getBoolean("is_alive");

            int idItemOff = result.getInt("item_off_id");
            int idItemOff2 = result.getInt("item_off2_id");
            int idItemdef = result.getInt("item_def_id");
            int idBuff = result.getInt("buff_id");

            //récupère item offensif "1" via l'id
            EquipementOff itemoff = getEquipOff(conn, idItemOff);
            //récupère item offensif "2" via l'id
            EquipementOff itemoff2 = getEquipOff(conn, idItemOff2);
            //Récupère item def
            EquipementDef itemDef = getEquipDef(conn, idItemdef);
            //Récupère item buff
            Buff itemBuff = getBuff(conn, idBuff);

            return getPersoFromDB(nom, type, hp, atk, itemoff, itemoff2, itemDef, itemBuff, isAlive);

        } catch (Exception e) {
            System.out.println("SQLException : " + e.getMessage());
        }
        return new Murloc("Bug", KindClass.Murloc, 5000, 5000);
    }

    /**
     * créer un nouvel équipement off similaire au précédent du joueur via l'id
     *
     * @param conn connection au SQL
     * @return l'item off
     */
    private EquipementOff getEquipOff(Connection conn, int idItemOff) {
        try {
            String requeteItemOff = "SELECT * FROM item_off WHERE id = ?";
            PreparedStatement statementItemOff = conn.prepareStatement(requeteItemOff);
            statementItemOff.setInt(1, idItemOff);
            ResultSet resItemOff = statementItemOff.executeQuery();
            resItemOff.next();
            String typeItemOff = resItemOff.getString("type");
            String nomItemOff = resItemOff.getString("nom");
            int atkItemOff = resItemOff.getInt("atk");
            String descItemOff = resItemOff.getString("drop_description");
            KindItemOff typeitoff = KindItemOff.valueOf(typeItemOff);
            return createItemoff(typeitoff, nomItemOff, atkItemOff, descItemOff);
        } catch (Exception e) {
            System.out.println("SQLerror" + e.getMessage());
        }
        return new DefaultOff();
    }

    private EquipementDef getEquipDef(Connection conn, int idItemdef) {
        try {
            String requeteItemDef = "SELECT * FROM item_def WHERE id = ?";
            PreparedStatement statementItemDef = conn.prepareStatement(requeteItemDef);
            statementItemDef.setInt(1, idItemdef);
            ResultSet resItemDef = statementItemDef.executeQuery();
            resItemDef.next();
            String typeItemDef = resItemDef.getString("type");
            String nomItemDef = resItemDef.getString("nom");
            int atkItemDef = resItemDef.getInt("def");
            KindItemDef typeitDef = KindItemDef.valueOf(typeItemDef);
            return createItemDef(typeitDef, nomItemDef, atkItemDef);
        } catch (Exception e) {
            System.out.println("SQLerror" + e.getMessage());
        }
        return new DefaultDef();
    }

    private Buff getBuff(Connection conn, int idBuff) {
        try {
            String requeteBuff = "SELECT * FROM buff WHERE id = ?";
            PreparedStatement statementBuff = conn.prepareStatement(requeteBuff);
            statementBuff.setInt(1, idBuff);
            ResultSet resBuff = statementBuff.executeQuery();
            resBuff.next();
            String nomBuff = resBuff.getString("nom");
            int durationBuff = resBuff.getInt("duration");
            return createBuff(durationBuff, nomBuff);
        } catch (Exception e) {
            System.out.println("SQLerror" + e.getMessage());
        }
        return new ThunderPotion(0, "Thunder potion");
    }

    //  Factory à perso
    private Personnage getPersoFromDB(String nom, KindClass type, int hp, int atk, EquipementOff offItem, EquipementOff offItem2, EquipementDef defItem, Buff buff, Boolean isAlive) {
        if (type == KindClass.Mage) {
            return new Mage(nom, type, hp, atk, offItem, offItem2, defItem, buff, isAlive);
        } else if (type == KindClass.War) {
            return new War(nom, type, hp, atk, offItem, offItem2, defItem, buff, isAlive);
        } else {
            {
                return new Murloc(nom, type, hp, atk, offItem, offItem2, defItem, buff, isAlive);
            }
        }
    }

    // factory à equipment offensif
    private EquipementOff createItemoff(KindItemOff type, String nom, int atk, String desc) {
        if (type == KindItemOff.Sword) {
            return new Epee(type, nom, atk, desc);
        } else if (type == KindItemOff.Arc) {
            return new Arc(type, nom, atk, desc);
        } else if (type == KindItemOff.Spell && nom.equalsIgnoreCase("Fire ball")) {
            return new FireBall(type, nom, atk, desc);
        } else if (type == KindItemOff.Spell && nom.equalsIgnoreCase("Frost bolt")) {
            return new FrostBolt(type, nom, atk, desc);
        } else if (type == KindItemOff.Spell && nom.equalsIgnoreCase("Arcane blast")) {
            return new ArcaneBlast(type, nom, atk, desc);
        } else {
            return new DefaultOff();
        }
    }

    //factory item def
    private EquipementDef createItemDef(KindItemDef type, String nom, int def) {
        if (type == KindItemDef.Bouclier) {
            return new Bouclier(type, nom, def);
        } else if (type == KindItemDef.IceBarrier) {
            return new Barriere(type, nom, def);
        } else {
            return new DefaultDef();
        }
    }

    //factory buff
    private Buff createBuff(int duration, String nom) {
        if (nom.equalsIgnoreCase("Thunder Potion")) {
            return new ThunderPotion(duration, nom);
        } else return new ThunderPotion(0, nom);
    }

}


