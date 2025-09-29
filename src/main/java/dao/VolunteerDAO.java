package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IVolunteerDAO;
import models.AppointmentEntity;
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
    public boolean create(VolunteerEntity volunteerEntity) throws PersistenceException {
        String sql = "INSERT INTO voluntarios (nombre, telefono, email, fecha_nacimiento, especialidad) VALUES (?,?,?,?,?)";
        String checkPhone = "SELECT COUNT(*) FROM voluntarios WHERE telefono = ?";
        String checkEmail = "SELECT COUNT(*) FROM voluntarios WHERE email = ?";

        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement psInsert = con.prepareStatement(sql);
                PreparedStatement psCheckPhone = con.prepareStatement(checkPhone);
                PreparedStatement psCheckEmail = con.prepareStatement(checkEmail)
        ) {

            psCheckPhone.setString(1, volunteerEntity.getPhone_number());
            try (ResultSet rs = psCheckPhone.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new PersistenceException("El teléfono ya está en uso, no se puede agregar el voluntario");
                }
            }

            psCheckEmail.setString(1, volunteerEntity.getEmail());
            try (ResultSet rs = psCheckEmail.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new PersistenceException("El correo ya está en uso, no se puede agregar el voluntario");
                }
            }

            psInsert.setString(1, volunteerEntity.getName_volunteer());
            psInsert.setString(2, volunteerEntity.getPhone_number());
            psInsert.setString(3, volunteerEntity.getEmail());
            psInsert.setObject(4, volunteerEntity.getDate_birth());
            psInsert.setString(5, volunteerEntity.getSpecialty());

            System.out.println("El voluntario se ha agregado exitosamente");
            return psInsert.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println("No se ha podido insertar el voluntario");
            exception.printStackTrace();
            return false;

        } catch (PersistenceException ex) {
            throw new PersistenceException(ex.getMessage());
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
        if(hasAppointments(id)){
            System.out.println("El voluntario tiene citas asignadas, no se puede eliminar");
            return false;
        }
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
    public List<AppointmentEntity> getAppointmentsByVolunteerId(int volunteerId) throws PersistenceException {
        String sql = "SELECT * FROM asignaciones WHERE id_voluntario = ?";
        List<AppointmentEntity> appointments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, volunteerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentEntity appointmentEntity = new AppointmentEntity();
                    appointmentEntity.setId(rs.getInt("id"));
                    appointmentEntity.setComments(rs.getString("observaciones"));
                    appointmentEntity.setStatus(rs.getString("estado"));
                    appointmentEntity.setDateBooked(rs.getObject("fecha_realizacion", LocalDate.class));
                    appointmentEntity.setDateEvent(rs.getObject("fecha_de_agenda", LocalDate.class));
                    appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                    appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                    appointmentEntity.setActivity(rs.getString("actividad"));
                    appointmentEntity.setAnimalCheck(rs.getBoolean("requiere_animal"));
                    appointments.add(appointmentEntity);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return appointments;
    }

    public boolean hasAppointments(int volunteerId) throws PersistenceException {
        String sql = "SELECT COUNT(*) FROM asignaciones WHERE id_voluntario = ?";
        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, volunteerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
