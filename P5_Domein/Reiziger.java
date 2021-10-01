package P5_Domein;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Reiziger {

    private  int reizigerId;
    private  String voorletters;
    private  String tussenvoegsel;
    private  String achternaam;
    private  Date geboortedatum;
    private Adres adres;
    private List<Ov_chipkaart> ov_chipkaarts = new ArrayList<>();

    public Reiziger(int id , String voorletters , String tussenvoegsel, String achternaam, Date geboortedatum){
        reizigerId = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;

    }



    public List<Ov_chipkaart> getOv_chipkaarts() {
        return ov_chipkaarts;
    }

    public void setOv_chipkaarts(List<Ov_chipkaart> ov_chipkaarts) {
        this.ov_chipkaarts = ov_chipkaarts;
    }



    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public  int getReizigerId() {
        return reizigerId;
    }

    public    Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public    String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public  String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public  String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
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
        String ov = "";
        if(adres!=null && ov_chipkaarts.size() != 0){
            ov = ov_chipkaarts.toString();
            a = adres.toString();
        }
        return  voorletters + tussenvoegsel +" " +  achternaam + "  " +  geboortedatum +" " + reizigerId +" "  + " " + a + " " + ov;
    }




}




















//