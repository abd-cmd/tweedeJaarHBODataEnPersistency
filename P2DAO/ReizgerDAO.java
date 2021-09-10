package P2DAO;

import P2Domein.Reiziger;

import java.util.List;

public interface ReizgerDAO {
    public boolean save(P2Domein.Reiziger reiziger);
    public boolean update(P2Domein.Reiziger reiziger);
    public boolean delete(P2Domein.Reiziger reiziger);
    public P2Domein.Reiziger findById(int id);
    public List<P2Domein.Reiziger> findByGbdatum(String datum);
    public List<Reiziger> findAll();

}
