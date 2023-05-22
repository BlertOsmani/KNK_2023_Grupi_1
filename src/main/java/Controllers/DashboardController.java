package Controllers;


import Repositories.AdresaRepository;
import Repositories.QytetariRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.sql.SQLException;

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
    void openGjejQytetarin(ActionEvent event) {

    }

    @FXML
    void translateAL(ActionEvent event) {

    }

    @FXML
    void translateEN(ActionEvent event) {

    }

}
