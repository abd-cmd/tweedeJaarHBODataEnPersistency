package P2Domein;

import java.util.Date;

public class Reiziger {
    private int reiziger_id;
    private String voorletters;
    private String tussenvogel;
    private String achternaam;
    private Date geboortedatum;


    public Reiziger( int id , String Voorletters , String Tussenvogel, String Achternaam, Date geboortedatum){
        reiziger_id = id;
        voorletters = Voorletters;
        tussenvogel = Tussenvogel;
        achternaam = Achternaam;
        this.geboortedatum = geboortedatum;

    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvogel() {
        return tussenvogel;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public void setTussenvogel(String tussenvogel) {
        this.tussenvogel = tussenvogel;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "reiziger_id=" + reiziger_id +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvogel='" + tussenvogel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                '}';
    }
}
