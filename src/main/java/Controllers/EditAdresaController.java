package Controllers;

import Adresa.Adresa;
import AdresatDashboard.AdresatDashboard;
import Dashboard.Dashboard;
import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import QytetaretDashboard.QytetaretDashboard;
import Repositories.AdresaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Button adresatBtn;

    @FXML
    private Button backToDashboard;

    @FXML
    private Button dashboardBtn;

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
    private Button qytetaretBtn;

    @FXML
    private Label qytetiLabel;

    @FXML
    private Label rrugaLabel;

    @FXML
    private Button updateAdresen;

    public int GetId;
    public String GetQyteti;
    public String GetKomuna;
    public String GetFshati;
    public String GetRruga;
    public String GetObjekti;
    public String GetHyrja;
    public int GetNumri;
    public int GetNumriPostal;
    public String GetLlojiVendbanimit;

    public void setAdressFields(int id, String qyteti, String komuna,String fshati, String rruga, String objekti, String hyrja, int numri, int numriPostal, String llojiVendbanimit){
        this.GetId = id;
        this.GetQyteti = qyteti;
        this.GetKomuna = komuna;
        this.GetFshati = fshati;
        this.GetRruga = rruga;
        this.GetObjekti = objekti;
        this.GetHyrja = hyrja;
        this.GetNumri = numri;
        this.GetNumriPostal = numriPostal;
        this.GetLlojiVendbanimit = llojiVendbanimit;

        adresaId.setText(String.valueOf(GetId));
        Qyteti.setText(GetQyteti);
        Komuna.setText(GetKomuna);
        Fshati.setText(GetFshati);
        Rruga.setText(GetRruga);
        Objekti.setText(GetObjekti);
        Hyrja.setText(GetHyrja);
        Numri.setText(String.valueOf(GetNumri));
        NumriPostal.setText(String.valueOf(GetNumriPostal));
        if(GetLlojiVendbanimit == "I perhershem"){
            perhershem.setSelected(true);
            perkohshem.setSelected(false);
        }
        else{
            perhershem.setSelected(false);
            perkohshem.setSelected(true);
        }

    }

    @FXML
    void openAdresatDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdresatDashboard.class.getResource("AdresatDashboard.fxml"));
        Pane pane = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openQytetaretDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QytetaretDashboard.class.getResource("QytetaretDashboard.fxml"));
        Pane pane = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Dashboard.class.getResource("Dashboard.fxml"));
        Pane pane = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
                    FXMLLoader fxmlLoader = new FXMLLoader(AdresatDashboard.class.getResource("AdresatDashboard.fxml"));
                    Pane pane = fxmlLoader.load();
                    ScrollPane scrollPane = new ScrollPane(pane);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setFitToHeight(true);

                    Scene scene = new Scene(scrollPane, 1400, 600);
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

    @FXML
    void openAdminDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdresatDashboard.class.getResource("AdresatDashboard.fxml"));
        Pane pane = fxmlLoader.load();
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
