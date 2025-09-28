package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IAppointmentDAO;
import models.AppointmentEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements IAppointmentDAO {

    @Override
    public boolean create(AppointmentEntity appointmentEntity) throws PersistenceException, SQLException {
        String checkAnimalId = "SELECT COUNT(*) FROM animales WHERE id = ?";
        String checkVolunteerId = "SELECT COUNT(*) FROM voluntarios WHERE id = ?";
        String sql = "INSERT INTO asignaciones (observaciones, estado, fecha_de_agenda, fecha_realizacion, id_animal, id_voluntario, actividad, requiere_animal) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            if (appointmentEntity.getIdAnimal() != null) {
                try (PreparedStatement checkAnimalPs = con.prepareStatement(checkAnimalId)) {
                    checkAnimalPs.setInt(1, appointmentEntity.getIdAnimal());
                    try (ResultSet rs = checkAnimalPs.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 0) {
                            throw new PersistenceException("El animal con id '" + appointmentEntity.getIdAnimal() + "' no existe.");
                        }
                    }
                }
            }

            try (PreparedStatement checkVolunteerPs = con.prepareStatement(checkVolunteerId)) {
                checkVolunteerPs.setInt(1, appointmentEntity.getIdVolunteer());
                try (ResultSet rs = checkVolunteerPs.executeQuery()) {
                    if (rs.next() && rs.getInt(1) == 0) {
                        System.out.println("El voluntario con id '" + appointmentEntity.getIdVolunteer() + "' no existe.");
                        throw new PersistenceException("El voluntario con id '" + appointmentEntity.getIdVolunteer() + "' no existe.");
                    }
                    ps.setString(1, appointmentEntity.getComments());
                    ps.setObject(2, appointmentEntity.getStatus());
                    ps.setObject(3, appointmentEntity.getDateBooked());
                    if (appointmentEntity.getDateEvent() != null) {
                        ps.setObject(4, appointmentEntity.getDateEvent());
                    } else {
                        ps.setNull(4, java.sql.Types.DATE);
                    }

                    if (appointmentEntity.getIdAnimal() != null) {
                        ps.setInt(5, appointmentEntity.getIdAnimal());
                    } else {
                        ps.setNull(5, java.sql.Types.INTEGER);

                    }
                    ps.setInt(6, appointmentEntity.getIdVolunteer());
                    ps.setString(7, appointmentEntity.getActivity());
                    ps.setBoolean(8, appointmentEntity.isAnimalCheck());

                    System.out.println("La asignación se ha agregado exitosamente");
                    return ps.executeUpdate() > 0;

                } catch (SQLException exception) {
                    System.out.println("No se ha podido agregar la asignación");
                    exception.printStackTrace();
                    return false;
                }catch(PersistenceException exception){
                    throw new PersistenceException(exception.getMessage());
                }
            }
        }
    }

    @Override
    public AppointmentEntity readById(int id) throws PersistenceException {
        String sql = "SELECT * FROM asignaciones where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AppointmentEntity appointmentEntity = new AppointmentEntity();
                    appointmentEntity.setId(rs.getInt("id"));
                    appointmentEntity.setComments(rs.getString("observaciones"));
                    appointmentEntity.setStatus(rs.getString("estado"));
                    appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                    appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                    appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                    appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                    appointmentEntity.setActivity(rs.getString("actividad"));
                    return appointmentEntity;
                }
            }
        } catch (SQLException exception) {
            System.out.println("No se ha encontrado la asignacion");
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(AppointmentEntity appointmentEntity) throws PersistenceException {
        String sql = "UPDATE asignaciones SET observaciones = ?, estado = ?, fecha_de_agenda = ?, fecha_realizacion = ?, id_animal = ?, id_voluntario = ?, actividad = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, appointmentEntity.getComments());
            ps.setObject(2, appointmentEntity.getStatus());
            ps.setObject(3, appointmentEntity.getDateBooked());
            if (appointmentEntity.getDateEvent() != null) {
                ps.setObject(4, appointmentEntity.getDateEvent());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            if (appointmentEntity.getIdAnimal() != null) {
                ps.setInt(5, appointmentEntity.getIdAnimal());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setInt(6, appointmentEntity.getIdVolunteer());
            ps.setString(7, appointmentEntity.getActivity());
            ps.setInt(8, appointmentEntity.getId());
            System.out.println("Asignacion actualizado con exito");
            return ps.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println("No se ha podido actualizar la asignacion");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) throws PersistenceException {
        String sql = "DELETE FROM asignaciones WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int deletedRows = ps.executeUpdate();
            if (deletedRows > 0) {
                System.out.println("La asignacion se ha eliminado con exito");
                return true;
            } else {
                System.out.println("No se ha podido eliminar la asignacion");
                return false;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AppointmentEntity> readAll() throws PersistenceException {
        String sql = "SELECT * FROM asignaciones";
        List<AppointmentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AppointmentEntity appointmentEntity = new AppointmentEntity();
                appointmentEntity.setId(rs.getInt("id"));
                appointmentEntity.setComments(rs.getString("observaciones"));
                appointmentEntity.setStatus(rs.getString("estado"));
                appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                appointmentEntity.setActivity(rs.getString("actividad"));
                appoiments.add(appointmentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appoiments;
    }

    @Override
    public List<AppointmentEntity> searchByState(Integer id, String estado) throws PersistenceException {
        List<AppointmentEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM animales WHERE 1=1");

        if (id != null) {
            sql.append(" AND id = ?");
        }
        if (estado != null) {
            sql.append(" AND estado_de_salud = ?");
        }

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int index = 1;
            if (id != null) {
                ps.setInt(index++, id);
            }
            if (estado != null) {
                ps.setString(index, estado);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentEntity a = new AppointmentEntity();
                a.setId(rs.getInt("id"));
                a.setStatus(rs.getString("estado"));
                a.setDateBooked(LocalDate.parse(rs.getString("fecha_de_agenda")));
                a.setDateEvent(LocalDate.parse(rs.getString("fecha_realizacion")));
                a.setIdAnimal(rs.getInt( "id_animal"));
                a.setIdVolunteer(rs.getInt( "id_voluntario"));
                a.setActivity(rs.getString(  "actividad"));
                a.setComments(rs.getString( "observaciones"));
                result.add(a);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error al buscar asignaciones");
        }

        return result;
    }

    @Override
    public List<AppointmentEntity>  getAppointmentsByStatusPending() throws PersistenceException {
        String sql = "SELECT * FROM asignaciones WHERE estado = 'pendiente'";
        List<AppointmentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AppointmentEntity appointmentEntity = new AppointmentEntity();
                appointmentEntity.setId(rs.getInt("id"));
                appointmentEntity.setComments(rs.getString("observaciones"));
                appointmentEntity.setStatus(rs.getString("estado"));
                appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                appointmentEntity.setActivity(rs.getString("actividad"));
                appoiments.add(appointmentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appoiments;
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByStatusCanceled() throws PersistenceException {
        String sql = "SELECT * FROM asignaciones WHERE estado = 'cancelado'";
        List<AppointmentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AppointmentEntity appointmentEntity = new AppointmentEntity();
                appointmentEntity.setId(rs.getInt("id"));
                appointmentEntity.setComments(rs.getString("observaciones"));
                appointmentEntity.setStatus(rs.getString("estado"));
                appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                appointmentEntity.setActivity(rs.getString("actividad"));
                appoiments.add(appointmentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appoiments;
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByStatusCompleted() throws PersistenceException {
        String sql = "SELECT * FROM asignaciones WHERE estado = 'completado'";
        List<AppointmentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AppointmentEntity appointmentEntity = new AppointmentEntity();
                appointmentEntity.setId(rs.getInt("id"));
                appointmentEntity.setComments(rs.getString("observaciones"));
                appointmentEntity.setStatus(rs.getString("estado"));
                appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                appointmentEntity.setActivity(rs.getString("actividad"));
                appoiments.add(appointmentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appoiments;
    }

    @Override
    public List<AppointmentEntity> getAppointmentsById(int id) {
        String sql = "SELECT * FROM asignaciones WHERE id = ?";
        List<AppointmentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentEntity appointmentEntity = new AppointmentEntity();
                    appointmentEntity.setId(rs.getInt("id"));
                    appointmentEntity.setComments(rs.getString("observaciones"));
                    appointmentEntity.setStatus(rs.getString("estado"));
                    appointmentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                    appointmentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                    appointmentEntity.setIdAnimal(rs.getInt("id_animal"));
                    appointmentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                    appointmentEntity.setActivity(rs.getString("actividad"));
                    appoiments.add(appointmentEntity);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return appoiments;

    }

    @Override
    public boolean isNotEmpty() {
        String sql = "SELECT 1 FROM asignaciones LIMIT 1";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar registros de la tabla asignaciones", e);
        }
    }



}

