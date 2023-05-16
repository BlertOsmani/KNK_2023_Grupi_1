package Controllers;

import DbConnection.ConnectionUtil;
import Login.Login;
import Models.UserModel;
import Models.dto.CreateUserDto;
import Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Services.PasswordHasher;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
    public Label errorSignup;


    @FXML
    void signup(ActionEvent event) {
            try {
                if (emri.getText().isEmpty() || mbiemri.getText().isEmpty() || email.getText().isEmpty() || Username.getText().isEmpty() || Password.getText().isEmpty() || nrPersonal.getText().isEmpty()) {
                    errorSignup.setVisible(true);
                    return;
                }
                Connection connection = ConnectionUtil.getConnection();
                if (connection != null) {
                    CreateUserDto userDto = new CreateUserDto(nrPersonal.getText(), emri.getText(), mbiemri.getText(), email.getText(), Username.getText(), PasswordHasher.generateSalt() ,Password.getText());
                    UserRepository userRepository = new UserRepository();
                    userRepository.insert(userDto, connection);
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
                } else {
                    System.out.println("Failed to insert user in the database");
                }
            } catch (SQLException e) {
                System.err.println("Error inserting user into database: " + e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
    }

}

