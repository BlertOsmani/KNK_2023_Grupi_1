package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class DashboardController {
    @FXML
    private BarChart<String, Number> barChart;

    public void initialize() {
        // Create data for the chart
        ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Category 1", 10));
        series.getData().add(new XYChart.Data<>("Category 2", 20));
        series.getData().add(new XYChart.Data<>("Category 3", 15));
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
