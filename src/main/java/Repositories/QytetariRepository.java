package Repositories;

import DbConnection.ConnectionUtil;
import Models.QytetariModel;
import Models.dto.CreateQytetariDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
