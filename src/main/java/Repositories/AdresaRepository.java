package Repositories;

import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Qytetari.Qytetari;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Qytetari.QytetariController;

public class AdresaRepository
{
    public void insert(AdresaModel adresaModel, Connection connection) throws SQLException{
            // Insert the new address into the database
            String sql = "INSERT INTO adresa(Qyteti, Komuna, Fshati, Rruga, Objekti, Hyrja, Numri, NumriPostal,LlojiVendbanimit) VALUES (?,?,?,?,?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adresaModel.Qyteti);
            statement.setString(2, adresaModel.Komuna);
            statement.setString(3, adresaModel.Fshati);
            statement.setString(4, adresaModel.Rruga);
            statement.setString(5, adresaModel.Objekti);
            statement.setString(6, adresaModel.Hyrja);
            statement.setInt(7, adresaModel.Numri);
            statement.setInt(8, adresaModel.NumriPostal);
            statement.setString(9, adresaModel.LlojiVendbanimit);
            statement.executeUpdate();
    }
}
