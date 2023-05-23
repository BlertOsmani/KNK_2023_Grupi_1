package Controllers;

import AdresatDashboard.AdresatDashboard;
import Adresa.Adresa;
import Dashboard.Dashboard;
import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Models.QytetariModel;
import Models.dto.CreateAdresaDto;
import Models.dto.CreateQytetariDto;
import QytetaretDashboard.QytetaretDashboard;
import Repositories.AdresaRepository;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import EditQytetari.EditQytetari;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
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

    @FXML
    public Pagination pagination;
    @FXML
    private AnchorPane anchorPane;

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
   void openGjejQytetarin(ActionEvent event) throws IOException {
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
    void filterQytetariTable(ActionEvent event) throws SQLException {
        String NrPersonalFilter = nrPersonal.getText();
        String EmriFilter = emri.getText();
        String MbiemriFilter = mbiemri.getText();
        String DitelindjaFilter = ditelindja.getValue() != null ? String.valueOf(ditelindja.getValue()) : "";
        Connection connection = null;
        try {
            connection = ConnectionUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CreateQytetariDto QytetariDto = new CreateQytetariDto(NrPersonalFilter, EmriFilter, "", "", MbiemriFilter, DitelindjaFilter, "", "", "", 0);
        List<QytetariModel> QytetariModelList = null;

        try {
            QytetariModelList = QytetariRepository.filterTable(connection, QytetariDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Update the table with the filtered data
        ObservableList<QytetariModel> filteredList = FXCollections.observableList(QytetariModelList);
        qytetariTable.setItems(filteredList);
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
        qytetariAdresa.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().Adresa).asObject());

        qytetariAksionet.setCellFactory(column -> new TableCell<QytetariModel, Void>() {
            private final Button edit = new Button("Update");
            private final Button delete = new Button("Delete");
            private final HBox buttonsContainer = new HBox(edit, delete);

            {
                // Define the action to be performed when the edit button is clicked
                edit.setOnAction(event -> {
                    QytetariModel model = getTableRow().getItem();
                    if (model != null) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(EditQytetari.class.getResource("EditQytetari.fxml"));
                            Pane pane = fxmlLoader.load();
                            EditQytetariController editQytetariController = fxmlLoader.getController();
                            editQytetariController.setQytetariFields(model.Id, model.NrPersonal, model.Emri, model.EmriBabait, model.EmriNenes, model.Mbiemri, model.Ditelindja, model.Email, model.NrTel, model.Gjinia, model.Adresa);
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
                        System.out.println("Button clicked for item:");
                    }
                });

                // Define the action to be performed when the delete button is clicked
                delete.setOnAction(event -> {
                    QytetariModel model = getTableRow().getItem();
                    if (model != null) {
                        int qytetariId = model.Id;
                        QytetariRepository qytetariRepository = new QytetariRepository();
                        try {
                            Connection connection = ConnectionUtil.getConnection();
                            qytetariRepository.delete(qytetariId, connection);
                            System.out.println("Qytetari deleted successfully");
                            qytetariTable.getItems().remove(model);
                        } catch (SQLException e) {
                            System.err.println("Error deleting qytetari: " + e.getMessage());
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
        anchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.F12){
                translateEN(new ActionEvent());
            }
            else if(event.getCode() == KeyCode.F11){
                translateAL(new ActionEvent());
            }
        });

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
        int itemsPerPage = 10;
        int pageCount = (qytetariObservableList.size() + itemsPerPage - 1) / itemsPerPage;
        pagination.setPageCount(pageCount);
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage, qytetariObservableList.size());
            qytetariTable.setItems(FXCollections.observableArrayList(qytetariObservableList.subList(fromIndex, toIndex)));
            return new Pane();
        });
    }

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        gjuhaAdmin.setText(translate.getString("adresat.menu.gjuha"));
        adresatBtn.setText(translate.getString("adresat.button.Adresat"));
        qytetaretBtn.setText(translate.getString("adresat.button.Qytetaret"));
        nrPersonalLabel.setText(translate.getString("qytetari.label.nrPersonal"));
        dashboardBtn.setText(translate.getString("adresat.button.dashboard"));
        emriLabel.setText(translate.getString("qytetari.label.emri"));
        mbiemriLabel.setText(translate.getString("qytetari.label.mbiemri"));
        ditelindjaLabel.setText(translate.getString("qytetari.label.ditelindja"));
        filterBtn.setText(translate.getString("adresat.button.filter"));
        qytetariNrPersonal.setText(translate.getString("qytetari.label.nrPersonal"));
        qytetariEmri.setText(translate.getString("qytetari.label.emri"));
        qytetariEmriBabait.setText(translate.getString("qytetari.label.emriBabait"));
        qytetariEmriNenes.setText(translate.getString("qytetari.label.emriNenes"));
        qytetariMbiemri.setText(translate.getString("qytetari.label.mbiemri"));
        qytetariDitelindja.setText(translate.getString("qytetari.label.ditelindja"));
        qytetariEmail.setText(translate.getString("qytetari.label.email"));
        qytetariNrTelefonit.setText(translate.getString("qytetari.label.nrTelefonit"));
        qytetariGjinia.setText(translate.getString("qytetari.label.gjinia"));
        qytetariAdresa.setText(translate.getString("qytetari.label.adresa"));
        qytetariAksionet.setText(translate.getString("adresat.aksionet"));

    }

    @FXML
    void translateAL(ActionEvent event) {
        Locale.setDefault(new Locale("al"));
        this.translate();
    }

    @FXML
    void translateEN(ActionEvent event) {
        Locale.setDefault(new Locale("en"));
        this.translate();
    }


}
