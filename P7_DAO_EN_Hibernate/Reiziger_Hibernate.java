package P7_DAO_EN_Hibernate;


import P7_Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reiziger_Hibernate extends BaseDAOHibernate implements Reiziger_DAO {

    private Session session;


    public Reiziger_Hibernate(Session conn) {
        super(conn);
        this.session = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) throws HibernateException {
      try {
          session.beginTransaction();
          session.save(reiziger);
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
    public boolean update(Reiziger reiziger) throws HibernateException {
        try {
            session.beginTransaction();
            session.update(reiziger);
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
    public boolean delete(Reiziger reiziger) throws HibernateException {
        try {
            session.beginTransaction();
            session.delete(reiziger);
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
    public Reiziger findById(int id) throws HibernateException {
        try {
           return session.get(Reiziger.class,id);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findByGbdatum(Date datum) throws HibernateException {
        try {
            return session.createQuery("FROM reiziger WHERE geboortedatum = '" + datum + "'").list();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() throws HibernateException {
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reiziger> cq = cb.createQuery(Reiziger.class);
            Root<Reiziger> rootEntry = cq.from(Reiziger.class);
            CriteriaQuery<Reiziger> all = cq.select(rootEntry);

            TypedQuery<Reiziger> allQuery = session.createQuery(all);
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
