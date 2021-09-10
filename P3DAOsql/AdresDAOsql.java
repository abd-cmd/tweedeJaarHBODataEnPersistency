package P3DAOsql;

import P2Domein.Reiziger;
import P3DAO.AdresDAO;
import P3DAO.ReizgerDAO2;
import P3Domein.Adres;
import P3Domein.Reiziger2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOsql implements AdresDAO {
    private Connection connection;
    private ReizgerDAO2 rdao;


    public AdresDAOsql(Connection connection) {
        this.connection = connection;

    }

    @Override
    public boolean save(Adres adres) {
        try{

            String q = "insert into adres (adres_id, postcode, huisnummer, straat, woonplaats,reiziger_id) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement mysta = connection.prepareStatement(q);

            mysta.setInt(1,adres.getId());
            mysta.setString(2,adres.getPostcode());
            mysta.setString(3,adres.getHuisnummer());
            mysta.setString(4,adres.getStraat());
            mysta.setString(5,adres.getWoonplaats());
            mysta.setInt(6,adres.getReiziger_id());
            mysta.execute();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{

            String q = ("update adres set adres_id = ?,postcode = ?,huisnummer = ?,straat = ?, woonplaats = ?, reiziger_id = ? where adres_id = ?; ");
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,adres.getId());
            mysta.setString(2, adres.getPostcode());
            mysta.setString(3, adres.getHuisnummer());
            mysta.setString(4, adres.getStraat());
            mysta.setString(5,adres.getWoonplaats());
            mysta.setInt(6,adres.getReiziger_id());
            mysta.setInt(7,adres.getId());
            mysta.execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try{

            String q = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,adres.getId());
            mysta.execute();


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger2 reiziger2) {
        try{

               String q = "select * from Adres where reiziger_id = ?";
               PreparedStatement mysta = connection.prepareStatement(q);
               mysta.setInt(1, reiziger2.getReizigerId());

               ResultSet myRs = mysta.executeQuery();
               while (myRs.next()) {
                   int adres_id = myRs.getInt("adres_id");
                   String postcode = myRs.getString("postcode");
                   String huisnummer = myRs.getString("huisnummer");
                   String straat = myRs.getString("straat");
                   String woonplaats = myRs.getString("woonplaats");
                   int reiziger_id = myRs.getInt("reiziger_id");
                   return new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
               }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Adres> findAll() {
        try{

            String q = "select  * from adres";
            PreparedStatement mysta = connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Adres> ListvanAdreses = new ArrayList<>();

            while (myRs.next()){
                Adres adres = new Adres(myRs.getInt("adres_id"),myRs.getString("postcode"),
                        myRs.getString("huisnummer"),myRs.getString("straat"),
                        myRs.getString("woonplaats"),myRs.getInt("reiziger_id"));
                ListvanAdreses.add(adres);
            }
            return ListvanAdreses ;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
