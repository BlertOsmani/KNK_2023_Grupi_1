package Repositories;

import DbConnection.ConnectionUtil;
import Models.LoginModel;
import Models.UserModel;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Services.PasswordHasher;

public class UserRepository {
    public void insert(UserModel userModel, Connection connection) throws SQLException {
        String sql = "INSERT INTO user(NrPersonal, Emri, Mbiemri, Email, Username,Salt, Password, Created_at) VALUES (?, ?, ?, ?, ?, ?, ?,NOW())";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userModel.NrPersonal);
        statement.setString(2, userModel.Emri);
        statement.setString(3, userModel.Mbiemri);
        statement.setString(4, userModel.Email);
        statement.setString(5, userModel.Username);
        statement.setString(6, userModel.Salt);
        statement.setString(7, userModel.Password);
        statement.executeUpdate();
    }

    public static String getSalt(String username) throws SQLException, NoSuchAlgorithmException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select * from user where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String salt = resultSet.getString("Salt");
            return salt;
        }
        else{
            return null;
        }
    }
    public static String getPassword(String username) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select * from user where username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            String password = resultSet.getString("Password");
            return password;
        }
        else{
            return null;
        }
    }
}
