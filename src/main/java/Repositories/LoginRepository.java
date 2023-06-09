package Repositories;

import Models.LoginModel;
import Services.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    public int login(LoginModel loginModel, Connection connection) {
        String sql = "SELECT * FROM user WHERE Username = ? and Password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginModel.username);
            statement.setString(2, loginModel.password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                // No rows returned, so username is incorrect
                System.out.println("Username or password is incorrect.");
                return -1;
            } else {
                int Id = resultSet.getInt("Id");

                    // Password is correct, so login is successful
                    System.out.println("Login successful!");
                    // Open adminDashboard tab here
                    return Id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }







}
