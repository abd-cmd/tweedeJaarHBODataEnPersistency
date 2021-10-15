package P7_Domein;

import P7_DAO_EN_Hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;
    private BaseDAOHibernate baseDAOHibernate;


    static {
        try {
            // Create a Hibernate session factory

            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {

//        testFetchAll();
        testHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testHibernate(){
        Session session = factory.openSession();
        Reiziger_Hibernate reiziger_hibernate = new Reiziger_Hibernate(session);
        Adres_Hibernate adres_hibernate = new Adres_Hibernate(session);
        Ov_chipkaart_Hibernate ov_chipkaart_hibernate = new Ov_chipkaart_Hibernate(session);
        Product_Hibernate product_hibernate = new Product_Hibernate(session);

        //save
        String gbdatum = "1981-03-14";
        Date date = Date.valueOf(gbdatum);
        Reiziger sietske = new Reiziger(77, "S", "hhhhhh", "Boers", date);
        System.out.println(reiziger_hibernate.update(sietske));

        // findById
        System.out.println("findById");
        System.out.println(reiziger_hibernate.findById(3));

        System.out.println();

        //findByGbdatum
        System.out.println("findByGbdatum");
        String gboortdatum2 = "1998-08-11";
        Date date3 = Date.valueOf(gboortdatum2);
        System.out.println(reiziger_hibernate.findByGbdatum(date3));
        System.out.println();


        for (Reiziger reiziger: reiziger_hibernate.findAll()){
            System.out.println(reiziger);
        }
        System.out.println();

        //Save
        Adres adres = new Adres(6,"3555VM","71","maasdijkstraat","Utrecht",sietske);
//        adres_hibernate.save(adres);

        System.out.println();

        //findByReiziger
        System.out.println("adres.findByReiziger");
        String gboortdatum = "1998-09-17";
        Date date1 = Date.valueOf(gboortdatum);
        Reiziger r2 = new Reiziger(2,"B","van", "Rijn",date1);
        System.out.println(adres_hibernate.findByReiziger(r2));

        System.out.println();
        System.out.println("adres.findAll");
        List<Adres> adressen = adres_hibernate.findAll();
        for (Adres adres2: adressen) {
            System.out.println(adres2 );

        }

        System.out.println();

        String gboortdatum3 = "2020-01-01";
        Ov_chipkaart Ov_chipkaart = new Ov_chipkaart(44444,java.sql.Date.valueOf(gboortdatum3),3,25,sietske);
//        ov_chipkaart_hibernate.save(Ov_chipkaart);

        //findbyReiziger
        System.out.println("ov_chipkaart.findByReiziger");
        System.out.println(ov_chipkaart_hibernate.findByReiziger(r2));
        System.out.println();

        System.out.println("ov_chipkaart.findAll");
        List<Ov_chipkaart> ov_chipkaarts = ov_chipkaart_hibernate.findAll();
        for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
            System.out.println(ov_chipkaart );
        }
        System.out.println();

        String geldig_tot = "2018-05-31";
        Ov_chipkaart Ov_chipkaart2 = new Ov_chipkaart(35283,java.sql.Date.valueOf(geldig_tot),2,25,r2);
        System.out.println("product.findByOv_chipkaart");
        System.out.println(product_hibernate.findByOv_chipkaart(Ov_chipkaart2));


        System.out.println();
        System.out.println("product.findAll");
        List<Product> Products = product_hibernate.findAll();
        for (Product Product : Products) {
            System.out.println(Product);
        }

    }
}