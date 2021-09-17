package P3_Domein;

import P3_DAO_EN_DAOSQL.AdresDAOsql;

import java.util.Date;

public class Reiziger {

        private  int reizigerId;
        private  String voorletters;
        private  String tussenvoegsel;
        private  String achternaam;
        private  Date geboortedatum;
        private Adres adres;

        public Reiziger(int id , String voorletters , String tussenvoegsel, String achternaam, Date geboortedatum){
            reizigerId = id;
            this.voorletters = voorletters;
            this.tussenvoegsel = tussenvoegsel;
            this.achternaam = achternaam;
            this.geboortedatum = geboortedatum;

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
                    '}' + " " + adres.toString();

            if (adres == null) {

                out = "Reiziger{" +
                        "reiziger_id=" + reizigerId +
                        ", voorletters='" + voorletters + '\'' +
                        ", tussenvogel='" + tussenvoegsel + '\'' +
                        ", achternaam='" + achternaam + '\'' +
                        ", geboortedatum=" + geboortedatum +
                        '}';
            }
            return out;
        }


}
