package P7_DAO_EN_Hibernate;


import P7_DAO_EN_Hibernate.*;
import P7_Domein.Adres;
import P7_Domein.Ov_chipkaart;
import P7_Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ov_chipkaart_Hibernate extends BaseDAOHibernate implements Ov_chipkaart_DAO {

    private Session session;



    public Ov_chipkaart_Hibernate(Session connection) {
        super(connection);
        this.session = connection;
    }



    @Override
    public boolean save(Ov_chipkaart ov_chipkaart) throws HibernateException {
        try {
            session.beginTransaction();
            session.save(ov_chipkaart);
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
    public boolean update(Ov_chipkaart ov_chipkaart) throws HibernateException {

        try {
            session.beginTransaction();
            session.update(ov_chipkaart);
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
    public boolean delete(Ov_chipkaart ov_chipkaart) throws HibernateException {
        try {
            session.beginTransaction();
            session.delete(ov_chipkaart);
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
    public List<Ov_chipkaart> findByReiziger(Reiziger reiziger) throws HibernateException {
        try {
            return session.createQuery(" FROM ov_chipkaart where reiziger_id =" + reiziger.getReizigerId() ).list();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public List<Ov_chipkaart> findAll() throws HibernateException {
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Ov_chipkaart> cq = cb.createQuery(Ov_chipkaart.class);
            Root<Ov_chipkaart> rootEntry = cq.from(Ov_chipkaart.class);
            CriteriaQuery<Ov_chipkaart> all = cq.select(rootEntry);

            TypedQuery<Ov_chipkaart> allQuery = session.createQuery(all);
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
