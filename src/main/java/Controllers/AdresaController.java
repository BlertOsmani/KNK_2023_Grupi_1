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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import Models.AdresaModel;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

}
