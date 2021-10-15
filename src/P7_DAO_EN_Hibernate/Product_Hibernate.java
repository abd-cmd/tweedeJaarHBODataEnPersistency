package P7_DAO_EN_Hibernate;


import P7_DAO_EN_Hibernate.Ov_chipkaart_DAO;
import P7_DAO_EN_Hibernate.Product_DAO;
import P7_Domein.Ov_chipkaart;
import P7_Domein.Product;
import P7_Domein.Reiziger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product_Hibernate extends BaseDAOHibernate implements Product_DAO {

    private Session session;
    private Ov_chipkaart_Hibernate ov_chipkaart_hibernate;


    public Product_Hibernate(Session connection) {
        super(connection);
        this.session = connection;
        this.setOv_chipkaart_hibernate(ov_chipkaart_hibernate);
    }

    public void setOv_chipkaart_hibernate(Ov_chipkaart_Hibernate ov_chipkaart_hibernate) {
        this.ov_chipkaart_hibernate = ov_chipkaart_hibernate;
    }

    @Override
    public boolean save(Product product) throws HibernateException {
        try {
            session.beginTransaction();
            session.save(product);
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
    public boolean update(Product product) throws HibernateException {

            try {
                session.beginTransaction();
                session.update(product);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }

        }

        @Override
        public boolean delete (Product product) throws HibernateException {
            try {
                session.beginTransaction();
                session.delete(product);
                session.getTransaction().commit();
                return true;
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }


        @Override
        public List<Product> findByOv_chipkaart (Ov_chipkaart ov_chipkaart) throws HibernateException {
            try {
                // ik heb het getest en het werkt
               Query products = session.createQuery("select p from product p join p.ov_chipkaarts p_ov where p_ov.id = :ovchipkaart");
                products.setParameter("ovchipkaart",ov_chipkaart.getId());
                return products.list();
            }catch (HibernateException e){
                System.out.println(e.getMessage());
                return null;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }


        @Override
        public List<Product> findAll ()throws HibernateException {
            try {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Product> cq = cb.createQuery(Product.class);
                Root<Product> rootEntry = cq.from(Product.class);
                CriteriaQuery<Product> all = cq.select(rootEntry);

                TypedQuery<Product> allQuery = session.createQuery(all);
                return allQuery.getResultList();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
                return null;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
}



