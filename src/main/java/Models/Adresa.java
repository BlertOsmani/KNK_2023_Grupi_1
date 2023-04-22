package Models;



public class Adresa {
    public String Qyteti;
    public String Rruga;
    public String Numri;
    public String NumriPostal;
    public String LlojiVendbanimit;
    public String GjatesiaGjeografike;
    public String GjeresiaGjeografike;

    public Adresa(String qyteti, String rruga,String numri, String numriPostal, String gjatesiaGjeografike, String gjeresiaGjeografike){
        this.Qyteti = qyteti;
        this.Rruga = rruga;
        this.Numri = numri;
        this.NumriPostal = numriPostal;
        this.GjatesiaGjeografike = gjatesiaGjeografike;
        this.GjeresiaGjeografike = gjeresiaGjeografike;
    }
}
