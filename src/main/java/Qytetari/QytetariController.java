package Qytetari;

import DbConnection.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class QytetariController {

    @FXML
    public TextField Adresa;

    @FXML
    public DatePicker Ditelindja;

    @FXML
    public TextField Email;

    @FXML
    public TextField Emri;

    @FXML
    public TextField EmriBabait;

    @FXML
    public TextField EmriNenes;

    @FXML
    public RadioButton Femer;

    @FXML
    public RadioButton Mashkull;

    @FXML
    public TextField Mbiemri;

    @FXML
    public TextField NrPersonal;

    @FXML
    public TextField NrTel;

    @FXML
    public Button Ruaj;

    @FXML
    public Button back;
    @FXML
    public Label errorNrPersonal;

    public void initialize(){
        // create a TextField
        TextField phoneNumberTextField = new TextField();

// create a TextFormatter with the phone number mask
        String phoneRegex = "(\\d{0,3})(\\d{0,2})(\\d{0,3})(\\d{0,3})"; // mask for phone number
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String newText = c.getControlNewText();
            if (newText.matches(phoneRegex)) {
                // remove any existing formatting characters
                newText = newText.replaceAll("[^\\d]", "");

                // apply the phone number format
                StringBuilder formattedText = new StringBuilder("+");
                int length = newText.length();
                for (int i = 0; i < length; i++) {
                    char ch = newText.charAt(i);
                    if (i == 3 || i == 5 || i == 8) {
                        formattedText.append(" ");
                    }
                    formattedText.append(ch);
                }

                // update the change object with the formatted text
                c.setText(formattedText.toString());
                c.setRange(0, length);
                c.setCaretPosition(formattedText.length());
                c.setAnchor(formattedText.length());

                return c;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(filter);

// set the TextFormatter for the TextField
        NrTel.setTextFormatter(formatter);

    }

    @FXML
    void ruaj(ActionEvent event) {

        try {
            // Get the values entered in the text fields
            String nrPersonal = NrPersonal.getText();
            String emri = Emri.getText();
            String emriBabait = EmriBabait.getText();
            String emriNenes = EmriNenes.getText();
            String mbiemri = Mbiemri.getText();
            LocalDate ditelindja = Ditelindja.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
            String ditelindjaStr = ditelindja.format(formatter);

            String email = Email.getText();
            String nrTel = NrTel.getText();
            String gjinia = "";
            String adresa = Adresa.getText();
            if(Femer.isSelected()){
                gjinia = "Femer";
            }
            if(Mashkull.isSelected()){
                gjinia = "Mashkull";
            }
            if(nrPersonal.length() < 10 || nrPersonal.length() > 10){
                errorNrPersonal.setVisible(true);
                return;
            }
            Connection connection = ConnectionUtil.getConnection();

            if (connection != null) {
                // Insert the new address into the database
                String sql = "INSERT INTO qytetari(NrPersonal, Emri, EmriBabait, EmriNenes, Mbiemri, Ditelindja, Email, NrTelefonit, Gjinia, Adresa, Created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nrPersonal);
                statement.setString(2, emri);
                statement.setString(3, emriBabait);
                statement.setString(4, emriNenes);
                statement.setString(5, mbiemri);
                statement.setString(6, ditelindjaStr);
                statement.setString(7, email);
                statement.setString(8, nrTel);
                statement.setString(9, gjinia);
                statement.setString(10, adresa);
                statement.executeUpdate();
                System.out.println("Qytetari u krijua me sukses");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.err.println("Error inserting address into database: " + e.getMessage());
        }
    }

}
