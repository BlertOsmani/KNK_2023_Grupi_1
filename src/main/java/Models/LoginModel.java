package Models;

import Repositories.UserRepository;
import Services.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginModel {
    public String username;
    public String password;

    public LoginModel(String username, String password) throws NoSuchAlgorithmException, SQLException {
        this.username = username;
        this.password = PasswordHasher.generateSaltedHash(password, UserRepository.getSalt(this.username));
    }

}
