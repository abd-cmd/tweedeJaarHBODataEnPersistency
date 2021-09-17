package P3_Domein;

import P3_DAO_EN_DAOSQL.AdresDAO;
import P3_DAO_EN_DAOSQL.AdresDAOsql;
import P3_DAO_EN_DAOSQL.ReizgerDAO;
import P3_DAO_EN_DAOSQL.ReizigerDAOsql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        ReizigerDAOsql d1 = new ReizigerDAOsql(connection);
        AdresDAOsql A1 = new AdresDAOsql(connection);
        d1.setAdao(A1);
        testReizigerDAO(d1);
        testAdresDAO(A1);
        P2_Domein.Main.closeConnection();


    }

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            String Password = "Pass.123%25";
            String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=" + Password + "";
            String user = "postgres";
            Connection Con = DriverManager.getConnection(url, user, Password);
            return Con;
        }
        String Password = "Pass.123%25";
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=" + Password + "";
        String user = "postgres";
        try {

            Connection Con = DriverManager.getConnection(url, user, Password);
            return Con;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public static Connection closeConnection(){
        try {

            connection.close();
            return connection;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private static void testReizigerDAO(ReizgerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        //findAll
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();





        // Maak een nieuwe reiziger aan en persisteer deze in de database
        // save
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
      //  rdao.save(sietske);
        System.out.println("het werkt");

        System.out.println();

        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        System.out.println();
        //Update
        String gboortdatum = "1998-09-17";
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum));
        System.out.println(rdao.update(r));
        System.out.println("het werkt");
        System.out.println();
        //Delete
//        System.out.println(rdao.delete(sietske));
        System.out.println("het werkt");

        System.out.println();
        // findById
        System.out.println(rdao.findById(3));
        System.out.println("het werkt");

        System.out.println();
        //findByGbdatum
        String gboortdatum2 = "1998-09-17";
        Date date3 = Date.valueOf(gboortdatum2);
        System.out.println(rdao.findByGbdatum(date3));
        System.out.println("het werkt");


    }


    private static void testAdresDAO(AdresDAO adao)throws SQLException{

        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle Adresen op uit de database
        //findAll
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende Adressen:");
        for (Adres adres : adressen) {
            System.out.println(adres );

        }
        String gboortdatum = "1998-09-17";
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum));


        // Maak een nieuwe Adres aan en persisteer deze in de database

        System.out.println();
        //Save
        String gboortdatum2 = "1998-09-17";
        Reiziger r2 = new Reiziger(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum2));
        Adres adres = new Adres(6,"3555VM","71","maasdijkstraat","Utrecht",r);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(adres);

        System.out.println();
        adressen = adao.findAll();
        System.out.println(adressen.size() + " Adressen\n");

        System.out.println();
        //findByReiziger
        System.out.println(adao.findByReiziger(r));

        System.out.println();
        //Delete
        adao.delete(adres);

        System.out.println();
        //Update
        Adres adres1 = new Adres(5,"3521BV","373 BISA","croeslaan","Utrecht",r2);
        adao.update(adres1);




    }
}
