package P6_Domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "ov_chipkaart")
public class Ov_chipkaart {

    @Id
    @Column(name = "kaart_nummer")
    private int id;
    private Date geldig_tot;
    private int klasse;
    private int saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns ={ @JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns ={ @JoinColumn(name = "product_nummer")})
    private List<Product> products = new ArrayList<>();


    public Ov_chipkaart(int id, Date geldig_tot, int klaase, int saldo, Reiziger reiziger) {
        this.id = id;
        this.geldig_tot = geldig_tot;
        this.klasse = klaase;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public Ov_chipkaart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlaase() {
        return klasse;
    }

    public void setKlaase(int klaase) {
        this.klasse = klaase;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public boolean voegProductToe(Product product){
        for (Product product1: products){
            if (!product.equals(product1)){
            products.add(product);
            return true;
            }
        }
        return false;
    }

    public boolean verwijderProduct(Product product){
        for (Product product1: products){
            if (product.equals(product1)){
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        String out = " er is geen product";
        int R = 0;

        for (Product product: products) {
            if (products.size() != 0) {
                out = product.toString();
            }
        }

        if (reiziger != null){
            R = reiziger.getReizigerId();
        }

            return "Ov_chipkaart{" +
            "kaart_nummer=" + id +
            ", geldig_tot=" + geldig_tot +
            ", klaase=" + klasse +
            ", saldo=" + saldo +
            ", reiziger=" +R+ "\n"+
            ", products="+  out +
            '}' ;
    }
}
