package Models;



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



}
