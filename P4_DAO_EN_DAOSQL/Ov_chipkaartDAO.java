package P4_DAO_EN_DAOSQL;

import P4_Domein.Ov_chipkaart;
import P4_Domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface Ov_chipkaartDAO {
    public boolean save(Ov_chipkaart ov_chipkaart) throws SQLException;
    public boolean update(Ov_chipkaart ov_chipkaart) throws SQLException;
    public boolean delete(Ov_chipkaart ov_chipkaart) throws SQLException;
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
    public List<Ov_chipkaart> findAll() throws SQLException;
}
