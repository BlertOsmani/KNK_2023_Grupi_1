package Repositories;

import DbConnection.ConnectionUtil;
import Models.AdresaModel;
import Models.dto.CreateAdresaDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresaRepository
{
    public void insert(CreateAdresaDto adresaDto, Connection connection) throws SQLException{
            // Insert the new address into the database
            String sql = "INSERT INTO adresa(Qyteti, Komuna, Fshati, Rruga, Objekti, Hyrja, Numri, NumriPostal,LlojiVendbanimit) VALUES (?,?,?,?,?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adresaDto.Qyteti);
            statement.setString(2, adresaDto.Komuna);
            statement.setString(3, adresaDto.Fshati);
            statement.setString(4, adresaDto.Rruga);
            statement.setString(5, adresaDto.Objekti);
            statement.setString(6, adresaDto.Hyrja);
            statement.setInt(7, adresaDto.Numri);
            statement.setInt(8, adresaDto.NumriPostal);
            statement.setString(9, adresaDto.LlojiVendbanimit);
            statement.executeUpdate();
    }
        public static List<AdresaModel> getAdresses(Connection connection) throws SQLException {
                List<AdresaModel> adresaList = new ArrayList<>();
                String sql = "SELECT * FROM adresa";
                PreparedStatement statement = connection.prepareStatement(sql);

                // Set the SQL parameters for pagination


                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                        // Retrieve the address data from the result set
                        int id = resultSet.getInt("Id");
                        String qyteti = resultSet.getString("Qyteti");
                        String komuna = resultSet.getString("Komuna");
                        String fshati = resultSet.getString("Fshati");
                        String rruga = resultSet.getString("Rruga");
                        String objekti = resultSet.getString("Objekti");
                        String hyrja = resultSet.getString("Hyrja");
                        int numri = resultSet.getInt("Numri");
                        int numriPostal = resultSet.getInt("NumriPostal");
                        String llojiVendbanimit = resultSet.getString("LlojiVendbanimit").equals("1") ? "I perhershem" : "I perkohshem";

                        // Create an instance of AdresaModel and add it to the list
                        AdresaModel adresa = new AdresaModel(id, qyteti, komuna, fshati, rruga, objekti, hyrja, numri, numriPostal, llojiVendbanimit);
                        adresaList.add(adresa);
                }

                resultSet.close();
                statement.close();

                return adresaList;
        }

        public void update(AdresaModel editAdresaModel, Connection connection) throws SQLException {
                // Update the address in the database
                String sql = "UPDATE adresa SET Qyteti = ?, Komuna = ?, Fshati = ?, Rruga = ?, Objekti = ?, Hyrja = ?, Numri = ?, NumriPostal = ?, LlojiVendbanimit = ? WHERE Id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, editAdresaModel.Qyteti);
                statement.setString(2, editAdresaModel.Komuna);
                statement.setString(3, editAdresaModel.Fshati);
                statement.setString(4, editAdresaModel.Rruga);
                statement.setString(5, editAdresaModel.Objekti);
                statement.setString(6, editAdresaModel.Hyrja);
                statement.setInt(7, editAdresaModel.Numri);
                statement.setInt(8, editAdresaModel.NumriPostal);
                statement.setString(9, editAdresaModel.LlojiVendbanimit);
                statement.setInt(10, editAdresaModel.Id);
                statement.executeUpdate();
        }

        public int getLastId(Connection connection) throws SQLException {
                String sql = "SELECT LAST_INSERT_ID() AS LastId";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                int id = 0;
                if (resultSet.next()) {
                        id = resultSet.getInt("LastId");
                }
                resultSet.close();
                statement.close();
                return id;
        }

        public void delete(int adresaId, Connection connection) throws SQLException {
                String sql = "DELETE FROM adresa WHERE Id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, adresaId);
                statement.executeUpdate();
                statement.close();
        }
        public static List<AdresaModel> filterTable(Connection connection, CreateAdresaDto model) throws SQLException {
                List<AdresaModel> adresaList = new ArrayList<>();
                StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM adresa WHERE 1=1");
                if (model.Qyteti != null && !model.Qyteti.isEmpty()) {
                        sqlBuilder.append(" AND Qyteti LIKE ?");
                }
                if (model.Komuna != null && !model.Komuna.isEmpty()) {
                        sqlBuilder.append(" AND Komuna LIKE ?");
                }
                if (model.Fshati != null && !model.Fshati.isEmpty()) {
                        sqlBuilder.append(" AND Fshati LIKE ?");
                }
                if (model.Rruga != null && !model.Rruga.isEmpty()) {
                        sqlBuilder.append(" AND Rruga LIKE ?");
                }
                if (model.Objekti != null && !model.Objekti.isEmpty()) {
                        sqlBuilder.append(" AND Objekti LIKE ?");
                }
                if (model.Hyrja != null && !model.Hyrja.isEmpty()) {
                        sqlBuilder.append(" AND Hyrja LIKE ?");
                }
                if(model.Numri != 0){
                        sqlBuilder.append(" AND Numri Like ?");
                }
                if(model.NumriPostal != 0){
                        sqlBuilder.append(" AND NumriPostal Like ?");
                }
                if (model.LlojiVendbanimit != null && !model.LlojiVendbanimit.isEmpty()) {
                        sqlBuilder.append(" AND LlojiVendbanimit LIKE ?");
                }

                PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());
                int parameterIndex = 1;
                if (model.Qyteti != null && !model.Qyteti.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Qyteti + "%");
                }
                if (model.Komuna != null && !model.Komuna.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Komuna + "%");
                }
                if (model.Fshati != null && !model.Fshati.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Fshati + "%");
                }
                if (model.Rruga != null && !model.Rruga.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Rruga + "%");
                }
                if (model.Objekti != null && !model.Objekti.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Objekti + "%");
                }
                if (model.Hyrja != null && !model.Hyrja.isEmpty()) {
                        statement.setString(parameterIndex++, "%" + model.Hyrja + "%");
                }
                if (model.Numri != 0) {
                        statement.setInt(parameterIndex++, model.Numri );
                }
                if (model.NumriPostal != 0) {
                        statement.setInt(parameterIndex++, model.NumriPostal );
                }
                if (model.LlojiVendbanimit != null && !model.LlojiVendbanimit.isEmpty()) {
                        statement.setString(parameterIndex, "%" + model.LlojiVendbanimit + "%");
                }

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                        int id = resultSet.getInt("Id");
                        String qyteti = resultSet.getString("Qyteti");
                        String komuna = resultSet.getString("Komuna");
                        String fshati = resultSet.getString("Fshati");
                        String rruga = resultSet.getString("Rruga");
                        String objekti = resultSet.getString("Objekti");
                        String hyrja = resultSet.getString("Hyrja");
                        int numri = resultSet.getInt("Numri");
                        int numriPostal = resultSet.getInt("NumriPostal");
                        String llojiVendbanimit = resultSet.getString("LlojiVendbanimit").equals("1") ? "I perhershem" : "I perkohshem";

                        AdresaModel adresa = new AdresaModel(id, qyteti, komuna, fshati, rruga, objekti, hyrja, numri, numriPostal, llojiVendbanimit);
                        adresaList.add(adresa);
                }

                resultSet.close();
                statement.close();

                return adresaList;
        }
        public int countAdresa () throws SQLException {
                Connection connection = ConnectionUtil.getConnection();
                String sql = "Select count(*) as adresaCount from adresa";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                        int count = rs.getInt("adresaCount");
                        return count;
                }
                return 0;
        }
}