package P2Domein;

import P2DAO.ReizgerDAO;
import P2DAOsql.ReizigerDAOsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        ReizigerDAOsql d1 = new ReizigerDAOsql(connection);
        testReizigerDAO(d1);


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
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Voeg aanvullende tests van de ontbrekende CRUD-operaties in.
        String gboortdatum = "1998-09-17";
        Reiziger r = new Reiziger(1,"A","Almoneim", "Alshami", java.sql.Date.valueOf(gboortdatum));
        System.out.println(rdao.update(r));
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
}
