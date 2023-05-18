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
    exports AdminDashboard;
    opens AdminDashboard to javafx.fxml;
    opens Controllers to javafx.fxml;
    exports Controllers;
    exports EditAdresa;
    opens EditAdresa to javafx.fxml;
}