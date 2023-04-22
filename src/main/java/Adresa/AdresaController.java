package Adresa;

import DbConnection.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import Models.Adresa;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class AdresaController {

    @FXML
    public RadioButton Perhershem;

    @FXML
    public RadioButton Perkohshem;
    @FXML
    public TextField gjatesiaGjeo;

    @FXML
    public TextField gjeresiaGjeo;

    @FXML
    public TextField numri;

    @FXML
    public TextField numriPostal;

    @FXML
    public TextField qyteti;

    @FXML
    public TextField rruga;

    @FXML
    public Button shtoAdresen;

    @FXML
    public Pane googleMapPane;

    @FXML
    public Button shtoLokacionin;

    public void initialize(){
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://maps.google.com");
        googleMapPane.getChildren().add(webView);


    }

    @FXML
    void shtoAdresen(ActionEvent event) {
        try {
            // Get the values entered in the text fields
            String rrugaValue = rruga.getText();
            String numriValue = numri.getText();
            String qytetiValue = qyteti.getText();
            String numriPostalValue = numriPostal.getText();
            String gjeresiaGjeoValue = gjeresiaGjeo.getText();
            String gjatesiaGjeoValue = gjatesiaGjeo.getText();
            String llojiVendbanimit = "";
            if(Perhershem.isSelected()){
                llojiVendbanimit = "1";
            }
            if(Perkohshem.isSelected()){
                llojiVendbanimit = "0";
            }
            Connection connection = ConnectionUtil.getConnection();

            if (connection != null) {
                // Insert the new address into the database
                String sql = "INSERT INTO adresa(Qyteti, Rruga, Numri, NumriPostal,LlojiVendbanimit, GjatesiaGjeografike, GjeresiaGjeografike) VALUES (?,?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, qytetiValue);
                statement.setString(2, rrugaValue);
                statement.setString(3, numriValue);
                statement.setString(4, numriPostalValue);
                statement.setString(5, llojiVendbanimit);
                statement.setString(6, gjatesiaGjeoValue);
                statement.setString(7, gjeresiaGjeoValue);
                statement.executeUpdate();
                System.out.println("Adresa u krijua me sukses");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting address into database: " + e.getMessage());
        }
    }



    @FXML
    void shtoLokacionin(ActionEvent event) {

    }

}
