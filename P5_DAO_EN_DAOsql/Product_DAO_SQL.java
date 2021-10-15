package P5_DAO_EN_DAOsql;

import P5.Domein.Ov_chipkaart;
import P5.Domein.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product_DAO_SQL implements Product_DAO {

    private Connection connection;
    private Ov_chipkaart_DAO odao;




    public Product_DAO_SQL(Connection connection) {
        this.connection = connection;
        this.setOdao(odao);
    }

    public void setOdao(Ov_chipkaart_DAO odao) {
        this.odao = odao;
    }

    @Override
    public boolean save(Product product) throws SQLException {
        try{
            String q = "insert into product (product_nummer, naam, beschrijving, prijs) " +
                    "values (?,?,?,?)";

            PreparedStatement mysta = connection.prepareStatement(q);

            mysta.setInt(1,product.getProduct_nummer());
            mysta.setString(2,product.getNaam());
            mysta.setString(3,product.getBeschrijving());
            mysta.setInt(4,product.getPrijs());

            mysta.execute();
            mysta.close();

            String q2 = "insert into ov_chipkaart_product (kaart_nummer, product_nummer,status,last_update) " +
                    "values (?,?,?,?)";

            PreparedStatement mysta2 = connection.prepareStatement(q2);

            int Kaart_nummer = 0;
            List<Integer> ov_chipkaarts = product.getOv_chipkaarts();
            for (Integer ov_chipkaart: ov_chipkaarts) {
                if (product.voegOv_chipkaartToe(ov_chipkaart)) {
                    Kaart_nummer = ov_chipkaart;
                }
            }

            mysta2.setInt(1, Kaart_nummer);
            mysta2.setInt(2,product.getProduct_nummer());
            mysta2.setString(3,"");
            mysta2.setDate(4, Date.valueOf(LocalDate.now()));

            mysta2.execute();
            mysta2.close();
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
    public boolean update(Product product) throws SQLException {
        try{


            String q = ("update product set product_nummer = ?,naam = ?,beschrijving = ?,prijs = ?" +
                    " where product_nummer = ?; ");


            PreparedStatement mysta = connection.prepareStatement(q);


            mysta.setInt(1,product.getProduct_nummer());
            mysta.setString(2,product.getNaam());
            mysta.setString(3,product.getBeschrijving());
            mysta.setInt(4,product.getPrijs());
            mysta.setInt(5,product.getProduct_nummer());

            mysta.execute();
            mysta.close();


            String q1 = "DELETE FROM ov_chipkaart_product WHERE product_nummer=? ";

            PreparedStatement mysta1 = connection.prepareStatement(q1);

            mysta1.setInt(1,product.getProduct_nummer());

            mysta1.execute();
            mysta1.close();


            String q2 = "insert into ov_chipkaart_product (kaart_nummer, product_nummer,status,last_update) " +
                    "values (?,?,?,?)";

            PreparedStatement mysta2 = connection.prepareStatement(q2);

            int Kaart_nummer = 0;
            List<Ov_chipkaart> ov_chipkaarts = odao.findAll();
            for (Ov_chipkaart ov_chipkaart: ov_chipkaarts) {
                if (product.voegOv_chipkaartToe(ov_chipkaart.getKaart_nummer())) {
                    Kaart_nummer = ov_chipkaart.getKaart_nummer();
                }
            }
            mysta2.setInt(1, Kaart_nummer);
            mysta2.setInt(2,product.getProduct_nummer());
            mysta2.setString(3,"");
            mysta2.setDate(4, Date.valueOf(LocalDate.now()));


            mysta2.execute();
            mysta2.close();


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
    public boolean delete(Product product) throws SQLException {
        try{


            String q1 = "DELETE FROM ov_chipkaart_product WHERE product_nummer=? ";

            PreparedStatement mysta1 = connection.prepareStatement(q1);

            mysta1.setInt(1,product.getProduct_nummer());

            mysta1.execute();
            mysta1.close();



            String q2 = "DELETE FROM product WHERE product_nummer=?";

            PreparedStatement mysta2 = connection.prepareStatement(q2);

            mysta2.setInt(1,product.getProduct_nummer());

            mysta2.execute();
            mysta2.close();


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
    public List<Product> findByOV_chipKaart(Ov_chipkaart ov_chipkaart) throws SQLException {

        try{

            String q = "select pr.product_nummer,pr.naam, pr.beschrijving,pr.prijs from" +
                    "  product pr , ov_chipkaart_product ov_pr " +
                    "where ov_pr.product_nummer = pr.product_nummer AND ov_pr.kaart_nummer = ?";



            PreparedStatement mysta = connection.prepareStatement(q);
            mysta.setInt(1, ov_chipkaart.getKaart_nummer());


            ResultSet myRs = mysta.executeQuery();


            List<Product> products = new ArrayList<>();
            while (myRs.next()) {
                int product_nummer = myRs.getInt("product_nummer");
                String naam = myRs.getString("naam");
                String beschrijving = myRs.getString("beschrijving");
                int prijs = myRs.getInt("prijs");
                Product product = new Product(product_nummer,naam,beschrijving,prijs);


                ArrayList<Integer> ov_chipkaats_nummers = new ArrayList<>();
                ov_chipkaats_nummers.add(ov_chipkaart.getKaart_nummer());
                product.setOv_chipkaarts(ov_chipkaats_nummers);


                products.add(product);
            }

            mysta.close();
            myRs.close();
            return products;
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Product> findAll() throws SQLException {

        try{

            String q = "select  * from product";
            PreparedStatement mysta = connection.prepareStatement(q);
            ResultSet myRs = mysta.executeQuery();


            List<Product> products = new ArrayList<>();

            while (myRs.next()) {
                int product_nummer = myRs.getInt("product_nummer");
                String naam = myRs.getString("naam");
                String beschrijving = myRs.getString("beschrijving");
                int prijs = myRs.getInt("prijs");

                Product product = new Product(product_nummer,naam,beschrijving,prijs);
                products.add(product);
            }

            List<Ov_chipkaart> ov_chipkaarts = odao.findAll();
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts ){
                List<Product> products2 = ov_chipkaart.getProducts();
                for (Product product2 : products2){
                    for (Product product1 : products){
                        if (product1.getProduct_nummer() == product2.getProduct_nummer()
                                && !product1.getOv_chipkaarts().contains(ov_chipkaart.getKaart_nummer())){
                            product1.voegOv_chipkaartToe(ov_chipkaart.getKaart_nummer());
                        }
                    }
                }
            }

            mysta.close();
            myRs.close();
            return products ;

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
