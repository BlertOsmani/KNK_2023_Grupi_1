package Controllers;

import Adresa.Adresa;
import AdresatDashboard.AdresatDashboard;
import Dashboard.Dashboard;
import DbConnection.ConnectionUtil;
import Models.QytetariModel;
import Models.dto.CreateAdresaDto;
import QytetaretDashboard.QytetaretDashboard;
import Models.AdresaModel;
import Qytetari.Qytetari;
import Repositories.AdresaRepository;
import Repositories.QytetariRepository;
import Repositories.QytetetRepository;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import EditAdresa.EditAdresa;

public class AdresatDashboardController implements Initializable {

    @FXML
    public TableView<AdresaModel> adresaTable;
    @FXML
    public TableColumn<AdresaModel, String> adresaFshati;

    @FXML
    public TableColumn<AdresaModel, String> adresaHyrja;

    @FXML
    public TableColumn<AdresaModel, String> adresaKomuna;

    @FXML
    public TableColumn<AdresaModel, Integer> adresaNumri;

    @FXML
    public TableColumn<AdresaModel, Integer> adresaNumriPostal;

    @FXML
    public TableColumn<AdresaModel, String> adresaObjekti;

    @FXML
    public TableColumn<AdresaModel, String> adresaQyteti;

    @FXML
    public TableColumn<AdresaModel, String> adresaRruga;

    @FXML
    public TableColumn<AdresaModel, String> adresaVendbanimi;

    @FXML
    public TableColumn<AdresaModel, Integer> adresaId;
    @FXML
    public TableColumn<AdresaModel, Void> adresaAksionet;

    private ObservableList<AdresaModel> masterData = FXCollections.observableArrayList();


    @FXML
    public MenuItem close;

    @FXML
    public MenuItem english;

    @FXML
    public Button filterBtn;

    @FXML
    public MenuItem shqip;

    @FXML
    public Menu gjuhaAdmin;
    @FXML
    public Button qytetaretBtn;

    @FXML
    public Button dashboardBtn;

    @FXML
    public Button adresatBtn;

    @FXML
    public Button shtoAdresenBtn;

    @FXML
    public TextField fshati;

    @FXML
    public Label fshatiLabel;

    @FXML
    public TextField hyrja;

    @FXML
    public Label hyrjaLabel;

    @FXML
    public TextField komuna;

    @FXML
    public Label komunaLabel;

    @FXML
    public ChoiceBox<String> llojiVendbanimit;

    @FXML
    public Label llojiVendbanimitLabel;

    @FXML
    public Label nrPostalLabel;

    @FXML
    public TextField numri;

    @FXML
    public Label numriLabel;

    @FXML
    public TextField numriPostal;

    @FXML
    public TextField objekti;

    @FXML
    public Label objektiLabel;

    @FXML
    private ChoiceBox<String> qytetiChoiceBox;

    @FXML
    public Label qytetiLabel;

    @FXML
    public TextField rruga;

    @FXML
    public Label rrugaLabel;

    @FXML
    public Pagination pagination;

    @FXML
    public Button shtoQytetarin;

    @FXML
    public AnchorPane anchorPane;

    @FXML
    private TableColumn<QytetariModel, Integer> qytetariAdresa;

    @FXML
    private TableColumn<QytetariModel, String> qytetariDitelindja;

    @FXML
    private TableColumn<QytetariModel, String> qytetariEmail;

    @FXML
    private TableColumn<QytetariModel, String> qytetariEmri;

    @FXML
    private TableColumn<QytetariModel, String> qytetariEmriBabait;

    @FXML
    private TableColumn<QytetariModel, String> qytetariEmriNenes;

    @FXML
    private TableColumn<QytetariModel, String> qytetariGjinia;

    @FXML
    private TableColumn<QytetariModel, Integer> qytetariId;

    @FXML
    private TableColumn<QytetariModel, String> qytetariMbiemri;

    @FXML
    private TableColumn<QytetariModel, String> qytetariNrPersonal;

    @FXML
    private TableColumn<QytetariModel, String> qytetariNrTelefonit;

    @FXML
    private TableView<QytetariModel> qytetariTable;

    @FXML
    private Pagination pagination1;

    @FXML
    void openShtoQytetarin(ActionEvent event) throws IOException{
        AdresaModel model = adresaTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Qytetari.class.getResource("Qytetari.fxml"));
            Pane pane = fxmlLoader.load();
            QytetariController qytetariController = fxmlLoader.getController();
            qytetariController.setAddressInfo(model.Id,model.Qyteti,model.Komuna,model.Fshati,model.Rruga,model.Objekti,model.Hyrja,model.Numri,model.NumriPostal);
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
    void openShtoAdresen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Adresa.class.getResource("Adresa.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.F12){
                translateEN(new ActionEvent());
            }
            else if(event.getCode() == KeyCode.F11){
                translateAL(new ActionEvent());
            }
        });


        ObservableList<String> Items = FXCollections.observableArrayList("I perhershem","I perkohshem");
        llojiVendbanimit.setItems(Items);
        adresaRruga.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Rruga));
        adresaVendbanimi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().LlojiVendbanimit));
        adresaFshati.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Fshati));
        adresaHyrja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Hyrja));
        adresaNumri.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Numri).asObject());
        adresaKomuna.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Komuna));
        adresaNumriPostal.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().NumriPostal).asObject());
        adresaObjekti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Objekti));
        adresaQyteti.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Qyteti));
        adresaId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Id).asObject());

        adresaAksionet.setCellFactory(column -> new TableCell<AdresaModel, Void>() {
            private final Button edit = new Button("Update");
            private final Button delete = new Button("Delete");
            private final HBox buttonsContainer = new HBox(edit, delete);

            {
                // Define the action to be performed when the edit button is clicked
                edit.setOnAction(event -> {
                    AdresaModel model = getTableRow().getItem();
                    if (model != null) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(EditAdresa.class.getResource("EditAdresa.fxml"));
                            Pane pane = fxmlLoader.load();
                            EditAdresaController editAdresaController = fxmlLoader.getController();
                            editAdresaController.setAdressFields(model.Id, model.Qyteti, model.Komuna, model.Fshati, model.Rruga, model.Objekti, model.Hyrja, model.Numri, model.NumriPostal, model.LlojiVendbanimit);
                            Scene scene = new Scene(pane);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            System.err.println("Error loading FXML file: " + e.getMessage());
                        }
                        System.out.println("Button clicked for item:");
                    }
                });

                // Define the action to be performed when the delete button is clicked
                delete.setOnAction(event -> {
                    AdresaModel model = getTableRow().getItem();
                    if (model != null) {
                        int adresaId = model.Id;
                        AdresaRepository adresaRepository = new AdresaRepository();
                        try {
                            Connection connection = ConnectionUtil.getConnection();
                            adresaRepository.delete(adresaId, connection);
                            System.out.println("Address deleted successfully");
                            adresaTable.getItems().remove(model);
                        } catch (SQLException e) {
                            System.err.println("Error deleting address: " + e.getMessage());
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {

                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsContainer);
                }
            }
        });
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<AdresaModel> adresaModelList = null;
        try {

            adresaModelList = AdresaRepository.getAdresses(connection, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<AdresaModel> adresaObservableList = FXCollections.observableList(adresaModelList);
        int itemsPerPage = 10;
        int pageCount = (adresaObservableList.size() + itemsPerPage - 1) / itemsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex->{
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage,adresaObservableList.size());
            adresaTable.setItems(FXCollections.observableArrayList(adresaObservableList.subList(fromIndex,toIndex)));
            return new Pane();
        });
        ObservableList<String> qytetetList = null;
        try {
            qytetetList = FXCollections.observableList(QytetetRepository.getQytetet());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        qytetiChoiceBox.setItems(qytetetList);

    }

    @FXML
    void shfaqQytetaret(ActionEvent event){
        AdresaModel adresaModel = adresaTable.getSelectionModel().getSelectedItem();
        qytetariEmri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Emri));
        qytetariMbiemri.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Mbiemri));
        qytetariEmriBabait.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().EmriBabait));
        qytetariEmriNenes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().EmriNenes));
        qytetariNrPersonal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().NrPersonal));
        qytetariDitelindja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Ditelindja));
        qytetariNrTelefonit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().NrTel));
        qytetariAdresa.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Adresa).asObject());
        qytetariGjinia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Gjinia));
        qytetariId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Id).asObject());
        qytetariEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Email));
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<QytetariModel> qytetariModelList = null;
        try {
            qytetariModelList = QytetariRepository.getQytetari(connection, adresaModel.Id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<QytetariModel> qytetariObservableList = FXCollections.observableList(qytetariModelList);
        int itemsPerPage = 10;
        int pageCount = (qytetariObservableList.size() + itemsPerPage - 1) / itemsPerPage;
        pagination1.setPageCount(pageCount);
        pagination1.setPageFactory(pageIndex->{
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage,qytetariObservableList.size());
            qytetariTable.setItems(FXCollections.observableArrayList(qytetariObservableList.subList(fromIndex,toIndex)));
            qytetariTable.setVisible(true);
            pagination1.setVisible(true);
            return new Pane();
        });
    }
    @FXML
    private void filterAdresaTable(ActionEvent event) throws SQLException {
        // Get the filter values from the text fields
        String qytetiFilter = qytetiChoiceBox.getValue();
        String komunaFilter = komuna.getText();
        String fshatiFilter = fshati.getText();
        String rrugaFilter = rruga.getText();
        String objektiFilter = objekti.getText();
        String hyrjaFilter = hyrja.getText();
        int numriFilter = 0;
        String numriText = numri.getText();
        if (!numriText.isEmpty()) {
            numriFilter = Integer.parseInt(numriText);
        }

        int numriPostalFilter = 0;
        String numriPostalText = numriPostal.getText();
        if (!numriPostalText.isEmpty()) {
            numriPostalFilter = Integer.parseInt(numriPostalText);
        }

        String llojiVendbanimitFilter = llojiVendbanimit.getValue();
        if (llojiVendbanimitFilter != null) {
            if (llojiVendbanimitFilter.equals("I perhershem")) {
                llojiVendbanimitFilter = "1";
            } else if (llojiVendbanimitFilter.equals("I perkohshem")) {
                llojiVendbanimitFilter = "0";
            } else {
                llojiVendbanimitFilter = ""; // or any other default value you want to set
            }
        }
        else{
            llojiVendbanimitFilter = "";
        }

        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CreateAdresaDto adresaDto = new CreateAdresaDto(qytetiFilter, komunaFilter, fshatiFilter, rrugaFilter, objektiFilter, hyrjaFilter, numriFilter, numriPostalFilter, llojiVendbanimitFilter);
        List<AdresaModel> adresaModelList = null;
        try {
            adresaModelList = AdresaRepository.filterTable(connection, adresaDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Update the table with the filtered data
        ObservableList<AdresaModel> filteredList = FXCollections.observableList(adresaModelList);
        adresaTable.setItems(filteredList);
    }

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        gjuhaAdmin.setText(translate.getString("adresat.menu.gjuha"));
        adresatBtn.setText(translate.getString("adresat.button.Adresat"));
        qytetaretBtn.setText(translate.getString("adresat.button.Qytetaret"));
        shtoAdresenBtn.setText(translate.getString("adresat.button.shtoAdresen"));
        dashboardBtn.setText(translate.getString("adresat.button.dashboard"));
        qytetiLabel.setText(translate.getString("adresat.label.qyteti"));
        komunaLabel.setText(translate.getString("adresat.label.komuna"));
        fshatiLabel.setText(translate.getString("adresat.label.fshati"));
        objektiLabel.setText(translate.getString("adresat.label.objekti"));
        rrugaLabel.setText(translate.getString("adresat.label.rruga"));
        hyrjaLabel.setText(translate.getString("adresat.label.hyrja"));
        numriLabel.setText(translate.getString("adresat.label.numri"));
        nrPostalLabel.setText(translate.getString("adresat.label.numriPostal"));
        llojiVendbanimitLabel.setText(translate.getString("adresat.label.llojiVendbanimit"));
        filterBtn.setText(translate.getString("adresat.button.filter"));
        adresaQyteti.setText(translate.getString("adresa.label.qyteti"));
        adresaKomuna.setText(translate.getString("adresa.label.komuna"));
        adresaFshati.setText(translate.getString("adresa.label.fshati"));
        adresaRruga.setText(translate.getString("adresa.label.rruga"));
        adresaObjekti.setText(translate.getString("adresa.label.objekti"));
        adresaHyrja.setText(translate.getString("adresa.label.hyrja"));
        adresaNumri.setText(translate.getString("adresa.numri"));
        adresaNumriPostal.setText(translate.getString("adresa.label.numriPostal"));
        adresaVendbanimi.setText(translate.getString("adresat.label.llojiVendbanimit"));
        adresaAksionet.setText(translate.getString("adresat.aksionet"));
    }

    @FXML
    public void translateEN(ActionEvent event){
        Locale.setDefault(new Locale("en"));
        this.translate();
    }

    @FXML
    public void translateAL(ActionEvent event){
        Locale.setDefault(new Locale("al"));
        this.translate();
    }

}
