package P2_Domein;


import P2_DAO_EN_DAOSAL.ReizgerDAO;
import P2_DAO_EN_DAOSAL.ReizigerDAOsql;

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
        testReizigerDAO(d1);
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
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Date date = Date.valueOf(gbdatum);
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", date);
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);


        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        // update
        String gboortdatum = "1998-09-17";
        Date date2 = Date.valueOf(gboortdatum);
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami",date2 );
        System.out.println(rdao.update(r));
        System.out.println("het werkt");

        //delete
        rdao.delete(sietske);
        System.out.println("het werkt");

        //FindById
        System.out.println(rdao.findById(3));
        System.out.println("het werkt");

        //FindByGbdatum
        String gboortdatum2 = "1998-09-17";
        Date date3 = Date.valueOf(gboortdatum2);
        System.out.println(rdao.findByGbdatum(date3));


    }
}
