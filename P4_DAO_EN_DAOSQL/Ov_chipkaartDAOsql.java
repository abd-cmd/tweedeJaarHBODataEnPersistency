package P4_DAO_EN_DAOSQL;

import P4_Domein.Ov_chipkaart;
import P4_Domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ov_chipkaartDAOsql implements Ov_chipkaartDAO {
    private Connection connection;
    private ReizgerDAO rdao;

    public Ov_chipkaartDAOsql(Connection connection) {
        this.connection = connection;
        this.rdao =  new ReizigerDAOsql(connection);
    }

    public void setRdao(ReizgerDAO rdao) {
        this.rdao = rdao;
    }

    @Override
    public boolean save(Ov_chipkaart ov_chipkaart) throws SQLException {
        try{

            String query = "insert into ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "values (?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,ov_chipkaart.getKaart_nummer());
            statement.setDate(2,(Date) ov_chipkaart.getGeldig_tot());
            statement.setInt(3,ov_chipkaart.getKlaase());
            statement.setInt(4,ov_chipkaart.getSaldo());
            statement.setInt(5, ov_chipkaart.getReiziger().getReizigerId());

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
    public boolean update(Ov_chipkaart ov_chipkaart) throws SQLException {

        try{

            String q = ("update ov_chipkaart set kaart_nummer = ?," +
                    "geldig_tot = ?,klasse = ?,saldo = ?, reiziger_id = ? where kaart_nummer = ?; ");
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,ov_chipkaart.getKaart_nummer());
            mysta.setDate(2, (Date) ov_chipkaart.getGeldig_tot());
            mysta.setInt(3, ov_chipkaart.getKlaase());
            mysta.setInt(4, ov_chipkaart.getSaldo());
            mysta.setInt(5,ov_chipkaart.getReiziger().getReizigerId());
            mysta.setInt(6,ov_chipkaart.getKaart_nummer());
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
    public boolean delete(Ov_chipkaart ov_chipkaart) throws SQLException {
        try{

            String q = "DELETE FROM ov_chipkaart WHERE kaart_nummer=?";
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1,ov_chipkaart.getKaart_nummer());
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
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        try{

            String q = "select * from ov_chipkaart where reiziger_id = ?";
            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1, reiziger.getReizigerId());
            ResultSet myRs = mysta.executeQuery();

            List<Ov_chipkaart> ov_chipkaarts = new ArrayList<>();
            while (myRs.next()) {
                int kaart_nummer = myRs.getInt("kaart_nummer");
                java.util.Date geldig_tot = myRs.getDate("geldig_tot");
                int klaase = myRs.getInt("klasse");
                int saldo = myRs.getInt("saldo");
                int reiziger_id = myRs.getInt("reiziger_id");

                ov_chipkaarts.add(new Ov_chipkaart(kaart_nummer,geldig_tot,klaase,saldo,rdao.findById(reiziger_id)));
            }

            mysta.close();
            myRs.close();
            return ov_chipkaarts;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ov_chipkaart> findAll() throws SQLException {
        try{

            String q = "select * from ov_chipkaart";
            PreparedStatement mysta = connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();

            List<Ov_chipkaart> ov_chipkaarts = new ArrayList<>();

            while (myRs.next()) {
                int kaart_nummer = myRs.getInt("kaart_nummer");
                java.util.Date geldig_tot = myRs.getDate("geldig_tot");
                int klaase = myRs.getInt("klasse");
                int saldo = myRs.getInt("saldo");
                int reiziger_id = myRs.getInt("reiziger_id");

                ov_chipkaarts.add(new Ov_chipkaart(kaart_nummer,geldig_tot,klaase,saldo,rdao.findById(reiziger_id)));
            }

            mysta.close();
            myRs.close();
            return ov_chipkaarts;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
