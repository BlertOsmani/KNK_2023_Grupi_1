package Models;

import Services.PasswordHasher;

import java.security.NoSuchAlgorithmException;

public class LoginModel {
    public String username;
    public String password;

    public LoginModel(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = PasswordHasher.hashString(password);

    }

}
