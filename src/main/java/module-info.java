module com.example.knk_2023_grupi_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.knk_2023_grupi_1 to javafx.fxml;
    exports com.example.knk_2023_grupi_1;
    exports Qytetari;
    exports Adresa to javafx.graphics;
    opens Adresa to javafx.fxml;
    opens Qytetari to javafx.fxml;
    exports Login;
    opens Login to javafx.fxml;
    exports Signup;
    opens Signup to javafx.fxml;
    exports AdresatDashboard;
    opens AdresatDashboard to javafx.fxml;
    opens Controllers to javafx.fxml;
    exports Controllers;
    exports QytetaretDashboard;
    opens QytetaretDashboard to javafx.fxml;
    exports Models;
    exports EditAdresa;
    opens EditAdresa to javafx.fxml;
    exports EditQytetari;
    opens EditQytetari to javafx.fxml;
    exports Dashboard;
    opens Dashboard to javafx.fxml;
    exports Help;
    opens Help to javafx.fxml;
}