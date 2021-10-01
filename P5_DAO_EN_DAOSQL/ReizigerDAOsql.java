package P5_DAO_EN_DAOSQL;

import P5_Domein.Adres;
import P5_Domein.Ov_chipkaart;
import P5_Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOsql implements  ReizigerDAO{

    private Connection connection;
    private AdresDAO adao;
    private OV_chipkaartDAO odao;

    public ReizigerDAOsql(Connection conn) {
        this.connection = conn;
        this.setAdao(adao);
        this.setOdao(odao);
    }

    public void setOdao(OV_chipkaartDAO odao) {
        this.odao = odao;
    }

    public void setAdao(AdresDAO adao) {
        this.adao = adao;
    }

    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try{

            String query = "insert into reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "values (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,reiziger.getReizigerId());
            statement.setString(2,reiziger.getVoorletters());
            statement.setString(3,reiziger.getTussenvoegsel());
            statement.setString(4,reiziger.getAchternaam());
            statement.setDate(5, reiziger.getGeboortedatum());


            Adres adres = reiziger.getAdres();
            if (adres != null) {
                adao.save(adres);
            }else {
                adao.update(adres);
            }

            List<Ov_chipkaart> ov_chipkaarts = reiziger.getOv_chipkaarts();
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if (ov_chipkaarts.size() != 0) {
                    odao.save(ov_chipkaart);
                }else {
                    odao.update(ov_chipkaart);
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
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());
            mysta.setString(2, reiziger.getVoorletters());
            mysta.setString(3, reiziger.getTussenvoegsel());
            mysta.setString(4, reiziger.getAchternaam());
            mysta.setDate(5,(Date) reiziger.getGeboortedatum());
            mysta.setInt(6,reiziger.getReizigerId());

            Adres adres = reiziger.getAdres();
            if (adres != null) {
                adao.update(adres);
            }

            List<Ov_chipkaart> ov_chipkaarts =  reiziger.getOv_chipkaarts();
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if ( ov_chipkaarts.size() != 0) {
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

            // Adres wordt verwijderd.
            Adres adres = reiziger.getAdres();
            if (adres != null) {
                adao.delete(adres);
            }

            // ov_chipkaart wordt verwijderd.
            List<Ov_chipkaart> ov_chipkaarts =  reiziger.getOv_chipkaarts();
            if ( ov_chipkaarts.size() != 0) {
                for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                    odao.delete(ov_chipkaart);
                }
            }

            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,reiziger.getReizigerId());



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
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,id);
            ResultSet myRs = mysta.executeQuery();

            while (myRs.next()){
                int reiziger_id = myRs.getInt("reiziger_id");
                String voorletter = myRs.getString("voorletters");
                String tussenvoegsel = myRs.getString("tussenvoegsel");
                String achternaam = myRs.getString("achternaam");
                Date GB = myRs.getDate("geboortedatum");

                Reiziger reiziger = new Reiziger(reiziger_id,voorletter,tussenvoegsel,achternaam,GB);
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.setOv_chipkaarts(odao.findByReiziger(reiziger));
                return reiziger ;

            }
            myRs.close();
            mysta.close();
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
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setDate(1, datum);
            mysta.execute();
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
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.setOv_chipkaarts(odao.findByReiziger(reiziger));
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
            PreparedStatement mysta = connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();

            ArrayList<Reiziger> Listvanreizegers = new ArrayList<>();

            while (myRs.next()){
                Reiziger reiziger = new Reiziger(myRs.getInt("reiziger_id"),myRs.getString("voorletters")
                        ,myRs.getString("tussenvoegsel"),
                        myRs.getString("achternaam"),myRs.getDate("geboortedatum"));
                Listvanreizegers.add(reiziger);
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reiziger.setOv_chipkaarts(odao.findByReiziger(reiziger));
            }



            mysta.close();
            myRs.close();
            return Listvanreizegers ;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());;
            return null;
        }
    }

}
