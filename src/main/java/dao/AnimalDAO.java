package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IAnimalDAO;
import models.AnimalEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO implements IAnimalDAO {

    @Override
    public void insertAnimals() throws PersistenceException {
        if (isNotEmpty()) {
            System.out.println("Error: La tabla 'animales' ya tiene datos. No se insertarán datos de ejemplo.\n");
            return;

        }
        int contInserts = 0;
        String sql = "INSERT INTO animales " +
                "(nombre, edad, fecha_ingreso, estado_salud, especie, id_refugio) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "pipi");
            pstmt.setInt(2, 3);
            pstmt.setDate(3, java.sql.Date.valueOf("2025-09-01"));
            pstmt.setString(4, "Saludable");
            pstmt.setString(5, "Perro");
            pstmt.setInt(6, 1);
            pstmt.executeUpdate();
            contInserts++;

            pstmt.setString(1, "sebastian");
            pstmt.setInt(2, 2);
            pstmt.setDate(3, java.sql.Date.valueOf("2025-08-20"));
            pstmt.setString(4, "Enfermo");
            pstmt.setString(5, "Gata");
            pstmt.setInt(6, 2);
            pstmt.executeUpdate();
            contInserts++;

            pstmt.setString(1, "manuel");
            pstmt.setInt(2, 4);
            pstmt.setDate(3, java.sql.Date.valueOf("2025-07-15"));
            pstmt.setString(4, "Saludable");
            pstmt.setString(5, "Perra");
            pstmt.setInt(6, 1);
            pstmt.executeUpdate();
            contInserts++;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.printf("Se insertaron %d animales.%n", contInserts);
    }

    @Override
    public AnimalEntity readByHealthSituation(String healthSituation) throws PersistenceException {
        return null;
    }

    @Override
    public boolean create(AnimalEntity animalEntity) throws PersistenceException {
        String sql = "INSERT INTO animales (nombre, edad, fecha_ingreso, estado_salud, especie, id_refugio) VALUES (?,?,?,?,?,?)";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, animalEntity.getName());
            ps.setInt(2, animalEntity.getAge());
            ps.setObject(3, animalEntity.getDate_entry());
            ps.setString(4, animalEntity.getHealth_situation());
            ps.setString(5, animalEntity.getSpecie());
            ps.setInt(6, animalEntity.getId_shelter());

            System.out.println("El Animal se ha agregado exitosamente");
            return ps.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println("No se ha podido insertar el animal");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public AnimalEntity readById(int id) throws PersistenceException {
        String sql = "SELECT * FROM animales where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AnimalEntity animalEntity = new AnimalEntity();
                    animalEntity.setId(rs.getInt("id"));
                    animalEntity.setName(rs.getString("nombre"));
                    animalEntity.setAge(rs.getInt("edad"));
                    animalEntity.setDate_entry(rs.getObject("fecha_ingreso", LocalDate.class));
                    animalEntity.setHealth_situation(rs.getString("estado_salud"));
                    animalEntity.setSpecie(rs.getString("especie"));
                    animalEntity.setId_shelter(rs.getInt("id_refugio"));
                    ;

                    System.out.println("Animal encotrado: " + animalEntity.toString());
                    return animalEntity;
                }
            }
        } catch (SQLException exception) {
            System.out.println("No se ha encontrado el animal");
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(AnimalEntity animalEntity) throws PersistenceException {
        String sql = "UPDATE animales SET nombre = ?, edad = ?, fecha_ingreso = ?, estado_salud = ?, especie = ?, id_refugio = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, animalEntity.getName());
            ps.setInt(2, animalEntity.getAge());
            ps.setObject(3, animalEntity.getDate_entry());
            ps.setString(4, animalEntity.getHealth_situation());
            ps.setString(5, animalEntity.getSpecie());
            ps.setInt(6, animalEntity.getId_shelter());
            ps.setInt(7, animalEntity.getId());
            System.out.println("Animal actualizado con exito");
            return ps.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println("No se ha podido actualizar el animal");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) throws PersistenceException {
        String sql = "DELETE FROM animales WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int deletedRows = ps.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("El animal se ha eliminado con exito");
                return true;
            } else {
                System.out.println("No se ha podido eliminar al animal");
                return false;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AnimalEntity> readAll() throws PersistenceException {
        String sql = "SELECT * FROM animales";
        List<AnimalEntity> animals = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AnimalEntity animalEntity = new AnimalEntity();
                animalEntity.setId(rs.getInt("id"));
                animalEntity.setName(rs.getString("nombre"));
                animalEntity.setAge(rs.getInt("edad"));
                animalEntity.setDate_entry(rs.getObject("fecha_ingreso", LocalDate.class));
                animalEntity.setHealth_situation(rs.getString("estado_salud"));
                animalEntity.setSpecie(rs.getString("especie"));
                animalEntity.setId_shelter(rs.getInt("id_refugio"));

                animals.add(animalEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return animals;
    }

    @Override
    public List<AnimalEntity> getAnimalsByIdTable(int id) {
        String sql = "SELECT * FROM animales WHERE id = ?";
        List<AnimalEntity> animals = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AnimalEntity animalEntity = new AnimalEntity();
                    animalEntity.setId(rs.getInt("id"));
                    animalEntity.setName(rs.getString("nombre"));
                    animalEntity.setAge(rs.getInt("edad"));
                    animalEntity.setDate_entry(rs.getObject("fecha_ingreso", LocalDate.class));
                    animalEntity.setHealth_situation(rs.getString("estado_salud"));
                    animalEntity.setSpecie(rs.getString("especie"));
                    animalEntity.setId_shelter(rs.getInt("id_refugio"));

                    animals.add(animalEntity);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return animals;
    }

    @Override
    public boolean isNotEmpty() {
        String sql = "SELECT 1 FROM animales LIMIT 1";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar registros de la tabla animales", e);
        }
    }


}
