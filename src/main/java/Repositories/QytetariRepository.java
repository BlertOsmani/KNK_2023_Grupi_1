package Repositories;

import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Models.QytetariModel;
import Models.dto.CreateAdresaDto;
import Models.dto.CreateQytetariDto;

import java.sql.*;
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

    public static boolean qytetariExists(String nrPersonal, int adresa,Connection connection) throws SQLException {
            String sql = "Select * from qytetari where NrPersonal = ? and Adresa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nrPersonal);
            statement.setInt(2, adresa);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                    return true;
            }
            else{
                    return false;
            }
    }

    public static List<QytetariModel> getQytetari(Connection connection, int Adresa) throws SQLException {
        if(Adresa == 0) {
            List<QytetariModel> qytetariList = new ArrayList<>();
            String sql = "Select * from qytetari";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
        else{
            List<QytetariModel> qytetariList = new ArrayList<>();
            String sql = "Select * from qytetari where Adresa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Adresa);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
    public static List<QytetariModel> filterTable(Connection connection, CreateQytetariDto model) throws SQLException {
        List<QytetariModel> QytetariList = new ArrayList<>();
           StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM qytetari WHERE 1=1");
           if (model.NrPersonal != null && !model.NrPersonal.isEmpty()) {
               sqlBuilder.append(" AND NrPersonal LIKE ?");
           }
           if (model.Emri != null && !model.Emri.isEmpty()) {
               sqlBuilder.append(" AND Emri LIKE ?");
           }
           if (model.Mbiemri != null && !model.Mbiemri.isEmpty()) {
               sqlBuilder.append(" AND Mbiemri LIKE ?");
           }
           if (model.Ditelindja != null && !model.Ditelindja.isEmpty()) {
               sqlBuilder.append(" AND Ditelindja LIKE ?");
           }

           PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
           int parameterIndex = 1;
           if (model.NrPersonal != null && !model.NrPersonal.isEmpty()) {
               statement.setString(parameterIndex++, "%" + model.NrPersonal + "%");
           }
           if (model.Emri != null && !model.Emri.isEmpty()) {
               statement.setString(parameterIndex++, "%" + model.Emri + "%");
           }
           if (model.Mbiemri != null && !model.Mbiemri.isEmpty()) {
               statement.setString(parameterIndex++, "%" + model.Mbiemri + "%");
           }
           if (model.Ditelindja != null && !model.Ditelindja.isEmpty()) {
               statement.setString(parameterIndex, "%" + model.Ditelindja + "%");
           }


           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()) {
               int id = resultSet.getInt("Id");
               String NrPersonal = resultSet.getString("NrPersonal");
               String Emri = resultSet.getString("Emri");
               String EmriBabait = resultSet.getString("EmriBabait");
               String EmriNenes = resultSet.getString("EmriNenes");
               String Mbiemri = resultSet.getString("Mbiemri");
               String Ditelindja = resultSet.getString("Ditelindja");
               String Email = resultSet.getString("Email");
               String NrTel = resultSet.getString("NrTelefonit");
               String Gjinia = resultSet.getString("Gjinia");
               int Adresa = resultSet.getInt("Adresa");

               QytetariModel qytetari = new QytetariModel(id, NrPersonal, Emri, EmriBabait, EmriNenes, Mbiemri, Ditelindja, Email, NrTel, Gjinia, Adresa);
               QytetariList.add(qytetari);
           }

           resultSet.close();
           statement.close();
           return QytetariList;
    }

    public void update(QytetariModel editQytetariModel, Connection connection) throws SQLException{
        String sql = "UPDATE qytetari SET NrPersonal = ?, Emri = ?, EmriBabait = ?, EmriNenes = ?, Mbiemri = ?, Ditelindja = ?, Email = ?, NrTelefonit = ?, Gjinia = ?, Adresa = ? WHERE Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, editQytetariModel.NrPersonal);
        statement.setString(2, editQytetariModel.Emri);
        statement.setString(3, editQytetariModel.EmriBabait);
        statement.setString(4, editQytetariModel.EmriNenes);
        statement.setString(5, editQytetariModel.Mbiemri);
        statement.setString(6, editQytetariModel.Ditelindja);
        statement.setString(7, editQytetariModel.Email);
        statement.setString(8, editQytetariModel.NrTel);
        statement.setString(9, editQytetariModel.Gjinia);
        statement.setInt(10, editQytetariModel.Adresa);
        statement.setInt(11, editQytetariModel.Id);
        statement.executeUpdate();
    }

    public void delete(int idQytetari, Connection connection) throws SQLException {
        String sql = "DELETE FROM qytetari WHERE Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idQytetari);
        statement.executeUpdate();
        statement.close();
    }

    public int countQytetaret() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select count(*) as qytetariCount from qytetari";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int count = rs.getInt("qytetariCount");
            return count;
        }
        return 0;
    }
    public int countBanoretNgaFshati() throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select count(*) as BanoretFshatiCount from  qytetari q join adresa a on a.Id = q.Adresa where a.Fshati <> ''";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int count = rs.getInt("BanoretFshatiCount");
            return count;
        }
        return 0;

    }
    public int countBanoretNgaQyteti() throws SQLException{
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select count(*) as BanoriNgaQytetiCount from  qytetari q join adresa a on a.Id = q.Adresa where a.Fshati is NULL or a.Fshati = '' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int count = rs.getInt("BanoriNgaQytetiCount");
            return count;
        }
        return 0;
    }

    public int countGjinia(String gjinia) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "Select count(*) as GjiniaCount from qytetari where Gjinia = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,gjinia);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int count = resultSet.getInt("GjiniaCount");
            return count;
        }
        return 0;
    }
}
