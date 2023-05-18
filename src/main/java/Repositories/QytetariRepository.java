package Repositories;

import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Models.QytetariModel;
import Models.dto.CreateQytetariDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QytetariRepository {

    public void insert(CreateQytetariDto qytetariDto, Connection connection) throws SQLException{
            // Insert the new address into the database
                    String sql = "INSERT INTO qytetari(NrPersonal, Emri, EmriBabait, EmriNenes, Mbiemri, Ditelindja, Email, NrTelefonit, Gjinia, Adresa, Created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, qytetariDto.NrPersonal);
                    statement.setString(2, qytetariDto.Emri);
                    statement.setString(3, qytetariDto.EmriBabait);
                    statement.setString(4, qytetariDto.EmriNenes);
                    statement.setString(5, qytetariDto.Mbiemri);
                    statement.setString(6, qytetariDto.Ditelindja);
                    statement.setString(7, qytetariDto.Email);
                    statement.setString(8, qytetariDto.NrTel);
                    statement.setString(9, qytetariDto.Gjinia);
                    statement.setInt(10, qytetariDto.Adresa);
                    statement.executeUpdate();
    }

    public static boolean qytetariExists(String nrPersonal,Connection connection) throws SQLException {
            String sql = "Select * from qytetari where NrPersonal = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nrPersonal);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                    return true;
            }
            else{
                    return false;
            }
    }

    public static List<QytetariModel> getQytetari(Connection connection) throws SQLException {
        List<QytetariModel> qytetariList = new ArrayList<>();
        String sql = "Select * from qytetari";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("Id");
            String nrPersonal = resultSet.getString("NrPersonal");
            String emri = resultSet.getString("Emri");
            String emriBabait = resultSet.getString("EmriBabait");
            String emriNenes = resultSet.getString("EmriNenes");
            String mbiemri = resultSet.getString("Mbiemri");
            String ditelindja = resultSet.getString("Ditelindja");
            String email = resultSet.getString("Email");
            String nrTelefonit = resultSet.getString("NrTelefonit");
            String gjinia = resultSet.getString("Gjinia");
            int adresa = resultSet.getInt("Adresa");
            QytetariModel qytetariModel = new QytetariModel(id, nrPersonal, emri, emriBabait, emriNenes, mbiemri, ditelindja, email, nrTelefonit, gjinia, adresa);
            qytetariList.add(qytetariModel);
        }
        resultSet.close();
        statement.close();
        return qytetariList;
    }

}
