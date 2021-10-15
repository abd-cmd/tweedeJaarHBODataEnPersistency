package P5_DAO_EN_DAOsql;

import P5_Domein.Adres;
import P5_Domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface Adres_DAO {
    public boolean save(Adres adres) throws SQLException;
    public boolean update(Adres adres) throws SQLException;
    public boolean delete(Adres adres) throws SQLException;
    public Adres findByReiziger(Reiziger reiziger) throws SQLException;
    public List<Adres> findAll()throws SQLException;
}
