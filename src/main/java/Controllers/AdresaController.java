package Controllers;

import DbConnection.ConnectionUtil;
import Models.dto.CreateAdresaDto;
import Qytetari.Qytetari;
import Repositories.AdresaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Models.AdresaModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.stage.Stage;

public class AdresaController {


    @FXML
    public RadioButton Perhershem;

    @FXML
    public RadioButton Perkohshem;

    @FXML
    public TextField fshati;

    @FXML
    public Pane googleMapPane;

    @FXML
    public TextField hyrja;

    @FXML
    public TextField komuna;

    @FXML
    public TextField numri;

    @FXML
    public TextField numriPostal;

    @FXML
    public TextField objekti;

    @FXML
    public TextField qyteti;

    @FXML
    public TextField rruga;

    @FXML
    public Button shtoAdresen;
    @FXML
    public Label qytetiLabel;
    @FXML
    public Label komunaLabel;
    @FXML
    public Label fshatiLabel;
    @FXML
    public Label rrugaLabel;
    @FXML
    public Label objektiLabel;
    @FXML
    public Label hyrjaLabel;
    @FXML
    public Label numriLabel;
    @FXML
    public Label numriPostalLabel;
    @FXML
    public Label adresaLabel;
    @FXML
    public Label teDhenatPersonale;
    @FXML
    public ComboBox<String> llojiVendbanimit;
    @FXML
    public Menu gjuha;



    public void initialize(){
        Pane parent = (Pane) shtoAdresen.getParent();
        parent.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                shtoAdresen.fire();
                event.consume();
            }
        });

        shtoAdresen.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                shtoAdresen(new ActionEvent());
            }
        });
    }

    @FXML
    void shtoAdresen(ActionEvent event) {
       try {
            // Get the values entered in the text fields
            String llojiVendbanimit = "";
            int numriValue = Integer.parseInt(numri.getText());
            if(Perhershem.isSelected()){
                llojiVendbanimit = "1";
            }
            if(Perkohshem.isSelected()){
                llojiVendbanimit = "0";
            }
            Connection connection = ConnectionUtil.getConnection();
            if(connection !=null) {

                CreateAdresaDto adresaDto = new CreateAdresaDto(qyteti.getText(), komuna.getText(), fshati.getText(), rruga.getText(), objekti.getText(), hyrja.getText(), numriValue, Integer.parseInt(numriPostal.getText()), llojiVendbanimit);
                // Insert the new address into the database
                AdresaRepository adresaRepository = new AdresaRepository();
                adresaRepository.insert(adresaDto, connection);
                System.out.println("Adresa u shtua me sukses");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Qytetari.class.getResource("Qytetari.fxml"));
                    Pane pane = fxmlLoader.load();
                    QytetariController qytetariController = fxmlLoader.getController();
                    qytetariController.setAddressInfo(adresaDto.Qyteti, adresaDto.Komuna, adresaDto.Fshati, adresaDto.Rruga, adresaDto.Objekti, adresaDto.Hyrja, adresaDto.Numri, adresaDto.NumriPostal);
                    Scene scene = new Scene(pane);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading FXML file: " + e.getMessage());
                }
            }
            else{
                System.out.println("Failed to insert adress in the database");
            }
         }catch(SQLException e) {
            System.err.println("Error inserting address into database: " + e.getMessage());
        }
    }



    @FXML
    void shtoLokacionin(ActionEvent event) {

    }

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        gjuha.setText(translate.getString("adresa.menu.gjuha"));
        adresaLabel.setText(translate.getString("adresa.label.adresa"));
        teDhenatPersonale.setText(translate.getString("adresa.label.teDhenatPersonale"));
        String promptText = translate.getString("adresa.combobox.llojiVendbanimit");
        llojiVendbanimit.setPromptText(promptText);
        qytetiLabel.setText(translate.getString("adresa.label.qyteti"));
        komunaLabel.setText(translate.getString("adresa.label.komuna"));
        fshatiLabel.setText(translate.getString("adresa.label.fshati"));
        rrugaLabel.setText(translate.getString("adresa.label.rruga"));
        objektiLabel.setText(translate.getString("adresa.label.objekti"));
        hyrjaLabel.setText(translate.getString("adresa.label.hyrja"));
        numriLabel.setText(translate.getString("adresa.label.numri"));
        numriPostalLabel.setText(translate.getString("adresa.label.numriPostal"));
        Perhershem.setText(translate.getString("adresa.radiobutton.perhershem"));
        Perkohshem.setText(translate.getString("adresa.radiobutton.perkohshem"));
        shtoAdresen.setText(translate.getString("adresa.button.next"));
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
