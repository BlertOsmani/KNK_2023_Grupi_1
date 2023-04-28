package Repositories;

import Models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Services.PasswordHasher;

public class UserRepository {
    public void insert(UserModel userModel, Connection connection) throws SQLException {
        String sql = "INSERT INTO user(NrPersonal, Emri, Mbiemri, Email, Username, Password, Created_at) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userModel.NrPersonal);
        statement.setString(2, userModel.Emri);
        statement.setString(3, userModel.Mbiemri);
        statement.setString(4, userModel.Email);
        statement.setString(5, userModel.Username);
        statement.setString(6, userModel.Password);
        statement.executeUpdate();
    }

}
