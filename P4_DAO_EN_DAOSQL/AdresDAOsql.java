package P4_DAO_EN_DAOSQL;


import P4_Domein.Adres;
import P4_Domein.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOsql implements AdresDAO {
    private Connection connection;
    private ReizgerDAO rdao;

    public void setRdao(ReizgerDAO rdao) {
        this.rdao = rdao;
    }

    public AdresDAOsql(Connection connection) {
        this.connection = connection;
        this.rdao = new ReizigerDAOsql(connection);
        this.setRdao(rdao);
    }

    @Override
    public boolean save(Adres adres) throws SQLException  {
        try{


            String q = "insert into adres (adres_id, postcode, huisnummer, straat, woonplaats,reiziger_id) " +
                    "values (?,?,?,?,?,?)";
            PreparedStatement mysta = connection.prepareStatement(q);

            mysta.setInt(1,adres.getId());
            mysta.setString(2,adres.getPostcode());
            mysta.setString(3,adres.getHuisnummer());
            mysta.setString(4,adres.getStraat());
            mysta.setString(5,adres.getWoonplaats());
            mysta.setInt(6,adres.getReiziger().getReizigerId());



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
    public boolean update(Adres adres) throws SQLException {
        try{

            String q = ("update adres set adres_id = ?,postcode = ?,huisnummer = ?,straat = ?, woonplaats = ?, reiziger_id = ? where adres_id = ?; ");
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,adres.getId());
            mysta.setString(2, adres.getPostcode());
            mysta.setString(3, adres.getHuisnummer());
            mysta.setString(4, adres.getStraat());
            mysta.setString(5,adres.getWoonplaats());
            mysta.setInt(6,adres.getReiziger().getReizigerId());
            mysta.setInt(7,adres.getId());
            mysta.execute();
            mysta.close();
            return true;
        }catch (SQLException e){
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) throws  SQLException {
        try{

            String q = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,adres.getId());
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
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        try{

               String q = "select * from Adres where reiziger_id = ?";
               PreparedStatement mysta = connection.prepareStatement(q);
               mysta.setInt(1, reiziger.getReizigerId());
               ResultSet myRs = mysta.executeQuery();
               while (myRs.next()) {
                   int adres_id = myRs.getInt("adres_id");
                   String postcode = myRs.getString("postcode");
                   String huisnummer = myRs.getString("huisnummer");
                   String straat = myRs.getString("straat");
                   String woonplaats = myRs.getString("woonplaats");
                   int reiziger_id = myRs.getInt("reiziger_id");
                   return new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger);
               }
               mysta.close();
               myRs.close();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        try{

            String q = "select  * from adres";
            PreparedStatement mysta = connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Adres> ListvanAdreses = new ArrayList<>();

            while (myRs.next()){
                int reiziger_id = myRs.getInt("reiziger_id");
                Adres adres = new Adres(myRs.getInt("adres_id"),myRs.getString("postcode"),
                        myRs.getString("huisnummer"),myRs.getString("straat"),
                        myRs.getString("woonplaats"),rdao.findById(reiziger_id));
                ListvanAdreses.add(adres);
            }
            mysta.close();
            myRs.close();
            return ListvanAdreses ;
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
