package P4_Domein;

import java.util.Date;

public class Ov_chipkaart {
  private int kaart_nummer;
  private Date geldig_tot;
  private int klaase;
  private int saldo;
  private Reiziger reiziger;

    public Ov_chipkaart(int kaart_nummer, Date geldig_tot, int klaase, int saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klaase = klaase;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.reiziger.getOv_chipkaarts().add(this);
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

    @Override
    public String toString() {
        return "Ov_chipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klaase=" + klaase +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger.getReizigerId() +
                '}' ;
    }
}
