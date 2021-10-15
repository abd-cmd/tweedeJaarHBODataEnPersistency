package P5_DAO_EN_DAOsql;

import P5_Domein.Ov_chipkaart;
import P5_Domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface Ov_chipkaart_DAO {
    public boolean save(Ov_chipkaart ov_chipkaart) throws SQLException;
    public boolean update(Ov_chipkaart ov_chipkaart) throws SQLException;
    public boolean delete(Ov_chipkaart ov_chipkaart) throws SQLException;
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    public List<Ov_chipkaart> findAll() throws SQLException;
}
