package Controllers;

import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Models.UserModel;
import Repositories.AdresaRepository;
import Repositories.UserRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

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

    @FXML
    public MenuItem close;

    @FXML
    public DatePicker ditelindja;

    @FXML
    public TextField emri;

    @FXML
    public MenuItem english;

    @FXML
    public Button filterBtn;

    @FXML
    public TextField mbiemri;

    @FXML
    public TextField nrPersonal;

    @FXML
    public MenuItem shqip;

    @FXML
    public Label emriLabel;
    @FXML
    public Button gjejQytetaret;
    @FXML
    public Menu gjuhaAdmin;
    @FXML
    public Button kerkoVendbanimin;
    @FXML
    public Label mbiemriLabel;
    @FXML
    public Label nrPersonalLabel;
    @FXML
    public Label dataLindjes;



    @FXML
    void filterAdresaTable(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            private final Button button = new Button("Click Me");

            {
                // Define the action to be performed when the button is clicked
                button.setOnAction(event -> {
                    AdresaModel model = getTableRow().getItem();
                    if (model != null) {
                        // Perform the desired action using the AdresaModel instance
                        // For example, you can access its properties like model.getId(), model.getName(), etc.
                        System.out.println("Button clicked for item:");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
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
            adresaModelList = AdresaRepository.getAdresses(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<AdresaModel> adresaObservableList = FXCollections.observableList(adresaModelList);
        adresaTable.setItems(adresaObservableList);
    }

    public void translate() {
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        gjuhaAdmin.setText(translate.getString("admin.menu.gjuha"));
        kerkoVendbanimin.setText(translate.getString("admin.button.kerkoVendbanimin"));
        gjejQytetaret.setText(translate.getString("admin.button.gjejQytetaret"));
        nrPersonalLabel.setText(translate.getString("admin.label.nrPersonal"));
        emriLabel.setText(translate.getString("admin.label.emri"));
        mbiemriLabel.setText(translate.getString("admin.label.mbiemri"));
        dataLindjes.setText(translate.getString("admin.label.ditelindja"));
    }

    public void translateEN(ActionEvent event){
        Locale.setDefault(new Locale("en"));
        this.translate();
    }

    public void translateAL(ActionEvent event){
        Locale.setDefault(new Locale("al"));
        this.translate();
    }

}
