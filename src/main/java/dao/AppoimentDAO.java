package dao;

import config.ConexionDB;
import dao.exceptions.PersistenceException;
import interfaces.dao.IAppoimentDAO;
import models.AppoimentEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppoimentDAO implements IAppoimentDAO {

    @Override
    public void insertAppointments() throws PersistenceException {

    }

    @Override
    public boolean create(AppoimentEntity appoimentEntity) throws PersistenceException {
        String sql = "INSERT INTO asignaciones (observaciones, estado, fecha_de_agenda, fecha_realizacion, id_animal, id_voluntario, actividad) VALUES (?,?,?,?,?,?,?)";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, appoimentEntity.getComments());
            ps.setObject(2, appoimentEntity.getStatus());
            ps.setObject(3, appoimentEntity.getDateBooked());
            if (appoimentEntity.getDateEvent() != null ) {
                ps.setObject(4, appoimentEntity.getDateEvent());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            if (appoimentEntity.getIdAnimal() != null) {
                ps.setInt(5, appoimentEntity.getIdAnimal());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setInt(6, appoimentEntity.getIdVolunteer());
            ps.setString(7, appoimentEntity.getActivity());
            System.out.println("La asignación se ha agregado exitosamente");
            return ps.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.out.println("No se ha podido agregar la asignación");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public AppoimentEntity readById(int id) throws PersistenceException {
        String sql = "SELECT * FROM asignaciones where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AppoimentEntity appoimentEntity = new AppoimentEntity();
                    appoimentEntity.setId(rs.getInt("id"));
                    appoimentEntity.setComments(rs.getString("observaciones"));
                    appoimentEntity.setStatus(rs.getString("estado"));
                    appoimentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                    appoimentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                    appoimentEntity.setIdAnimal(rs.getInt("id_animal"));
                    appoimentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                    appoimentEntity.setActivity(rs.getString("actividad"));
                    System.out.println("Asignacion encotrada: " + appoimentEntity.toString());
                    return appoimentEntity;
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
    public boolean update(AppoimentEntity appoimentEntity) throws PersistenceException {
        String sql = "UPDATE asignaciones SET observaciones = ?, estado = ?, fecha_de_agenda = ?, fecha_realizacion = ?, id_animal = ?, id_voluntario = ?, actividad = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, appoimentEntity.getComments());
            ps.setObject(2, appoimentEntity.getStatus());
            ps.setObject(3, appoimentEntity.getDateBooked());
            if (appoimentEntity.getDateEvent() != null ) {
                ps.setObject(4, appoimentEntity.getDateEvent());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            if (appoimentEntity.getIdAnimal() != null) {
                ps.setInt(5, appoimentEntity.getIdAnimal());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setInt(6, appoimentEntity.getIdVolunteer());
            ps.setString(7, appoimentEntity.getActivity());
            ps.setInt(8, appoimentEntity.getId());
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
    public List<AppoimentEntity> readAll() throws PersistenceException {
        String sql = "SELECT * FROM asignaciones";
        List<AppoimentEntity> appoiments = new ArrayList<>();
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AppoimentEntity appoimentEntity = new AppoimentEntity();
                appoimentEntity.setId(rs.getInt("id"));
                appoimentEntity.setComments(rs.getString("observaciones"));
                appoimentEntity.setStatus(rs.getString("estado"));
                appoimentEntity.setDateBooked(rs.getObject("fecha_de_agenda", LocalDate.class));
                appoimentEntity.setDateEvent(rs.getObject("fecha_realizacion", LocalDate.class));
                appoimentEntity.setIdAnimal(rs.getInt("id_animal"));
                appoimentEntity.setIdVolunteer(rs.getInt("id_voluntario"));
                appoimentEntity.setActivity(rs.getString("actividad"));
                appoiments.add(appoimentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        for (AppoimentEntity appoimentEntity : appoiments) {
            System.out.println(appoimentEntity.toString());
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
