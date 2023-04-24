module com.example.knk_2023_grupi_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    opens com.example.knk_2023_grupi_1 to javafx.fxml;
    exports com.example.knk_2023_grupi_1;
    exports Qytetari;
    exports Adresa to javafx.graphics;
    opens Adresa to javafx.fxml;
    opens Qytetari to javafx.fxml;
}