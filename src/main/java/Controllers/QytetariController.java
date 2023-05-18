package Controllers;

import Adresa.Adresa;
import DbConnection.ConnectionUtil;
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

    @FXML
    public TextField Adresa;

    @FXML
    public DatePicker Ditelindja;

    @FXML
    public TextField Email;

    @FXML
    public TextField Emri;

    @FXML
    public TextField EmriBabait;

    @FXML
    public TextField EmriNenes;

    @FXML
    public RadioButton Femer;

    @FXML
    public RadioButton Mashkull;

    @FXML
    public TextField Mbiemri;

    @FXML
    public TextField NrPersonal;

    @FXML
    public TextField NrTel;

    @FXML
    public Button Ruaj;

    @FXML
    public Button back;
    @FXML
    public Label errorNrPersonal;

    public String currentText;

    @FXML
    public Label ditelindjaError;

    @FXML
    public Label emailError;

    @FXML
    public Label emriBabaitError;

    @FXML
    public Label emriNenesError;

    @FXML
    public Label errorEmri;

    @FXML
    public Label gjiniaError;

    @FXML
    public Label mbiemriError;

    @FXML
    public Label nrTelError;

    public String qytetiValue;
    public String rrugaValue;
    public int numriValue;
    public int numriPostalValue;
    public String komuna;
    public String fshati;
    public String objekti;
    public String hyrja;
    @FXML
    public Label emriBabaitLabel;
    @FXML
    public Label emriLabel;
    @FXML
    public Label emriNenesLabel;
    @FXML
    public Label gjiniaLabel;
    @FXML
    public Label mbiemriLabel;
    @FXML
    public Menu menuGjuha;
    @FXML
    public Label nrPersonalLabel;
    @FXML
    public Label nrTelefonitLabel;
    @FXML
    public Label personalData;
    @FXML
    public Label qytetariAdresa;
    @FXML
    public Label ditelindjaLabel;
    @FXML
    public Label emailLabel;
    @FXML
    public Label qytetariAdresaLabel;

    public int AdresaId;

    @FXML
    public TextField adresaId;

    @FXML
    public Label errorQytetariEkziston;

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
                    CreateQytetariDto qytetariDto = new CreateQytetariDto(NrPersonal.getText(), Emri.getText(), EmriBabait.getText(), EmriNenes.getText(), Mbiemri.getText(), ditelindjaStr, Email.getText(), NrTel.getText(),gjinia, 1);
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
    void backToAdresa(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Adresa.class.getResource("Adresa.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        menuGjuha.setText(translate.getString("qytetari.menu.gjuha"));
        qytetariAdresa.setText(translate.getString("qytetari.label.adresa"));
        personalData.setText(translate.getString("qytetari.label.teDhenatPersonale"));
        qytetariAdresaLabel.setText(translate.getString("qytetari.label.adresa1"));
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
        back.setText(translate.getString("qytetari.button.back"));
        Ruaj.setText(translate.getString("qytetari.button.save"));
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
