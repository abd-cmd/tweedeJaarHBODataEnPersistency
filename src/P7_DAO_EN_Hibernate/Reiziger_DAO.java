package P7_DAO_EN_Hibernate;

import P7_Domein.Reiziger;
import org.hibernate.HibernateException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Reiziger_DAO {

    public boolean save(Reiziger reiziger) throws HibernateException;
    public boolean update(Reiziger reiziger) throws HibernateException;
    public boolean delete(Reiziger reiziger) throws HibernateException;
    public Reiziger findById(int id) throws HibernateException;
    public List<Reiziger> findByGbdatum(Date datum) throws HibernateException;
    public List<Reiziger> findAll() throws HibernateException;
}
