package Models;

import Services.PasswordHasher;

public class UserModel {
    public String NrPersonal;
    public String Emri;
    public String Mbiemri;
    public String Email;
    public String Username;
    public String Password;

    public UserModel(String nrPersonal, String emri, String mbiemri, String email, String username, String password) {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Email = email;
        Username = username;
        Password = PasswordHasher.generateSaltedHash(password, PasswordHasher.generateSalt());
    }
}