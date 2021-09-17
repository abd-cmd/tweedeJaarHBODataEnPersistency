package P4_Domein;

import java.util.ArrayList;
import java.sql.Date;

public class Reiziger {

        private  int reizigerId;
        private  String voorletters;
        private  String tussenvoegsel;
        private  String achternaam;
        private  Date geboortedatum;
        private Adres adres;
        private ArrayList<Ov_chipkaart> ov_chipkaarts;

        public Reiziger(int id , String voorletters , String tussenvoegsel, String achternaam, Date geboortedatum){
            reizigerId = id;
            this.voorletters = voorletters;
            this.tussenvoegsel = tussenvoegsel;
            this.achternaam = achternaam;
            this.geboortedatum = geboortedatum;
            this.ov_chipkaarts = new ArrayList<>();


        }



    public ArrayList<Ov_chipkaart> getOv_chipkaarts() {
        return ov_chipkaarts;
    }

    public void setOv_chipkaarts(ArrayList<Ov_chipkaart> ov_chipkaarts) {
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

        @Override
        public String toString() {

              String out = "Reiziger{" +
                      "reiziger_id=" + reizigerId +
                      ", voorletters='" + voorletters + '\'' +
                      ", tussenvogel='" + tussenvoegsel + '\'' +
                      ", achternaam='" + achternaam + '\'' +
                      ", geboortedatum=" + geboortedatum +
                      '}' ;

            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if (ov_chipkaarts == null && adres != null) {
                    out = "Reiziger{" +
                            "reiziger_id=" + reizigerId +
                            ", voorletters='" + voorletters + '\'' +
                            ", tussenvogel='" + tussenvoegsel + '\'' +
                            ", achternaam='" + achternaam + '\'' +
                            ", geboortedatum=" + geboortedatum +
                            '}' + " " + adres.toString();
                }
            }

            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if (ov_chipkaarts != null && adres == null) {
                    out = "Reiziger{" +
                            "reiziger_id=" + reizigerId +
                            ", voorletters='" + voorletters + '\'' +
                            ", tussenvogel='" + tussenvoegsel + '\'' +
                            ", achternaam='" + achternaam + '\'' +
                            ", geboortedatum=" + geboortedatum +
                            '}' + ov_chipkaart.toString();
                }
            }

            for (Ov_chipkaart ov_chipkaart : ov_chipkaarts) {
                if (ov_chipkaarts.size() != 0 && adres != null) {
                    out = "Reiziger{" +
                            "reiziger_id=" + reizigerId +
                            ", voorletters='" + voorletters + '\'' +
                            ", tussenvogel='" + tussenvoegsel + '\'' +
                            ", achternaam='" + achternaam + '\'' +
                            ", geboortedatum=" + geboortedatum +
                            '}'+ " " + adres.toString() + " "  + ov_chipkaart.toString();
                }
            }
            return out;

        }


}
