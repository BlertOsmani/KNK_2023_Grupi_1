package Signup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Signup extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Signup.class.getResource("Signup.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane, pane.getWidth()-100, pane.getHeight()-100);
        stage.setTitle("Aplikacion per regjistrimin e adresave dhe vendbanimeve");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[]args) throws SQLException {
        launch();
    }
}
