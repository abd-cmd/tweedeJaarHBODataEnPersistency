package P7_DAO_EN_Hibernate;

import P7_Domein.Ov_chipkaart;
import P7_Domein.Product;

import java.sql.SQLException;
import java.util.List;

public interface Product_DAO {

    public boolean save(Product product) throws SQLException;
    public boolean update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
    public List<Product> findByOv_chipkaart(Ov_chipkaart ov_chipkaart) throws SQLException;
    public List<Product> findAll() throws SQLException;
}
