package Login;

import DbConnection.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginController {

    @FXML
    public Button loginBtn;

    @FXML
    public PasswordField password;

    @FXML
    public TextField username;

    @FXML
    void login(ActionEvent event) {


    }

}

