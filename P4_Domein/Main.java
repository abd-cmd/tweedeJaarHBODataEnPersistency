package P4_Domein;


import P4_DAO_EN_DAOSQL.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();

        ReizigerDAOsql rdao = new ReizigerDAOsql(connection);
        AdresDAOsql adoa = new AdresDAOsql(connection);
        Ov_chipkaartDAOsql Odao = new Ov_chipkaartDAOsql(connection);



        adoa.setRdao(rdao);
        Odao.setRdao(rdao);
        rdao.setAdao(adoa);
        rdao.setOdao(Odao);


        testReizigerDAO(rdao);
        testAdresDAO(adoa);
        testOv_chipkaartDAO(Odao);

        Main.closeConnection();

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
        Date date = Date.valueOf(gbdatum);
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", date);
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        System.out.println("het werkt");

        System.out.println();

        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        System.out.println();
        //Update
        String gboortdatum = "1998-09-17";
        Date date2 = Date.valueOf(gboortdatum);
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami", date2);
        rdao.update(r);
        System.out.println("het werkt");
        System.out.println();

        //Delete
        rdao.delete(sietske);


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
        Date date1 = Date.valueOf(gboortdatum);
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami",date1);


        // Maak een nieuwe Adres aan en persisteer deze in de database

        System.out.println();
        //Save
        String gboortdatum2 = "1998-09-17";
        Date date2 = Date.valueOf(gboortdatum2);
        Reiziger r2 = new Reiziger(1,"A","Almoneim", "Alshami",date2);
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


    private static void testOv_chipkaartDAO(Ov_chipkaartDAO odao)throws SQLException{



        System.out.println("\n---------- Test Ov_chipkaartDAO -------------");


        // Maak een nieuwe ov_chipkaart aan en persisteer deze in de database
        String gboortdatum1 = "1998-09-17";
        Date date1 = Date.valueOf(gboortdatum1);
        Reiziger r5 = new Reiziger(150,"A","Almoneim", "Alshami", date1);
        String gboortdatum2 = "2020-01-01";
        Ov_chipkaart Ov_chipkaart = new Ov_chipkaart(44444,java.sql.Date.valueOf(gboortdatum2),3,25,r5);
        odao.save(Ov_chipkaart);


        // Haal alle ov_chipkaarts op uit de database
        //findAll
        List<Ov_chipkaart> ov_chipkaarts = odao.findAll();
        System.out.println("[Test] Ov_chipkaartDAO.findAll() geeft de volgende Ov_chipkaarten:");
        for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
            System.out.println(ov_chipkaart );

        }


        //update
        Ov_chipkaart Ov_chipkaart2 = new Ov_chipkaart(44444,java.sql.Date.valueOf(gboortdatum2),4,50,r5);
        odao.update(Ov_chipkaart2);

        //findbyReiziger
        odao.findByReiziger(r5);

        //delete
        odao.delete(Ov_chipkaart2);


    }




}
