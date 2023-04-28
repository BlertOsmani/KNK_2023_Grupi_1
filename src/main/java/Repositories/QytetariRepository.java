package Repositories;

import DbConnection.ConnectionUtil;
import Models.QytetariModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QytetariRepository {

    public void insert(QytetariModel qytetariModel, Connection connection) throws SQLException{
            // Insert the new address into the database
            String sql = "INSERT INTO qytetari(NrPersonal, Emri, EmriBabait, EmriNenes, Mbiemri, Ditelindja, Email, NrTelefonit, Gjinia, Adresa, Created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, qytetariModel.NrPersonal);
            statement.setString(2, qytetariModel.Emri);
            statement.setString(3, qytetariModel.EmriBabait);
            statement.setString(4, qytetariModel.EmriNenes);
            statement.setString(5, qytetariModel.Mbiemri);
            statement.setString(6, qytetariModel.Ditelindja);
            statement.setString(7, qytetariModel.Email);
            statement.setString(8, qytetariModel.NrTel);
            statement.setString(9, qytetariModel.Gjinia);
            statement.setInt(10, qytetariModel.Adresa);
            statement.executeUpdate();
    }
}
