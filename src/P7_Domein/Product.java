package P7_Domein;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
public class Product {


    @Id
    @Column(name = "product_nummer")
    private int id;
    private String naam;
    private String beschrijving;
    private int prijs;

    @ManyToMany(mappedBy = "products")
    private List<Ov_chipkaart> ov_chipkaarts = new ArrayList<>();


    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.id = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;

    }

    public Product() {}


    public int getProduct_nummer() {
        return id;
    }

    public void setProduct_nummer(int product_nummer) {
        this.id = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public List<Ov_chipkaart> getOv_chipkaarts() {
        return ov_chipkaarts;
    }

    public void setOv_chipkaarts(List<Ov_chipkaart> ov_chipkaarts) {
        this.ov_chipkaarts = ov_chipkaarts;
    }

    public boolean voegOv_chipkaartToe(Ov_chipkaart ov_chipkaart_nummer){

        ov_chipkaarts.add(ov_chipkaart_nummer);
        return true;
    }

    public boolean  verwijderOv_chipkaart(Ov_chipkaart ov_chipkaart_nummer){

        ov_chipkaarts.remove(ov_chipkaart_nummer);
        return true;

    }

    @Override
    public String toString() {

        int out = 0;
        if (ov_chipkaarts.size() != 0) {
            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                out = ov_chipkaart.getId();
            }
        }

        return "Product{" +
                "product_nummer=" + id +
                ", Naam='" + naam +
                ", beschrijving='" + beschrijving +
                ", prijs=" + prijs +
                ", ov_chipkaart_nummer=" + out +
                '}' + "\n" ;
    }
}
