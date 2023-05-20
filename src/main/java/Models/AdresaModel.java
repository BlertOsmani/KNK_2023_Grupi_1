package Models;


import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class AdresaModel{
    public int Id;
    public String Qyteti;
    public String Komuna;
    public String Fshati;
    public String Rruga;
    public String Objekti;
    public String Hyrja;

    public int Numri;
    public int NumriPostal;
    public String LlojiVendbanimit;

    public AdresaModel(int id,String qyteti, String komuna, String fshati, String rruga, String objekti, String hyrja, int numri, int numriPostal, String llojiVendbanimit) {
        Id = id;
        Qyteti = qyteti;
        Komuna = komuna;
        Fshati = fshati;
        Rruga = rruga;
        Objekti = objekti;
        Hyrja = hyrja;
        Numri = numri;
        NumriPostal = numriPostal;
        LlojiVendbanimit = llojiVendbanimit;
    }


    public String getadresaFshati() {
        return Fshati;
    }

    public String getadresaHyrja() {
        return Hyrja;
    }


    public String getAdresaQyteti() {
        return Qyteti;
    }

    public String getAdresaKomuna() {
        return Komuna;
    }

    public String getAdresaFshati() {
        return Fshati;
    }

    public String getAdresaRruga() {
        return Rruga;
    }

    public String getAdresaObjekti() {
        return Objekti;
    }

    public String getAdresaHyrja() {
        return Hyrja;
    }

    public int getAdresaNumri() {
        return Numri;
    }

    public int getAdresaNumriPostal() {
        return NumriPostal;
    }

    public Object getAdresaVendbanimi() {
        return LlojiVendbanimit;
    }
}
