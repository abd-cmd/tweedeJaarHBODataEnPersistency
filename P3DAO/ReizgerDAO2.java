package P3DAO;

import P2Domein.Reiziger;
import P3Domein.Reiziger2;

import java.util.List;

public interface ReizgerDAO2 {
    boolean save(Reiziger2 reiziger);
    boolean update(Reiziger2 reiziger);
    boolean delete(Reiziger2 reiziger);
    Reiziger2 findById(int id);
    List<Reiziger2> findByGbdatum(String datum);
    List<Reiziger2> findAll();
}
