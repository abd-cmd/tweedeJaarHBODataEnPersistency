package P5.Domein;

import java.util.ArrayList;

public class Product {
    private int product_nummer;
    private String Naam;
    private String beschrijving;
    private int prijs;
    private ArrayList<Integer> ov_chipkaarts = new ArrayList<>();


    public Product(int product_nummer, String naam, String beschrijving, int prijs) {
        this.product_nummer = product_nummer;
        Naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;

    }


    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return Naam;
    }

    public void setNaam(String naam) {
        Naam = naam;
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

    public ArrayList<Integer> getOv_chipkaarts() {
        return ov_chipkaarts;
    }

    public void setOv_chipkaarts(ArrayList<Integer> ov_chipkaarts) {
        this.ov_chipkaarts = ov_chipkaarts;
    }
    public boolean voegOv_chipkaartToe(Integer ov_chipkaart_nummer){

        ov_chipkaarts.add(ov_chipkaart_nummer);
        return true;
    }

    public boolean  verwijderOv_chipkaart(Integer ov_chipkaart_nummer){

        ov_chipkaarts.remove(ov_chipkaart_nummer);
        return true;

    }

    @Override
    public String toString() {
        return "Product{" +
                "product_nummer=" + product_nummer +
                ", Naam='" + Naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ov_chipkaarts=" + ov_chipkaarts +
                '}' +"\n";
    }
}
