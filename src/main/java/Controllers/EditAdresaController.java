package Controllers;

import AdminDashboard.AdminDashboard;
import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Repositories.AdresaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import EditAdresa.EditAdresa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class EditAdresaController {

    @FXML
    private TextField Fshati;

    @FXML
    private TextField Hyrja;

    @FXML
    private TextField Komuna;

    @FXML
    private TextField Numri;

    @FXML
    private TextField NumriPostal;

    @FXML
    private TextField Objekti;

    @FXML
    private TextField Qyteti;

    @FXML
    private TextField Rruga;

    @FXML
    private TextField adresaId;

    @FXML
    private Label adresaLabel;

    @FXML
    private Label fshatiLabel;

    @FXML
    private Menu gjuha;

    @FXML
    private Label hyrjaLabel;

    @FXML
    private Label komunaLabel;

    @FXML
    private Label numriLabel;

    @FXML
    private Label numriPostalLabel;

    @FXML
    private Label objektiLabel;

    @FXML
    private RadioButton perhershem;

    @FXML
    private RadioButton perkohshem;

    @FXML
    private Label qytetiLabel;

    @FXML
    private Label rrugaLabel;

    @FXML
    private Label teDhenatPersonale;

    @FXML
    private Button updateAdresen;

    public int adressId;

    public void getAdressId(int id){
        this.adressId = id;
        adresaId.setText(String.valueOf(adressId));
    }

    @FXML
    void updateAdresen(ActionEvent event) {
        try {
            // Get the values entered in the text fields
            String llojiVendbanimit = "";
            int numriValue = Integer.parseInt(Numri.getText());
            if (perhershem.isSelected()) {
                llojiVendbanimit = "1";
            }
            if (perkohshem.isSelected()) {
                llojiVendbanimit = "0";
            }

            Connection connection = ConnectionUtil.getConnection();
            if (connection != null) {
                AdresaModel editAdresaModel = new AdresaModel(Integer.parseInt(adresaId.getText()), Qyteti.getText(), Komuna.getText(), Fshati.getText(), Rruga.getText(), Objekti.getText(), Hyrja.getText(), numriValue, Integer.parseInt(NumriPostal.getText()), llojiVendbanimit);

                // Update the address in the database
                AdresaRepository editAdresaRepository = new AdresaRepository();
                editAdresaRepository.update(editAdresaModel, connection);

                System.out.println("Adresa u perditsua me sukses");

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
            } else {
                System.out.println("Failed to update address in the database");
            }
        } catch (SQLException e) {
            System.err.println("Error updating address in the database: " + e.getMessage());
        }
    }

    @FXML
    void translateAl(ActionEvent event) {

    }

    @FXML
    void translateEn(ActionEvent event) {

    }


}
