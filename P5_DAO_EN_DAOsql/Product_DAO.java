package P5_DAO_EN_DAOsql;

import P5.Domein.Ov_chipkaart;
import P5.Domein.Product;

import java.sql.SQLException;
import java.util.List;

public interface Product_DAO {
    public boolean save(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public List<Product> findByOV_chipKaart(Ov_chipkaart ov_chipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
