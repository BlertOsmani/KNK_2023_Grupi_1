package AdminDashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class AdminDashboardController {

    @FXML
    private TableColumn<?, ?> adresaFshati;

    @FXML
    private TableColumn<?, ?> adresaHyrja;

    @FXML
    private TableColumn<?, ?> adresaId;

    @FXML
    private TableColumn<?, ?> adresaKomuna;

    @FXML
    private TableColumn<?, ?> adresaNumri;

    @FXML
    private TableColumn<?, ?> adresaNumriPostal;

    @FXML
    private TableColumn<?, ?> adresaObjekti;

    @FXML
    private TableColumn<?, ?> adresaQyteti;

    @FXML
    private TableColumn<?, ?> adresaRruga;

    @FXML
    private TableColumn<?, ?> adresaVendbanimi;

    @FXML
    private MenuItem close;

    @FXML
    private DatePicker ditelindja;

    @FXML
    private TextField emri;

    @FXML
    private MenuItem english;

    @FXML
    private Button filterBtn;

    @FXML
    private TextField mbiemri;

    @FXML
    private TextField nrPersonal;

    @FXML
    private MenuItem shqip;

    @FXML
    void filterAdresaTable(ActionEvent event) {

    }

}
