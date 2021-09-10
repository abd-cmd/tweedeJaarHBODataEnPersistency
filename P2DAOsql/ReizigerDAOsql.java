package P2DAOsql;

import P2DAO.ReizgerDAO;
import P2Domein.Reiziger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql implements ReizgerDAO {

   private Connection Connection;

    public ReizigerDAOsql(Connection conn) {

        this.Connection = conn;

    }


    @Override
    public boolean save(Reiziger reiziger) {
        try{

         String q = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "values (?,?,?,?,?)";
         PreparedStatement mysta = Connection.prepareStatement(q);
        mysta.setInt(1,reiziger.getReiziger_id());
        mysta.setString(2,reiziger.getVoorletters());
        mysta.setString(3,reiziger.getTussenvogel());
        mysta.setString(4,reiziger.getAchternaam());
        mysta.setDate(5, (Date) reiziger.getGeboortedatum());
        mysta.execute();
        return true;
    }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{

            String q = ("update reiziger set reiziger_id = ?,voorletters = ?,tussenvoegsel = ?,achternaam = ?, geboortedatum = ? where reiziger_id = ?; ");
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReiziger_id());
            mysta.setString(2, reiziger.getVoorletters());
            mysta.setString(3, reiziger.getTussenvogel());
            mysta.setString(4, reiziger.getAchternaam());
            mysta.setDate(5,(Date) reiziger.getGeboortedatum());
            mysta.setInt(6,reiziger.getReiziger_id());
            mysta.execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public boolean delete(Reiziger reiziger) {
        try{

            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReiziger_id());
            mysta.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
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
                return new Reiziger(reiziger_id,voorletter,tussenvoegsel,achternaam,GB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {

        try {
            String q = "select * from reiziger" ;
            PreparedStatement mysta = Connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();
            List<Reiziger> ListvanReizigers = new ArrayList<>();
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
                    P2Domein.Reiziger reiziger = new P2Domein.Reiziger(reiziger_Id, voorletters, tussenvoegsel, achternaam, Gd);
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
    public List<Reiziger> findAll() {
        try{

            String q = "select  * from reiziger";
            PreparedStatement mysta = Connection.prepareStatement(q);
//            mysta.executeQuery();
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Reiziger> Listvanreizegers = new ArrayList<>();

            while (myRs.next()){
            Reiziger reiziger = new Reiziger(myRs.getInt("reiziger_id"),myRs.getString("voorletters")
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
