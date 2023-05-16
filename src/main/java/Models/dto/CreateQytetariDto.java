package Models.dto;

public class CreateQytetariDto {
    public String NrPersonal;
    public String Emri;
    public String EmriBabait;
    public String EmriNenes;
    public String Mbiemri;
    public String Ditelindja;
    public String Email;
    public String NrTel;
    public String Gjinia;
    public int Adresa;

    public CreateQytetariDto(String nrPersonal, String emri, String emriBabait, String emriNenes, String mbiemri, String ditelindja, String email,String nrTel, String gjinia, int adresa) {
        NrPersonal = nrPersonal;
        Emri = emri;
        EmriBabait = emriBabait;
        EmriNenes = emriNenes;
        Mbiemri = mbiemri;
        Ditelindja = ditelindja;
        Email = email;
        NrTel = nrTel;
        Gjinia = gjinia;
        Adresa = adresa;
    }
}
