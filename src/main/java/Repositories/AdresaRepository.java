package Repositories;

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
        public static List<AdresaModel> filterTable(Connection connection,CreateAdresaDto model) throws SQLException {
                List<AdresaModel> adresaList = new ArrayList<>();
                String sql = "SELECT * FROM adresa WHERE Qyteti like ? OR Komuna like ? OR Fshati like ? Or Rruga like ? " +
                        "OR Objekti like ? OR Hyrja like ? OR Numri like ? OR NumriPostal like ? OR LlojiVendbanimit like ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1,"%" + model.Qyteti + "%");
                statement.setString(2,"%" + model.Komuna + "%");
                statement.setString(3,"%" + model.Fshati + "%");
                statement.setString(4,"%" + model.Rruga + "%");
                statement.setString(5,"%" + model.Objekti + "%");
                statement.setString(6,"%" + model.Hyrja + "%");
                statement.setString(7,"%" + model.Numri + "%");
                statement.setString(8,"%" + model.NumriPostal + "%");
                statement.setString(9,"%" + model.LlojiVendbanimit + "%");

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

}
