package P7_DAO_EN_Hibernate;

import P7_Domein.Adres;
import P7_Domein.Reiziger;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

public interface Adres_DAO {

    public boolean save(Adres adres) throws HibernateException;
    public boolean update(Adres adres) throws HibernateException;
    public boolean delete(Adres adres) throws HibernateException;
    public Adres findByReiziger(Reiziger reiziger) throws HibernateException;
    public List<Adres> findAll() throws HibernateException;
}
