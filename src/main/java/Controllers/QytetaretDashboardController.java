package Controllers;

import AdresatDashboard.AdresatDashboard;
import Adresa.Adresa;
import DbConnection.ConnectionUtil;
import Models.QytetariModel;
import Repositories.QytetariRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class QytetaretDashboardController implements Initializable {
    @FXML
    public TableColumn<QytetariModel, Integer> qytetariAdresa;

    @FXML
    public TableColumn<QytetariModel, String> qytetariDitelindja;

    @FXML
    public TableColumn<QytetariModel, String> qytetariEmail;

    @FXML
    public TableColumn<QytetariModel, String> qytetariEmri;

    @FXML
    public TableColumn<QytetariModel, String> qytetariEmriBabait;

    @FXML
    public TableColumn<QytetariModel, String> qytetariEmriNenes;

    @FXML
    public TableColumn<QytetariModel, String> qytetariGjinia;

    @FXML
    public TableColumn<QytetariModel, Integer> qytetariId;

    @FXML
    public TableColumn<QytetariModel, String> qytetariMbiemri;

    @FXML
    public TableColumn<QytetariModel, String> qytetariNrPersonal;

    @FXML
    public TableColumn<QytetariModel, String> qytetariNrTelefonit;

    @FXML
    public TableView<QytetariModel> qytetariTable;

    @FXML
    public TableColumn<QytetariModel, Void> qytetariAksionet;
    @FXML
    private Button adresatBtn;

    @FXML
    private MenuItem close;

    @FXML
    private Button dashboardBtn;

    @FXML
    private DatePicker ditelindja;

    @FXML
    private Label ditelindjaLabel;

    @FXML
    private TextField emri;

    @FXML
    private Label emriLabel;

    @FXML
    private MenuItem english;

    @FXML
    private Button filterBtn;

    @FXML
    private Menu gjuhaAdmin;

    @FXML
    private TextField mbiemri;

    @FXML
    private Label mbiemriLabel;

    @FXML
    private TextField nrPersonal;

    @FXML
    private Label nrPersonalLabel;

    @FXML
    private Button qytetaretBtn;

    @FXML
    private MenuItem shqip;

    @FXML
    private Button shtoQytetarinBtn;

   /* @FXML
    void filterQytetariTable(ActionEvent event) {

    }
   @FXML
    void translateAL(ActionEvent event) {

    }

    @FXML
    void translateEN(ActionEvent event) {

    }*/

    @FXML
    void openAdresatDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdresatDashboard.class.getResource("AdresatDashboard.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Adresa.class.getResource("Adresa.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qytetariEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Emri));
        qytetariEmriBabait.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().EmriBabait));
        qytetariEmriNenes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().EmriNenes));
        qytetariMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Mbiemri));
        qytetariGjinia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Gjinia));
        qytetariEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Email));
        qytetariNrPersonal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().NrPersonal));
        qytetariNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().NrTel));
        qytetariDitelindja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Ditelindja));
        qytetariId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Id).asObject());
        qytetariAdresa.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().Adresa).asObject());
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<QytetariModel> qytetariModelList = null;
        try {
            qytetariModelList = QytetariRepository.getQytetari(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<QytetariModel> qytetariObservableList = FXCollections.observableList(qytetariModelList);
        qytetariTable.setItems(qytetariObservableList);
    }

    @FXML
    void translateAL(ActionEvent event) {

    }

    @FXML
    void translateEN(ActionEvent event) {

    }

    @FXML
    void filterQytetariTable(ActionEvent event){

    }
}
