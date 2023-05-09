package Models;

import Services.PasswordHasher;

public class LoginModel {
    public String username;
    public String password;

    public LoginModel(String username, String password){
        this.username = username;
        this.password = PasswordHasher.generateSaltedHash(password, PasswordHasher.generateSalt());

    }

}
