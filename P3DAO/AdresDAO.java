package P3DAO;

import P3Domein.Adres;
import P3Domein.Reiziger2;

import java.util.List;

public interface AdresDAO {
    boolean save(Adres adres);
    boolean update(Adres adres);
    boolean delete(Adres adres);
    Adres findByReiziger(Reiziger2 reiziger2);
    List<Adres> findAll();
}
