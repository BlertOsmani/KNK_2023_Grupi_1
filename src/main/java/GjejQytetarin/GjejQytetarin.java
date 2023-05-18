package GjejQytetarin;


import Adresa.Adresa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.application.Application.launch;

public class GjejQytetarin extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(GjejQytetarin.class.getResource("GjejQytetarin.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane, pane.getWidth()-100, pane.getHeight()-100);
        stage.setTitle("Aplikacion per kerkimin e qytetarit");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[]args) throws SQLException {
        launch();
    }
}
