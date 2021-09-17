package P2_DAO_EN_DAOSAL;


import P2_Domein.Reiziger;

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
    public boolean save(Reiziger reiziger)throws SQLException {
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
        mysta.close();
        return true;
    }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException {
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
            mysta.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try{

            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReiziger_id());
            mysta.execute();
            mysta.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) throws SQLException {
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
            myRs.close();
            mysta.close();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) {

        try {
            String q = "select * from reiziger where geboortedatum = ? " ;
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setDate(1, datum);
            ResultSet myRs = mysta.executeQuery();
            List<Reiziger> ListvanReizigers = new ArrayList<>();

            while (myRs.next()) {
                Date Gd =  myRs.getDate("geboortedatum");

                int reiziger_Id = myRs.getInt("reiziger_id");
                String voorletters = myRs.getString("voorletters");
                String tussenvoegsel = myRs.getString("tussenvoegsel");
                String achternaam = myRs.getString("achternaam");
                Reiziger reiziger = new Reiziger(reiziger_Id, voorletters, tussenvoegsel, achternaam, Gd);
                ListvanReizigers.add(reiziger);
            }

            mysta.close();
            myRs.close();
            return ListvanReizigers;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        try{

            String q = "select  * from reiziger";
            PreparedStatement mysta = Connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Reiziger> Listvanreizegers = new ArrayList<>();

            while (myRs.next()){
            Reiziger reiziger = new Reiziger(myRs.getInt("reiziger_id"),myRs.getString("voorletters")
            ,myRs.getString("tussenvoegsel"),myRs.getString("achternaam"),myRs.getDate("geboortedatum"));
            Listvanreizegers.add(reiziger);
            }
            myRs.close();
            mysta.close();
            return Listvanreizegers ;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
