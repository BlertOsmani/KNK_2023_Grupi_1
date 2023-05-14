package Controllers;

import Adresa.Adresa;
import DbConnection.ConnectionUtil;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import AdminDashboard.AdminDashboard;
import Models.LoginModel;
import Repositories.LoginRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public Button loginBtn;

    @FXML
    public PasswordField password;

    @FXML
    public TextField username;

    @FXML
    void login(ActionEvent event) {
        try {
            Connection  connection = ConnectionUtil.getConnection();
            if (connection != null) {
                LoginModel loginModel = new LoginModel(username.getText(), password.getText());
                LoginRepository loginRepository = new LoginRepository();
                boolean validlogin = loginRepository.login(loginModel, connection);
                if(validlogin) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(AdminDashboard.class.getResource("AdminDashboard.fxml"));
                        Pane pane = fxmlLoader.load();
                        Scene scene = new Scene(pane);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        System.err.println("Error loading FXML file: " + e.getMessage());
                    }

                }
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

