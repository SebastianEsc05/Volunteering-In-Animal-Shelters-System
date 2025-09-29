package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IVolunteerDAO;
import models.VolunteerEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class VolunteerDAO implements IVolunteerDAO {

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
                ps.setObject(4, volunteerEntity.getDate_birth());
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
                        volunteerEntity.setDate_birth(rs.getObject("fecha_nacimiento", Date.class));
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
            ps.setObject(4, volunteerEntity.getDate_birth());
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
                volunteerEntity.setDate_birth(rs.getObject("fecha_nacimiento", Date.class));
                volunteerEntity.setSpecialty(rs.getString("especialidad"));
                volunteers.add(volunteerEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return volunteers;
    }

    @Override
    public List<VolunteerEntity> getVooluntersByIdTable(int id) {
        String sql = "SELECT * FROM voluntarios WHERE id = ?";
        List<VolunteerEntity> volunteers = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VolunteerEntity volunteerEntity = new VolunteerEntity();
                    volunteerEntity.setId_volunteer(rs.getInt("id"));
                    volunteerEntity.setName_volunteer(rs.getString("nombre"));
                    volunteerEntity.setPhone_number(rs.getString("telefono"));
                    volunteerEntity.setEmail(rs.getString("email"));
                    volunteerEntity.setDate_birth(rs.getObject("fecha_nacimiento", Date.class));
                    volunteerEntity.setSpecialty(rs.getString("especialidad"));
                    volunteers.add(volunteerEntity);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return volunteers;
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
