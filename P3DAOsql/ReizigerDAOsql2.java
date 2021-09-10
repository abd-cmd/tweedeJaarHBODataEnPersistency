package P3DAOsql;

import P3DAO.AdresDAO;
import P3DAO.ReizgerDAO2;
import P3Domein.Reiziger2;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql2 implements ReizgerDAO2 {

   private Connection Connection;
   private AdresDAO adao;

    public ReizigerDAOsql2(Connection conn) {

        this.Connection = conn;

    }


    @Override
    public boolean save(Reiziger2 reiziger) {
        try{

         String query = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "values (?,?,?,?,?)";
         PreparedStatement statement = Connection.prepareStatement(query);
//         ResultSet myRs = mysta.executeQuery(q);
//            while (myRs.next()){
//                System.out.println("het werkt");
//            }
        statement.setInt(1,reiziger.getReizigerId());
        statement.setString(2,reiziger.getVoorletters());
        statement.setString(3,reiziger.getTussenvoegsel());
        statement.setString(4,reiziger.getAchternaam());
        statement.setDate(5, (Date) reiziger.getGeboortedatum());
        statement.execute();
        return true;
    }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger2 reiziger) {
        try{

            String q = ("update reiziger set reiziger_id = ?,voorletters = ?,tussenvoegsel = ?,achternaam = ?, geboortedatum = ? where reiziger_id = ?; ");
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());
            mysta.setString(2, reiziger.getVoorletters());
            mysta.setString(3, reiziger.getTussenvoegsel());
            mysta.setString(4, reiziger.getAchternaam());
            mysta.setDate(5,(Date) reiziger.getGeboortedatum());
            mysta.setInt(6,reiziger.getReizigerId());
            mysta.execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public boolean delete(Reiziger2 reiziger) {
        try{

            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());
            mysta.execute();

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger2 findById(int id) {
        try{

            String q = "select * from reiziger where reiziger_id = ?";
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,id);
            ResultSet myRs = mysta.executeQuery();
            while (myRs.next()){
                int reiziger_id = myRs.getInt("reiziger_id");
                String voorletter = myRs.getString("voorletters");
                String tussenvoegsel = myRs.getString("tussenvoegsel");
                String achternaam = myRs.getString("achternaam");
                Date GB = myRs.getDate("geboortedatum");
                return new Reiziger2(reiziger_id,voorletter,tussenvoegsel,achternaam,GB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger2> findByGbdatum(String datum) {
        try {
            String q = "select * from reiziger" ;
            PreparedStatement mysta = Connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();
            List<Reiziger2> ListvanReizigers = new ArrayList<>();
            while (myRs.next()) {
                Date Gd = myRs.getDate("geboortedatum");
                String pattern = "yyyy-MM-dd";
                DateFormat df = new SimpleDateFormat(pattern);
                String gd = df.format(Gd);
                if (gd.equals(datum)) {
                    int reiziger_Id = myRs.getInt("reiziger_id");
                    String voorletters = myRs.getString("voorletters");
                    String tussenvoegsel = myRs.getString("tussenvoegsel");
                    String achternaam = myRs.getString("achternaam");
                    Reiziger2 reiziger = new Reiziger2(reiziger_Id, voorletters, tussenvoegsel, achternaam, Gd);
                    ListvanReizigers.add(reiziger);
                }
            }
            return ListvanReizigers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger2> findAll() {
        try{

            String q = "select  * from reiziger";
            PreparedStatement mysta = Connection.prepareStatement(q);
//            mysta.executeQuery();
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Reiziger2> Listvanreizegers = new ArrayList<>();

            while (myRs.next()){
            Reiziger2 reiziger = new Reiziger2(myRs.getInt("reiziger_id"),myRs.getString("voorletters")
            ,myRs.getString("tussenvoegsel"),myRs.getString("achternaam"),myRs.getDate("geboortedatum"));
            Listvanreizegers.add(reiziger);
            }
            return Listvanreizegers ;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
