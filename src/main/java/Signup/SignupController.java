package Signup;

import DbConnection.ConnectionUtil;
import Login.Login;
import Models.UserModel;
import Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Services.PasswordHasher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SignupController {

    @FXML
    public PasswordField Password;

    @FXML
    public TextField Username;

    @FXML
    public TextField email;

    @FXML
    public TextField emri;

    @FXML
    public TextField mbiemri;

    @FXML
    private TextField nrPersonal;

    @FXML
    public Button signupBtn;

    @FXML
    void signup(ActionEvent event) {
        try {
            Connection connection = ConnectionUtil.getConnection();
            if(connection !=null) {
                String salt = PasswordHasher.generateSalt();
                String saltedHash = PasswordHasher.generateSaltedHash(Password.getText(), salt);
                UserModel userModel = new UserModel(nrPersonal.getText(), emri.getText(), mbiemri.getText(), email.getText(), Username.getText(), saltedHash);
                UserRepository userRepository = new UserRepository();
                userRepository.insert(userModel, connection);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
                    Pane pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading FXML file: " + e.getMessage());
                }
            }
            else{
                System.out.println("Failed to insert user in the database");
            }
        }catch(SQLException e) {
            System.err.println("Error inserting user into database: " + e.getMessage());
        }
    }

}

