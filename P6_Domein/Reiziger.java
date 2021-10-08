package P6_Domein;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "reiziger")
public class Reiziger {

    @Id
    @Column(name = "reiziger_id")
    private  int Id;
    private  String voorletters;
    private  String tussenvoegsel;
    private  String achternaam;
    private  Date geboortedatum;

    @OneToOne(mappedBy = "reiziger")
    @JoinColumn(name = "reiziger_id")
    private Adres adres;

    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL)
    private List<Ov_chipkaart> ov_chipkaarts = new ArrayList<>();

    public Reiziger(int id , String voorletters , String tussenvoegsel, String achternaam, Date geboortedatum){
        Id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {

    }


    public Adres getAdres() {
        return adres;
    }
    public  int getReizigerId() {
        return Id;
    }


    public Date getGeboortedatum() {
        return geboortedatum;
    }
    public  String getAchternaam() {
        return achternaam;
    }
    public  String getTussenvoegsel() {
        return tussenvoegsel;
    }
    public  String getVoorletters() {
        return voorletters;
    }
    public List<Ov_chipkaart> getOv_chipkaarts() {
        return ov_chipkaarts;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }
    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    public void setReizigerId(int reizigerId) {
        this.Id = reizigerId;
    }
    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }
    public void setOv_chipkaarts(List<Ov_chipkaart> ov_chipkaarts) {
        this.ov_chipkaarts = ov_chipkaarts;
    }


    public void voegOv_chipkaartToe(Ov_chipkaart ov_chipkaart){
        ov_chipkaarts.add(ov_chipkaart);
    }
    public void remove_Ov_chipkaart(Ov_chipkaart ov_chipkaart){
        ov_chipkaarts.remove(ov_chipkaart);
    }


    @Override
    public String toString() {
        //reiziger gegevens
        String a = "";


        ArrayList<String> ov_list = new ArrayList<>();
        if(ov_chipkaarts.size() != 0 ){
            for (Ov_chipkaart o : ov_chipkaarts)
               ov_list.add(String.valueOf(o.getSaldo()));
        }
        if(adres!=null){
            a = String.valueOf(adres);
        }
        return  voorletters +  " " +
                tussenvoegsel + " " +
                achternaam + "  " +
                geboortedatum +" " +  a + " " + ov_list;
    }

}












//