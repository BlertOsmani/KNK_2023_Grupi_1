package Models.dto;

import Services.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class CreateUserDto {
    public String NrPersonal;
    public String Emri;
    public String Mbiemri;
    public String Email;
    public String Username;
    public String Salt;
    public String Password;

    public CreateUserDto(String nrPersonal, String emri, String mbiemri, String email, String username,String salt, String password) throws NoSuchAlgorithmException {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Email = email;
        Username = username;
        Salt = salt;
        Password = PasswordHasher.generateSaltedHash(password, salt);
    }
}
