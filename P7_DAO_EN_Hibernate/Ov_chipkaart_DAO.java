package P7_DAO_EN_Hibernate;

import P7_Domein.Ov_chipkaart;
import P7_Domein.Reiziger;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

public interface Ov_chipkaart_DAO {

    public boolean save(Ov_chipkaart ov_chipkaart) throws HibernateException;
    public boolean update(Ov_chipkaart ov_chipkaart) throws HibernateException;
    public boolean delete(Ov_chipkaart ov_chipkaart) throws HibernateException;
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws HibernateException;
    public List<Ov_chipkaart> findAll() throws HibernateException;
}
