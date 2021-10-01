package P5_DAO_EN_DAOSQL;

import P5_Domein.Ov_chipkaart;
import P5_Domein.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public boolean save(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public List<Product> findByOV_chipKaart(Ov_chipkaart ov_chipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;

}








//    public Product  findByProduct_nummer(int product_nummer);
