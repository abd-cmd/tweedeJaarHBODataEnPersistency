package P5_Domein;

import P5_DAO_EN_DAOsql.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();

        Reiziger_DAO_SQL rdao = new Reiziger_DAO_SQL(connection);
        Adres_DAO_SQL adoa = new Adres_DAO_SQL(connection);
        Ov_chipkaart_DAO_SQL Odao = new Ov_chipkaart_DAO_SQL(connection);
        Product_DAO_SQL Pdao = new Product_DAO_SQL(connection);

        adoa.setRdao(rdao);
        Odao.setRdao(rdao);
        rdao.setAdao(adoa);
        rdao.setOdao(Odao);
        Pdao.setOdao(Odao);
        Odao.setPdao(Pdao);




        testReizigerDAO(rdao);
        testAdresDAO(adoa);
        testOv_chipkaartDAO(Odao);
        test_productDAO(Pdao);



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

    private static void testReizigerDAO(Reiziger_DAO rdao) throws SQLException {


        System.out.println("\n---------- Test ReizigerDAO -------------");



        // Maak een nieuwe reiziger aan en persisteer deze in de database
        // save
        String gbdatum = "1981-03-14";
        Date date = Date.valueOf(gbdatum);
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", date);
        Adres adres = new Adres(7,"3555VM","71","maasdijkstraat","Utrecht",sietske);
        String gboortdatum1 = "1981-03-14";
        Ov_chipkaart Ov_chipkaart = new Ov_chipkaart(77777,java.sql.Date.valueOf(gboortdatum1),3,25,sietske);


//            System.out.println(rdao.save(sietske));

        //Delete
//            rdao.delete(sietske);



        System.out.println();



        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        System.out.println();
        //Update
        String gboortdatum = "1998-09-17";
        Date date2 = Date.valueOf(gboortdatum);
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami", date2);
//            rdao.update(r);


        System.out.println();


        System.out.println();
        // findById
        System.out.println("findById");
        System.out.println(rdao.findById(3));


        System.out.println();

        //findByGbdatum

        System.out.println("findByGbdatum");
        String gboortdatum2 = "1998-08-11";
        Date date3 = Date.valueOf(gboortdatum2);
        System.out.println(rdao.findByGbdatum(date3));
        System.out.println();

        // Haal alle reizigers op uit de database
        //findAll
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println();
        System.out.println();


        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r1 : reizigers) {
            System.out.println(r1);
        }
        System.out.println();

        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");


    }


    private static void testAdresDAO(Adres_DAO adao)throws SQLException{

        System.out.println("\n---------- Test AdresDAO -------------");


        // Maak een nieuwe Adres aan en persisteer deze in de database

        System.out.println();
        //Save
//            String gboortdatum = "1998-09-17";
//            Date date1 = Date.valueOf(gboortdatum);
//            Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami",date1);
//            Adres adres = new Adres(6,"3555VM","71","maasdijkstraat","Utrecht",r);
//            adao.save(adres);
//
        System.out.println();
        //Delete

//            adao.delete(adres);

        //Update
//            Adres adres1 = new Adres(5,"3521BV","373 BISA","croeslaan","Utrecht",r);
//            adao.update(adres1);


        System.out.println();
        //findByReiziger
        String gboortdatum = "1998-09-17";
        Date date1 = Date.valueOf(gboortdatum);
        Reiziger r2 = new Reiziger(1,"A","Almoneim", "Alshami",date1);
        System.out.println(adao.findByReiziger(r2));

        System.out.println();

        System.out.println();

        // Haal alle Adresen op uit de database
        //findAll
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende Adressen:");
        for (Adres adres2: adressen) {
            System.out.println(adres2 );

        }
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");

        System.out.println();
        adressen = adao.findAll();
        System.out.println(adressen.size() + " Adressen\n");

    }


    private static void testOv_chipkaartDAO(Ov_chipkaart_DAO odao)throws SQLException{


        System.out.println("\n---------- Test Ov_chipkaartDAO -------------");


        // Maak een nieuwe ov_chipkaart aan en persisteer deze in de database
//            String gboortdatum = "1998-09-17";
//            Date date = Date.valueOf(gboortdatum);
//            Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami",date);
//            String gboortdatum2 = "2020-01-01";
//            Ov_chipkaart Ov_chipkaart = new Ov_chipkaart(44444,java.sql.Date.valueOf(gboortdatum2),3,25,r);

//            odao.save(Ov_chipkaart);

        //delete

//            odao.delete(Ov_chipkaart2);

        //update
//            Ov_chipkaart Ov_chipkaart2 = new Ov_chipkaart(44444,java.sql.Date.valueOf(gboortdatum2),4,50,r);
//            odao.update(Ov_chipkaart2);


        //findbyReiziger
        String gboortdatum1 = "1998-09-17";
        Date date1 = Date.valueOf(gboortdatum1);
        Reiziger r1 = new Reiziger(2,"A","Almoneim", "Alshami",date1);
        System.out.println(odao.findByReiziger(r1));
        System.out.println();

        System.out.println();
        System.out.println();

        // Haal alle ov_chipkaarts op uit de database
        //findAll
        List<Ov_chipkaart> ov_chipkaarts = odao.findAll();
        System.out.println("[Test] Ov_chipkaartDAO.findAll() geeft de volgende Ov_chipkaarten:");
        for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
            System.out.println(ov_chipkaart );

        }
        System.out.println();
        System.out.println();


    }

    private static void test_productDAO(Product_DAO Pdao)throws SQLException {
        System.out.println("\n---------- Test ProductDAO -------------");
        System.out.println();




//            System.out.println(Pdao.findByProduct_nummer(3));
        System.out.println();
        String geldig_tot = "2018-05-31";
        String gboortdatum = "2002-09-17";
        Date date1 = Date.valueOf(gboortdatum);
        Reiziger r = new Reiziger(1,"G","van", "Rijn",date1);
        Ov_chipkaart Ov_chipkaart = new Ov_chipkaart(35283,java.sql.Date.valueOf(geldig_tot),2,25,r);
        System.out.println(Pdao.findByOV_chipKaart(Ov_chipkaart));

        System.out.println();
        System.out.println();

        // Haal alle producten op uit de database
        //findAll
        List<Product> Products = Pdao.findAll();
        System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
        System.out.println(Products);
        for (Product Product : Products) {
            System.out.println(Product.getOv_chipkaarts() );

        }
        System.out.println();
        System.out.println();
    }

}
