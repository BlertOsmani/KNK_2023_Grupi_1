package Models;

import Services.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class UserModel {
    public int Id;
    public String NrPersonal;
    public String Emri;
    public String Mbiemri;
    public String Email;
    public String Username;

    public UserModel(int id,String nrPersonal, String emri, String mbiemri, String email, String username) throws NoSuchAlgorithmException {
        Id = id;
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Email = email;
        Username = username;
    }
}
