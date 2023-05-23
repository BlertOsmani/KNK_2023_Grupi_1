package Controllers;

import Repositories.AdresaRepository;
import Repositories.QytetariRepository;
import Adresa.Adresa;
import AdresatDashboard.AdresatDashboard;
import Dashboard.Dashboard;
import QytetaretDashboard.QytetaretDashboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.sql.SQLException;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController {
        @FXML
        private Label adresaCount;

        @FXML
        private Button adresatBtn;

        @FXML
        private MenuItem close;

        @FXML
        private Button dashboardBtn;

        @FXML
        private MenuItem english;

        @FXML
        private Menu gjuhaAdmin;

        @FXML
        private Button qytetaretBtn;

        @FXML
        private Label qytetariCount;

        @FXML
        private MenuItem shqip;
        @FXML
        private Label numriAdresave;
        @FXML
        private Label numriQytetareve;

    @FXML
    private BarChart<String, Number> barChart;

    public void initialize() throws SQLException {
        AdresaRepository adresaRepository = new AdresaRepository();
        adresaCount.setText(String.valueOf(adresaRepository.countAdresa()));

        QytetariRepository qytetariRepository = new QytetariRepository();
        qytetariCount.setText(String.valueOf(qytetariRepository.countQytetaret()));

        // Create data for the chart
        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Nr.Total i Qytetareve", qytetariRepository.countQytetaret()));
        series.getData().add(new XYChart.Data<>("Nr.Total i Adresave", adresaRepository.countAdresa()));

        barChartData.add(series);

        // Set the data to the Bar Chart
        barChart.setData(barChartData);
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
    void openGjejQytetarin(ActionEvent event) throws IOException {
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

    public void translate(){
        Locale locale = Locale.getDefault();
        ResourceBundle translate = ResourceBundle.getBundle("Translations.content", locale);

        gjuhaAdmin.setText(translate.getString("adresat.menu.gjuha"));
        adresatBtn.setText(translate.getString("adresat.button.Adresat"));
        qytetaretBtn.setText(translate.getString("adresat.button.Qytetaret"));
        dashboardBtn.setText(translate.getString("adresat.button.dashboard"));
        numriAdresave.setText(translate.getString("dashboard.nrAdresave"));
        numriQytetareve.setText(translate.getString("dashboard.nrQytetareve"));
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
