package P7_DAO_EN_Hibernate;


import P7_DAO_EN_Hibernate.Adres_DAO;
import P7_DAO_EN_Hibernate.Reiziger_DAO;
import P7_DAO_EN_Hibernate.Reiziger_Hibernate;
import P7_Domein.Adres;
import P7_Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Adres_Hibernate extends BaseDAOHibernate implements Adres_DAO {

    private Session session;


    public Adres_Hibernate(Session connection) {
        super(connection);
        this.session = connection;
    }

    @Override
    public boolean save(Adres adres) throws HibernateException {

        try {
            session.beginTransaction();
            session.save(adres);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) throws HibernateException {
        try {
            session.beginTransaction();
            session.update(adres);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) throws  HibernateException {
        try {
            session.beginTransaction();
            session.delete(adres);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws HibernateException {
        try {
            return session.get(Adres.class,reiziger.getReizigerId());
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Adres> findAll() throws HibernateException {
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Adres> cq = cb.createQuery(Adres.class);
            Root<Adres> rootEntry = cq.from(Adres.class);
            CriteriaQuery<Adres> all = cq.select(rootEntry);

            TypedQuery<Adres> allQuery = session.createQuery(all);
            return allQuery.getResultList();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
