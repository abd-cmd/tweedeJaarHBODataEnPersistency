package P3Domein;

import P2DAOsql.ReizigerDAOsql;
import P2Domein.Reiziger;
import P3DAO.AdresDAO;
import P3DAO.ReizgerDAO2;
import P3DAOsql.AdresDAOsql;
import P3DAOsql.ReizigerDAOsql2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main2 {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        ReizigerDAOsql2 d1 = new ReizigerDAOsql2(connection);
        testReizigerDAO(d1);
        AdresDAOsql A1 = new AdresDAOsql(connection);
        testAdresDAO(A1);

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

    private static void testReizigerDAO(ReizgerDAO2 rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger2> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger2 r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger2 sietske = new Reiziger2(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        String gboortdatum = "1998-09-17";
        Reiziger2 r = new Reiziger2(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum));
       // System.out.println(rdao.update(r));
        System.out.println("het werkt");
       // System.out.println(rdao.delete(new Reiziger(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf("1998-09-17"))));
        System.out.println("het werkt");
        rdao.delete(sietske);
        System.out.println(rdao.findById(3));
        System.out.println("het werkt");
        String gb = "2002-12-03";
        System.out.println(rdao.findByGbdatum(gb));
//



    }


    private static void testAdresDAO(AdresDAO adao)throws SQLException{

        System.out.println("\n---------- Test AdresDAO -------------");

        // Haal alle Adresen op uit de database
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende Adressen:");
        for (Adres adres : adressen) {
            System.out.println(adres);
        }
        System.out.println();


        // Maak een nieuwe Adres aan en persisteer deze in de database
        Adres adres = new Adres(6,"3555VM","71","maasdijkstraat","Utrecht",10);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(adres);
        adressen = adao.findAll();
        System.out.println(adressen.size() + " Adressen\n");
        String gboortdatum = "1998-09-17";

        Reiziger2 r = new Reiziger2(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum));

        System.out.println(adao.findByReiziger(r));

        System.out.println(adao.delete(adres));
        Adres adres1 = new Adres(5,"3521BV","373 BISA","croeslaan","Utrecht",5);
        adao.update(adres1);


    }
}
