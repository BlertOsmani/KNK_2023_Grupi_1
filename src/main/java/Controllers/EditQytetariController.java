package Controllers;

import AdresatDashboard.AdresatDashboard;
import Dashboard.Dashboard;
import DbConnection.ConnectionUtil;
import QytetaretDashboard.QytetaretDashboard;
import Models.QytetariModel;
import Models.dto.CreateQytetariDto;
import Repositories.QytetariRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class EditQytetariController {
    @FXML
    private TextField AdresaEdit;

    @FXML
    private Button Back;

    @FXML
    private TextField adresaId;

    @FXML
    private Button adresatBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private DatePicker ditelindja;

    @FXML
    private Label ditelindjaLabel;

    @FXML
    private TextField email;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emri;

    @FXML
    private TextField emriBabait;

    @FXML
    private Label emriBabaitLabel;

    @FXML
    private Label emriLabel;

    @FXML
    private TextField emriNenes;

    @FXML
    private Label emriNenesLabel;

    @FXML
    private Label errorQytetariEkziston;

    @FXML
    private RadioButton femer;

    @FXML
    private Label gjiniaLabel;

    @FXML
    private TextField idQytetari;

    @FXML
    private RadioButton mashkull;

    @FXML
    private TextField mbiemri;

    @FXML
    private Label mbiemriLabel;

    @FXML
    private Menu menuGjuha;

    @FXML
    private TextField nrPersonal;

    @FXML
    private Label nrPersonalLabel;

    @FXML
    private TextField nrTel;

    @FXML
    private Label nrTelefonitLabel;

    @FXML
    private Label personalData;

    @FXML
    private Button qytetaretBtn;

    @FXML
    private Button updateQytetarin;

    public int GetId;
    public String GetNrPersonal;
    public String GetEmri;
    public String GetEmriBabait;
    public String GetEmriNenes;
    public String GetMbiemri;
    public String GetDitelindja;
    public String GetEmail;
    public String GetNrTel;
    public String GetGjinia;
    public int GetAdresa;
    public String currentText;

    public void initialize() {
        currentText = "";
        // Add a listener to the text property to enforce the mask
        nrTel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Strip all non-digit characters from the input
                String strippedText = newValue.replaceAll("[^\\d]", "");

                // Insert the spaces between the segments of the mask
                StringBuilder formattedText = new StringBuilder();
                for (int i = 0; i < strippedText.length(); i++) {
                    if (i == 0) {
                        formattedText.append("+").append(strippedText.charAt(i));
                    } else if (i == 3 || i == 5 || i == 8) {
                        formattedText.append(" ").append(strippedText.charAt(i));
                    } else {
                        formattedText.append(strippedText.charAt(i));
                    }
                }

                // Set the new text on the TextField
                if (!formattedText.toString().equals(currentText)) {
                    currentText = formattedText.toString();
                    nrTel.setText(currentText);
                    nrTel.positionCaret(currentText.length());
                }
            }
        });

        femer.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mashkull.setSelected(false);
            }
        });
        mashkull.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                femer.setSelected(false);
            }
        });
    }


    public void setQytetariFields(int id,String NrPersonal, String Emri, String EmriBabait ,String EmriNenes,String Mbiemri, String Ditelindja ,String Email,String NrTel, String Gjinia, int adresa){
        this.GetId = id;
        this.GetNrPersonal = NrPersonal;
        this.GetEmri = Emri;
        this.GetEmriBabait = EmriBabait;
        this.GetEmriNenes = EmriNenes;
        this.GetMbiemri = Mbiemri;
        this.GetDitelindja = Ditelindja;
        this.GetEmail = Email;
        this.GetNrTel = NrTel;
        this.GetGjinia = Gjinia;
        this.GetAdresa = adresa;

        idQytetari.setText(String.valueOf(GetId));
        AdresaEdit.setText(String.valueOf(GetAdresa));
        nrPersonal.setText(GetNrPersonal);
        emri.setText(GetEmri);
        emriBabait.setText(GetEmriBabait);
        emriNenes.setText(GetEmriNenes);
        mbiemri.setText(GetMbiemri);
        ditelindja.setValue(LocalDate.parse(Ditelindja, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        email.setText(GetEmail);
        nrTel.setText(String.valueOf(GetNrTel));
        if(GetGjinia == "Femer"){
            femer.setSelected(false);
            mashkull.setSelected(true);
        }
        else{
            femer.setSelected(true);
            mashkull.setSelected(false);
        }
        adresaId.setText(String.valueOf(adresa));
    }

    @FXML
    void updateQytetarin(ActionEvent event) {
        try {
            LocalDate Ditelindja = ditelindja.getValue();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .toFormatter();
            String ditelindjaStr = Ditelindja == null ? null : Ditelindja.format(formatter);
            String gjinia = "";
            if(femer.isSelected()){
                gjinia = "Femer";
            }
            if(mashkull.isSelected()){
                gjinia = "Mashkull";
            }
            Connection connection = ConnectionUtil.getConnection();

            if (connection != null) {
                // Insert the new address into the database
                QytetariModel editQytetariModel = new QytetariModel(Integer.parseInt(idQytetari.getText()), nrPersonal.getText(), emri.getText(), emriBabait.getText(), emriNenes.getText(), mbiemri.getText(), ditelindjaStr, email.getText(), nrTel.getText(),gjinia, Integer.parseInt(adresaId.getText()));
                QytetariRepository editQytetariRepository = new QytetariRepository();
                editQytetariRepository.update(editQytetariModel, connection);

                System.out.print("Qytetari u perditesua me sukses!");

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(QytetaretDashboard.class.getResource("QytetaretDashboard.fxml"));
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
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating qytetari into database: " + e.getMessage());
        }
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
    void translateAl(ActionEvent event) {

    }

    @FXML
    void translateEn(ActionEvent event) {

    }

}
