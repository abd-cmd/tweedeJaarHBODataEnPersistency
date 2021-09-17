package P4_DAO_EN_DAOSQL;


import P4_Domein.Adres;
import P4_Domein.Ov_chipkaart;
import P4_Domein.Reiziger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql implements ReizgerDAO {

   private Connection Connection;
   private AdresDAO adao;
   private Ov_chipkaartDAO odao;

    public ReizigerDAOsql(Connection conn) {
        this.Connection = conn;
    }

    public void setOdao(Ov_chipkaartDAO odao) {
        this.odao = odao;
    }

    public void setAdao(AdresDAO adao) {
        this.adao = adao;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException{
        try{

         String query = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "values (?,?,?,?,?)";
         PreparedStatement statement = Connection.prepareStatement(query);

        statement.setInt(1,reiziger.getReizigerId());
        statement.setString(2,reiziger.getVoorletters());
        statement.setString(3,reiziger.getTussenvoegsel());
        statement.setString(4,reiziger.getAchternaam());
        statement.setDate(5, (Date) reiziger.getGeboortedatum());


        Adres adres = reiziger.getAdres();
        if (adres != null) {
            adao.save(adres);
        }

        ArrayList<Ov_chipkaart> ov_chipkaarts =  reiziger.getOv_chipkaarts();
        if ( ov_chipkaarts.size() > 0) {
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                odao.save(ov_chipkaart);
            }
        }

        statement.execute();
        statement.close();
        return true;
    }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) throws SQLException{
        try{

            String q = ("update reiziger set reiziger_id = ?,voorletters = ?,tussenvoegsel = ?,achternaam = ?, geboortedatum = ? where reiziger_id = ?; ");
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());
            mysta.setString(2, reiziger.getVoorletters());
            mysta.setString(3, reiziger.getTussenvoegsel());
            mysta.setString(4, reiziger.getAchternaam());
            mysta.setDate(5,(Date) reiziger.getGeboortedatum());
            mysta.setInt(6,reiziger.getReizigerId());

            Adres adres = adao.findByReiziger(reiziger);
            adao.update(adres);

            List<Ov_chipkaart> ov_chipkaarts =  odao.findAll();
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if ( ov_chipkaarts.size() > 0) {
                    odao.update(ov_chipkaart);
                }

            }

            mysta.execute();
            mysta.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }


    @Override
    public boolean delete(Reiziger reiziger) throws SQLException {
        try{
            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement mysta = Connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());

            // Adres wordt verwijderd.
            Adres adres = adao.findByReiziger(reiziger);
            if (adres != null) {
                adao.delete(adres);
            }

            // ov_chipkaart wordt verwijderd.
            List<Ov_chipkaart> ov_chipkaarts =  odao.findByReiziger(reiziger);
            if ( ov_chipkaarts.size() > 0) {
                for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                    odao.delete(ov_chipkaart);
                }
            }

            mysta.execute();
            mysta.close();


            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        catch (Exception e){
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
                Adres adres = adao.findByReiziger(new Reiziger(reiziger_id,voorletter,tussenvoegsel,achternaam,GB));
                if (adres != null){
                    System.out.println(adres);
                }

                List<Ov_chipkaart> ov_chipkaarts =  odao.findByReiziger(new Reiziger(reiziger_id,voorletter,tussenvoegsel,achternaam,GB));
                for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                    if ( ov_chipkaarts.size() > 0) {
                        System.out.println(ov_chipkaart.getReiziger());
                    }
                }
                return new Reiziger(reiziger_id,voorletter,tussenvoegsel,achternaam,GB);
            }
            mysta.close();
            myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reiziger> findByGbdatum(java.sql.Date datum) throws SQLException {
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

                Adres adres = adao.findByReiziger(new Reiziger(reiziger_Id,voorletters,tussenvoegsel,achternaam,Gd));
                if (adres != null){
                    System.out.println(adres);
                }

                List<Ov_chipkaart> ov_chipkaarts =  odao.findByReiziger(new Reiziger(reiziger_Id, voorletters, tussenvoegsel, achternaam, Gd));
                for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                    if ( ov_chipkaarts.size() > 0) {
                        System.out.println(ov_chipkaart.getReiziger());
                    }
                }

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
            ,myRs.getString("tussenvoegsel"),
                    myRs.getString("achternaam"),myRs.getDate("geboortedatum"));
            Listvanreizegers.add(reiziger);


            }

            List<Adres> adresList = adao.findAll();
            for (Adres adres : adresList) {
                System.out.println(adres.getReiziger());

            }


            List<Ov_chipkaart> ov_chipkaarts =  odao.findAll();
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                System.out.println(ov_chipkaart.getReiziger());
            }
            mysta.close();
            myRs.close();
            return Listvanreizegers ;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
