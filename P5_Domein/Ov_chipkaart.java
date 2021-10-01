package P5_Domein;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ov_chipkaart {
    private int kaart_nummer;
    private Date geldig_tot;
    private int klaase;
    private int saldo;
    private Reiziger reiziger;
    private List<Product> products = new ArrayList<>();


    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klaase, int saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klaase = klaase;
        this.saldo = saldo;
        this.reiziger = reiziger;
//        this.reiziger.getOv_chipkaarts().add(this);
//        this.setProducts(products);
//        for (Product product : products){
//            this.voegProductToe(product);
//        }
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlaase() {
        return klaase;
    }

    public void setKlaase(int klaase) {
        this.klaase = klaase;
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

    public void voegProductToe(Product product){
        for (Product product1: products){
            if (!product.equals(product1)){
        products.add(product);}}
    }

    public void verwijderProduct(Product product){
        products.remove(product);
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

        for (Product product1 : this.products){
            out = product1.toString();
        }


            return "Ov_chipkaart{" +
            "kaart_nummer=" + kaart_nummer +
            ", geldig_tot=" + geldig_tot +
            ", klaase=" + klaase +
            ", saldo=" + saldo +
            ", reiziger=" + reiziger.getReizigerId() + "\n"+
            ", products="+  out +
            '}' ;
    }
}
