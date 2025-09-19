package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IShelterDAO;
import models.ShelterEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO implements IShelterDAO {

    @Override
    public void createTableShelters() throws PersistenceException {
        String sql = "CREATE TABLE refugios (" +
                "id INT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "responsable VARCHAR(50) NOT NULL, " +
                "capacidad INT NOT NULL, " +
                "ubicacion VARCHAR(100)" +
                ");";

        try (Connection conn = ConexionDB.getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
            System.out.println("Tabla 'Refugios' creada.");

        }catch (SQLException e) {
            System.out.println("Error al crear la tabla refugios: " + e.getMessage());

        }
    }

    @Override
    public void insertShelters() throws PersistenceException {
        int contInserts = 0;
        String sql = "INSERT INTO refugios (nombre, responsable, capacidad, ubicacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Shelter 1
            System.out.println("Insertando refugio 1...");
            pstmt.setString(1, "Refugio California");
            pstmt.setString(2, "Lucia Gómez");
            pstmt.setInt(3, 25);
            pstmt.setString(4, "Los Angeles, CA");
            pstmt.executeUpdate();
            contInserts++;

            // Producto 2
            System.out.println("Insertando refugio 2...");
            pstmt.setString(1, "Casa Ramirez");
            pstmt.setString(2, "Gustavo Cerati");
            pstmt.setInt(3, 28);
            pstmt.setString(4, "Buenos Aires, Argentina");
            pstmt.executeUpdate();
            contInserts++;

            // Producto 3
            System.out.println("Insertando refugio 3...");
            pstmt.setString(1, "Refugio La Paz");
            pstmt.setString(2, "Rocio Guadalupe Diaz");
            pstmt.setInt(3, 30);
            pstmt.setString(4, "Madrid, España");
            pstmt.executeUpdate();
            contInserts++;

        } catch (SQLException e) {
            System.out.println("Error al insertar productos: " + e.getMessage());
        }

        System.out.printf("Se insertaron %d Refugios.%n", contInserts);
    }

    @Override
    public boolean create(ShelterEntity shelterEntity) throws PersistenceException {
        String sql = "INSERT INTO refugios (nombre, responsable, capacidad, ubicacion) VALUES (?,?,?,?);";

        try (Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, shelterEntity.getName_shelter());;
            ps.setString(2, shelterEntity.getResponsible());
            ps.setInt(3, shelterEntity.getCapacity());
            ps.setString(4, shelterEntity.getLocation());
            System.out.println("El refugio se ha agregado exitosamente");
            return ps.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println("No se ha podido agregar el refugio");
            exception.printStackTrace();
            return false;
        }

    }

    @Override
    public ShelterEntity readById(int id) throws PersistenceException {
        String sql = "SELECT * FROM refugios where id = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                   ShelterEntity shelterEntity = new ShelterEntity();
                   shelterEntity.setId_shelter(rs.getInt("id"));
                   shelterEntity.setName_shelter(rs.getString("nombre"));
                   shelterEntity.setResponsible(rs.getString("responsable"));
                   shelterEntity.setCapacity(rs.getInt("capacidad"));
                   shelterEntity.setLocation(rs.getString("ubicacion"));
                    System.out.println("Refugio encontrado: " + shelterEntity.toString());
                   return shelterEntity;
                }
            }
        }catch (SQLException exception) {
            System.out.println("No se ha encontrado al refugio");
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(ShelterEntity shelterEntity) throws PersistenceException {
        String sql = "UPDATE refugios SET nombre = ?, responsable = ?, capacidad = ?, ubicacion = ? where id = ?";
        try (
            Connection con = ConexionDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, shelterEntity.getName_shelter());
            ps.setString(2, shelterEntity.getResponsible());;
            ps.setInt(3, shelterEntity.getCapacity());
            ps.setString(4, shelterEntity.getLocation());
            ps.setInt(5, shelterEntity.getId_shelter());
            System.out.println("Refugio actualizado con exito");
            return ps.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println("No se ha podido actualizar el refugio");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) throws PersistenceException {
        String sql = "DELETE FROM refugios WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,id);

            int deletedRows = ps.executeUpdate();
            if(deletedRows > 0){
                System.out.println("El refugio se ha eliminado con exito");
                return true;
            }else{
                System.out.println("No se ha podido eliminar el refugio");
                return false;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ShelterEntity> readAll() throws PersistenceException {
        String sql = "SELECT * FROM refugios";
        List<ShelterEntity> shelters = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ShelterEntity shelterEntity = new ShelterEntity();
                shelterEntity.setId_shelter(rs.getInt("id"));
                shelterEntity.setName_shelter(rs.getString("nombre"));
                shelterEntity.setResponsible(rs.getString("responsable"));
                shelterEntity.setCapacity(rs.getInt("capacidad"));
                shelterEntity.setLocation(rs.getString("ubicacion"));
                shelters.add(shelterEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        for(ShelterEntity shelterEntity : shelters){
            System.out.println(shelterEntity);
        }
        return shelters;
    }

    @Override
    public void clieanUpTable() throws PersistenceException {
        String sql = "DELETE FROM refugios";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement()) {
            int filas = stmt.executeUpdate(sql);
            System.out.println("Se eliminaron " + filas + " registros de la tabla refugios.");

        } catch (SQLException e) {
            System.out.println("Error al limpiar la tabla refugios: " + e.getMessage());

        }
    }

}
