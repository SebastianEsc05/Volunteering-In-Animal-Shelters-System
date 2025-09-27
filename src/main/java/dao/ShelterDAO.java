package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IShelterDAO;
import models.AnimalEntity;
import models.ShelterEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO implements IShelterDAO {

    @Override
    public void insertShelters() throws PersistenceException {
        if (isNotEmpty()) {
            System.out.println("Error: La tabla 'refugios' ya tiene datos. No se insertarán datos de ejemplo.\n");
            return;

        }
        int contInserts = 0;
        String sql = "INSERT INTO refugios (nombre, responsable, capacidad, ubicacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Insertando refugio 1...");
            pstmt.setString(1, "Refugio California");
            pstmt.setString(2, "Lucia Gómez");
            pstmt.setInt(3, 25);
            pstmt.setString(4, "Los Angeles, CA");
            pstmt.executeUpdate();
            contInserts++;

            System.out.println("Insertando refugio 2...");
            pstmt.setString(1, "Casa Ramirez");
            pstmt.setString(2, "Gustavo Cerati");
            pstmt.setInt(3, 28);
            pstmt.setString(4, "Buenos Aires, Argentina");
            pstmt.executeUpdate();
            contInserts++;

            System.out.println("Insertando refugio 3...");
            pstmt.setString(1, "Refugio La Paz");
            pstmt.setString(2, "Rocio Guadalupe Diaz");
            pstmt.setInt(3, 30);
            pstmt.setString(4, "Madrid, España");
            pstmt.executeUpdate();
            contInserts++;

        } catch (SQLException e) {
            System.out.println("Error al insertar refugios: " + e.getMessage());
        }

        System.out.printf("Se insertaron %d refugios.%n\n", contInserts);
    }

    @Override
    public boolean create(ShelterEntity shelterEntity) throws PersistenceException {
        String sql = "INSERT INTO refugios (nombre, responsable, capacidad, ubicacion) VALUES (?,?,?,?);";

        try (Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, shelterEntity.getNameShelter());;
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
                   shelterEntity.setIdShelter(rs.getInt("id"));
                   shelterEntity.setNameShelter(rs.getString("nombre"));
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
            ps.setString(1, shelterEntity.getNameShelter());
            ps.setString(2, shelterEntity.getResponsible());;
            ps.setInt(3, shelterEntity.getCapacity());
            ps.setString(4, shelterEntity.getLocation());
            ps.setInt(5, shelterEntity.getIdShelter());
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
                shelterEntity.setIdShelter(rs.getInt("id"));
                shelterEntity.setNameShelter(rs.getString("nombre"));
                shelterEntity.setResponsible(rs.getString("responsable"));
                shelterEntity.setCapacity(rs.getInt("capacidad"));
                shelterEntity.setLocation(rs.getString("ubicacion"));
                shelters.add(shelterEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return shelters;
    }

    @Override
    public void cleanUpTable() throws PersistenceException {
        String sql = "DELETE FROM refugios";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement()) {
            int filas = stmt.executeUpdate(sql);
            System.out.println("Se eliminaron " + filas + " registros de la tabla refugios.");

        } catch (SQLException e) {
            System.out.println("Error al limpiar la tabla refugios: " + e.getMessage());

        }
    }

    @Override
    public List<ShelterEntity> getSheltersById(int id) {
        String sql = "SELECT * FROM refugios WHERE id = ?";
        List<ShelterEntity> shelters = new ArrayList<>();

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ShelterEntity shelterEntity = new ShelterEntity();
                    shelterEntity.setIdShelter(rs.getInt("id"));
                    shelterEntity.setNameShelter(rs.getString("nombre"));
                    shelterEntity.setResponsible(rs.getString("responsable"));
                    shelterEntity.setCapacity(rs.getInt("capacidad"));
                    shelterEntity.setLocation(rs.getString("ubicacion"));
                    shelters.add(shelterEntity);
                }
            }
        } catch (SQLException exception) {
            System.out.println("No se ha encontrado el refugio");
            exception.printStackTrace();
        }
        return shelters;
    }

    @Override
    public boolean isNotEmpty() {
        String sql = "SELECT 1 FROM refugios LIMIT 1";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar registros de la tabla refugios", e);
        }
    }

    @Override
    public List<AnimalEntity> getAnimals(int id) throws PersistenceException {
        List<AnimalEntity> animals = new ArrayList<>();
        String sql = "SELECT * FROM animales WHERE id_refugio = ?";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AnimalEntity animal = new AnimalEntity();
                    animal.setId(rs.getInt("id"));
                    animal.setName(rs.getString("nombre"));
                    animal.setAge(rs.getInt("edad"));
                    animal.setDate_entry(rs.getObject("fecha_ingreso" , LocalDate.class));
                    animal.setHealth_situation(rs.getString("estado_salud"));
                    animal.setSpecie(rs.getString("especie"));
                    animals.add(animal);
                }
            }
        } catch (SQLException exception) {
            System.out.println("No se ha encontrado el refugio");
            exception.printStackTrace();
        }
        return animals;
    }
}
