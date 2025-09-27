package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IVolunteerDAO;
import models.VolunteerEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VolunteerDAO implements IVolunteerDAO {

    @Override
    public void insertVolunteers() throws PersistenceException {
        if (isNotEmpty()) {
            System.out.println("Error: La tabla 'voluntarios' ya tiene datos. No se insertarán datos de ejemplo.\n");
            return;
        }

        int contInserts = 0;
        String sql = "INSERT INTO voluntarios (nombre, telefono, email, fecha_nacimiento, especialidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Insertando voluntario 1...");
            pstmt.setString(1, "Ana Gómez");
            pstmt.setString(2, "5512345678");
            pstmt.setString(3, "ana.gomez@example.com");
            pstmt.setDate(4, java.sql.Date.valueOf("1990-05-14"));
            pstmt.setString(5, "Adiestramiento");
            pstmt.executeUpdate();
            contInserts++;

            System.out.println("Insertando voluntario 2...");
            pstmt.setString(1, "Carlos Pérez");
            pstmt.setString(2, "5523456789");
            pstmt.setString(3, "carlos.perez@example.com");
            pstmt.setDate(4, java.sql.Date.valueOf("1985-09-21"));
            pstmt.setString(5, "Logística");
            pstmt.executeUpdate();
            contInserts++;

            System.out.println("Insertando voluntario 3...");
            pstmt.setString(1, "María López");
            pstmt.setString(2, "5534567890");
            pstmt.setString(3, "maria.lopez@example.com");
            pstmt.setDate(4, java.sql.Date.valueOf("1992-12-03"));
            pstmt.setString(5, "Veterinaria");
            pstmt.executeUpdate();
            contInserts++;


        } catch (SQLException e) {
            System.out.println("Error al insertar voluntarios: " + e.getMessage());
        }

        System.out.printf("Se insertaron %d voluntarios.%n", contInserts);


    }

    @Override
    /**
     * adds a volunteer entity object into the volunteer Table on a database
     * @param volunteerEntity
     */
    public boolean create(VolunteerEntity volunteerEntity) throws PersistenceException {

        String sql = "INSERT INTO voluntarios (nombre, telefono, email, fecha_nacimiento, especialidad) VALUES (?,?,?,?,?)";
            try(
                    Connection con = ConexionDB.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ){
                ps.setString(1,volunteerEntity.getName_volunteer());
                ps.setString(2,volunteerEntity.getPhone_number());
                ps.setString(3,volunteerEntity.getEmail());
                ps.setDate(4, volunteerEntity.getDate_birth());
                ps.setString(5, volunteerEntity.getSpecialty());
                System.out.println("El voluntario se ha agregado exitosamente");
                return ps.executeUpdate() > 0;

            }catch (SQLException exception){
                System.out.println("No se ha podido insertar el voluntario");
                exception.printStackTrace();
                return false;

            }
    }

    /**
     * Find and returns a volunteer by the Unique ID
     * @param id
     * @return VolunteerEntity
     */
    @Override
    public VolunteerEntity readById(int id) throws PersistenceException {
        String sql = "SELECT * FROM voluntarios where id = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1,id);
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        VolunteerEntity volunteerEntity = new VolunteerEntity();
                        volunteerEntity.setId_volunteer(rs.getInt("id"));
                        volunteerEntity.setName_volunteer(rs.getString("nombre"));
                        volunteerEntity.setPhone_number(rs.getString("telefono"));
                        volunteerEntity.setEmail(rs.getString("email"));
                        volunteerEntity.setDate_birth(rs.getDate("fecha_nacimiento"));
                        volunteerEntity.setSpecialty(rs.getString("especialidad"));
                        System.out.println("Voluntario encontrado: " + volunteerEntity.toString());
                        return volunteerEntity;
                    }
                }
        }catch (SQLException exception) {
            System.out.println("No se ha encontrado al voluntario");
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Updates a volunteer in the database
     */
    @Override
    public boolean update(VolunteerEntity volunteerEntity) throws PersistenceException {
        String sql = "UPDATE voluntarios SET nombre = ?, telefono = ?, email = ?, fecha_nacimiento = ?, especialidad = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, volunteerEntity.getName_volunteer());
            ps.setString(2, volunteerEntity.getPhone_number());
            ps.setString(3, volunteerEntity.getEmail());
            ps.setDate(4, volunteerEntity.getDate_birth());
            ps.setString(5, volunteerEntity.getSpecialty());
            ps.setInt(6, volunteerEntity.getId_volunteer());
            System.out.println("Voluntario actualizado con exito");
            return ps.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println("No se ha podido actualizar el voluntario");
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a volunteer in the database by the ID
     */
    @Override
    public boolean deleteById(int id) throws PersistenceException {
        String sql = "DELETE FROM voluntarios WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1,id);

                int deletedRows = ps.executeUpdate();
                if(deletedRows > 0){
                    System.out.println("El voluntario se ha eliminado con exito");
                    return true;
                }else{
                    System.out.println("No se ha podido eliminar el voluntario");
                    return false;
                }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * returns a list with all the volunteers from the database
     * @return a list with every volunteer from the database
     */
    @Override
    public List<VolunteerEntity> readAll() throws PersistenceException {
        String sql = "SELECT * FROM voluntarios";
        List<VolunteerEntity> volunteers = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VolunteerEntity volunteerEntity = new VolunteerEntity();
                volunteerEntity.setId_volunteer(rs.getInt("id"));
                volunteerEntity.setName_volunteer(rs.getString("nombre"));
                volunteerEntity.setPhone_number(rs.getString("telefono"));
                volunteerEntity.setEmail(rs.getString("email"));
                volunteerEntity.setDate_birth(rs.getDate("fecha_nacimiento"));
                volunteerEntity.setSpecialty(rs.getString("especialidad"));
                volunteers.add(volunteerEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return volunteers;
    }

    @Override
    public DefaultTableModel getVooluntersByIdTable(int id) {
        String[] columns = {"Id", "Nombre", "Teléfono", "Email", "Fecha de Nacimiento", "Especialidad"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<VolunteerEntity> volunteerList = null;
        try {
            volunteerList = readAll();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        for (VolunteerEntity v : volunteerList) {
            if (v.getId_volunteer() == id) {
                Object[] row = {
                        v.getId_volunteer(),
                        v.getName_volunteer(),
                        v.getPhone_number(),
                        v.getEmail(),
                        v.getDate_birth(),
                        v.getSpecialty()
                };
                model.addRow(row);
            }
        }

        return model;
    }

    @Override
    public boolean isNotEmpty() {
        String sql = "SELECT 1 FROM voluntarios LIMIT 1";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar registros de la tabla voluntarios", e);
        }
    }


}
