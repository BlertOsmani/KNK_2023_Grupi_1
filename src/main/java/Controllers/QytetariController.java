package Controllers;

import Adresa.Adresa;
import AdresatDashboard.AdresatDashboard;
import Dashboard.Dashboard;
import DbConnection.ConnectionUtil;
import Models.dto.CreateQytetariDto;
import QytetaretDashboard.QytetaretDashboard;
import Qytetari.Qytetari;
import Repositories.QytetariRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QytetariController {

    public String qytetiValue;
    public String rrugaValue;
    public int numriValue;
    public int numriPostalValue;
    public String komuna;
    public String fshati;
    public String objekti;
    public String hyrja;
    @FXML
    public TextField Adresa;

    @FXML
    private DatePicker Ditelindja;

    @FXML
    private TextField Email;

    @FXML
    private TextField Emri;

    @FXML
    private TextField EmriBabait;

    @FXML
    private TextField EmriNenes;

    @FXML
    private RadioButton Femer;

    @FXML
    private RadioButton Mashkull;

    @FXML
    private TextField Mbiemri;

    @FXML
    private TextField NrPersonal;

    @FXML
    private TextField NrTel;

    @FXML
    private Button Ruaj;

    @FXML
    private TextField adresaId;

    @FXML
    private Button adresatBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Label ditelindjaError;

    @FXML
    private Label ditelindjaLabel;

    @FXML
    private Label emailError;

    @FXML
    private Label emailLabel;

    @FXML
    private Label emriBabaitError;

    @FXML
    private Label emriBabaitLabel;

    @FXML
    private Label emriLabel;

    @FXML
    private Label emriNenesError;

    @FXML
    private Label emriNenesLabel;

    @FXML
    private Label errorEmri;

    @FXML
    private Label errorNrPersonal;

    @FXML
    private Label errorQytetariEkziston;

    @FXML
    private Label gjiniaError;

    @FXML
    private Label gjiniaLabel;

    @FXML
    private Label mbiemriError;

    @FXML
    private Label mbiemriLabel;

    @FXML
    private Menu menuGjuha;

    @FXML
    private Label nrPersonalLabel;

    @FXML
    private Label nrTelError;

    @FXML
    private Label nrTelefonitLabel;

    @FXML
    private Label personalData;

    @FXML
    private Button qytetaretBtn;

    @FXML
    private Label qytetariAdresa;

    @FXML
    private Label qytetariAdresaLabel;

    public String currentText;
    public int AdresaId;


    public void initialize() {
        currentText = "";
        // Add a listener to the text property to enforce the mask
        NrTel.textProperty().addListener(new ChangeListener<String>() {
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
                    NrTel.setText(currentText);
                    NrTel.positionCaret(currentText.length());
                }
            }
        });

        Femer.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Mashkull.setSelected(false);
            }
        });
        Mashkull.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Femer.setSelected(false);
            }
        });


    }

    public void setAddressInfo(int id,String qytetiValue, String komuna, String fshati ,String rrugaValue,String objekti, String hyrja ,int numriValue,int numriPostalValue){
        this.qytetiValue = qytetiValue;
        this.komuna = komuna;
        this.fshati = fshati;
        this.rrugaValue = rrugaValue;
        this.objekti = objekti;
        this.hyrja = hyrja;
        this.numriValue = numriValue;
        this.numriPostalValue = numriPostalValue;
        String adresaValue = "";
        this.AdresaId = id;


        if (qytetiValue != null && !qytetiValue.isEmpty()) {
            adresaValue += qytetiValue;
        }

        if (komuna != null && !komuna.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += komuna;
        }

        if (fshati != null && !fshati.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += fshati;
        }

        if (rrugaValue != null && !rrugaValue.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += rrugaValue;
        }

        if (objekti != null && !objekti.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += objekti;
        }

        if (hyrja != null && !hyrja.isEmpty()) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += hyrja;
        }

        if (numriValue != 0) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += String.valueOf(numriValue);
        }

        if (numriPostalValue != 0) {
            if (!adresaValue.isEmpty()) {
                adresaValue += ", ";
            }
            adresaValue += String.valueOf(numriPostalValue);
        }
        Adresa.setText(adresaValue);
        adresaId.setText(String.valueOf(AdresaId));

    }


    public void clearForm(){
        NrPersonal.setText("");
        Emri.setText("");
        EmriBabait.setText("");
        EmriNenes.setText("");
        Mbiemri.setText("");
        Ditelindja.setValue(null);
        Email.setText("");
        NrTel.setText("");
        Femer.setSelected(false);
        Mashkull.setSelected(false);
    }

    @FXML
    void ruaj(ActionEvent event) {

        try {
            // Get the values entered in the text fields
            String nrPersonal = NrPersonal.getText();
            String emri = Emri.getText();
            String emriBabait = EmriBabait.getText();
            String emriNenes = EmriNenes.getText();
            String mbiemri = Mbiemri.getText();
            LocalDate ditelindja = Ditelindja.getValue();
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    .toFormatter();
            String ditelindjaStr = ditelindja == null ? null : ditelindja.format(formatter);



            String email = Email.getText();
            String nrTel = NrTel.getText();
            String gjinia = "";
            String adresa = Adresa.getText();
            if(Femer.isSelected()){
                gjinia = "Femer";
            }
            if(Mashkull.isSelected()){
                gjinia = "Mashkull";
            }

            //Nese njera prej textfieldave eshte i zbrazet

            if(nrPersonal.length() < 10 || nrPersonal.length() > 10){
                errorNrPersonal.setVisible(true);
                errorNrPersonal.setText("Numri Personal Gabim!");
                return;
            }
            else if(nrPersonal == null || nrPersonal == ""){
                errorNrPersonal.setVisible(true);
                errorNrPersonal.setText("Kerkohet numri personal!");
                return;
            }
            else if(emri == null || emri.equals("")){
                errorEmri.setVisible(true);
                errorEmri.setText("Kerkohet emri!");
                return;
            }
            else if(emriBabait == null || emriBabait.equals("")){
                emriBabaitError.setVisible(true);
                emriBabaitError.setText("Kerkohet emri i babait!");
                return;
            }
            else if(emriNenes == null || emriNenes.equals("")){
                emriNenesError.setVisible(true);
                emriNenesError.setText("Kerkohet emri i nenes!");
                return;
            }
            else if(mbiemri == null || mbiemri.equals("")){
                mbiemriError.setVisible(true);
                mbiemriError.setText("Kerkohet mbiemri!");
            }
            else if(ditelindjaStr == null || ditelindjaStr.equals("")){
                ditelindjaError.setVisible(true);
                ditelindjaError.setText("Kerkohet ditelindja!");
            }
            else if(email == null || email.equals("")){
                emailError.setVisible(true);
                emailError.setText("Kerkohet email!");
                return;
            }
            else if(nrTel == null || nrTel.equals("")){
                nrTelError.setVisible(true);
                nrTelError.setText("Kerkohet numri i telefonit!");
                return;
            }
            else if(gjinia == null || gjinia.equals("")){
                gjiniaError.setVisible(true);
                gjiniaError.setText("Kerkohet gjinia!");
                return;
            }

            Connection connection = ConnectionUtil.getConnection();

            if (connection != null) {
                // Insert the new address into the database
                    CreateQytetariDto qytetariDto = new CreateQytetariDto(NrPersonal.getText(), Emri.getText(), EmriBabait.getText(), EmriNenes.getText(), Mbiemri.getText(), ditelindjaStr, Email.getText(), NrTel.getText(),gjinia, Integer.parseInt(adresaId.getText()));
                QytetariRepository qytetariRepository = new QytetariRepository();
                boolean QytetariExists = QytetariRepository.qytetariExists(NrPersonal.getText(), connection);
                if(QytetariExists == false) {
                    qytetariRepository.insert(qytetariDto, connection);
                    System.out.println("Qytetari u krijua me sukses");
                    clearForm();
                }
                else{
                    errorQytetariEkziston.setVisible(true);
                }

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting address into database: " + e.getMessage());
        }
    }

    @FXML
    void openAdresatDashboard(ActionEvent event) {
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
    }
    @FXML
    void openQytetaretDashboard(ActionEvent event) {
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

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        menuGjuha.setText(translate.getString("qytetari.menu.gjuha"));
        qytetariAdresa.setText(translate.getString("qytetari.label.adresa"));
        personalData.setText(translate.getString("qytetari.label.teDhenatPersonale"));
        qytetariAdresaLabel.setText(translate.getString("qytetari.label.adresa"));
        nrPersonalLabel.setText(translate.getString("qytetari.label.nrPersonal"));
        emriLabel.setText(translate.getString("qytetari.label.emri"));
        emriBabaitLabel.setText(translate.getString("qytetari.label.emriBabait"));
        emriNenesLabel.setText(translate.getString("qytetari.label.emriNenes"));
        mbiemriLabel.setText(translate.getString("qytetari.label.mbiemri"));
        ditelindjaLabel.setText(translate.getString("qytetari.label.ditelindja"));
        emailLabel.setText(translate.getString("qytetari.label.email"));
        nrTelefonitLabel.setText(translate.getString("qytetari.label.nrTelefonit"));
        gjiniaLabel.setText(translate.getString("qytetari.label.gjinia"));
        Femer.setText(translate.getString("qytetari.radiobutton.femer"));
        Mashkull.setText(translate.getString("qytetari.radiobutton.mashkull"));
        Ruaj.setText(translate.getString("qytetari.button.save"));
        qytetaretBtn.setText(translate.getString("adresat.button.Qytetaret"));
        dashboardBtn.setText(translate.getString("adresat.button.dashboard"));
        adresatBtn.setText(translate.getString("adresat.button.Adresat"));
    }
    public void translateEn(ActionEvent event){
        Locale.setDefault(new Locale("en"));
        this.translate();
    }

    public void translateAl(ActionEvent event){
        Locale.setDefault(new Locale("al"));
        this.translate();
    }
}
