package dao;

import config.ConexionDB;
import interfaces.IAppoimentDAO;
import models.AppoimentEntity;
import models.VolunteerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppoimentDAO implements IAppoimentDAO {

    @Override
    public boolean create(AppoimentEntity appoimentEntity) {
        String sql = "INSERT INTO asignaciones (observaciones, estado, fecha_de_agenda, fecha_realizacion, id_animal, id_voluntario, actividad) VALUES (?,?,?,?,?,?,?)";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setString(1, appoimentEntity.getComments());
            ps.setObject(2, appoimentEntity.getStatus());
            ps.setString(3, appoimentEntity.getDate_booked());

            if (appoimentEntity.getDate_event() != null && !appoimentEntity.getDate_event().isEmpty()) {
                ps.setString(4, appoimentEntity.getDate_event());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            if (appoimentEntity.getId_animal() != null) {
                ps.setInt(5, appoimentEntity.getId_animal());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setInt(6, appoimentEntity.getId_volunteer()) ;
            ps.setString(7, appoimentEntity.getActivity());
            System.out.println("La asignación se ha agregado exitosamente");
            return ps.executeUpdate() > 0;
        }catch (SQLException exception){
            System.out.println("No se ha podido agregar la asignación");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public AppoimentEntity read(int id) {
        String sql = "SELECT * FROM asignaciones where id = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    AppoimentEntity appoimentEntity = new AppoimentEntity();
                    appoimentEntity.setId(rs.getInt("id"));
                    appoimentEntity.setComments(rs.getString("observaciones"));
                    appoimentEntity.setStatus(rs.getString("estado"));
                    appoimentEntity.setDate_booked(rs.getString("fecha_de_agenda"));
                    appoimentEntity.setDate_event(rs.getString("fecha_realizacion"));
                    appoimentEntity.setId_animal(rs.getInt("id_animal"));
                    appoimentEntity.setId_volunteer(rs.getInt("id_voluntario"));
                    appoimentEntity.set_activity(rs.getString("actividad"));
                    System.out.println("Asignacion encotrada: " + appoimentEntity.toString());
                    return appoimentEntity;
                }
            }
        }catch (SQLException exception) {
            System.out.println("No se ha encontrado la asignacion");
            exception.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean update(AppoimentEntity appoimentEntity) {
        String sql = "UPDATE asignaciones SET observaciones = ?, estado = ?, fecha_de_agenda = ?, fecha_realizacion = ?, id_animal = ?, id_voluntario = ?, actividad = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, appoimentEntity.getComments());
            ps.setObject(2, appoimentEntity.getStatus());
            ps.setString(3, appoimentEntity.getDate_booked());
            if (appoimentEntity.getDate_event() != null && !appoimentEntity.getDate_event().isEmpty()) {
                ps.setString(4, appoimentEntity.getDate_event());
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            if (appoimentEntity.getId_animal() != null) {
                ps.setInt(5, appoimentEntity.getId_animal());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.setInt(6, appoimentEntity.getId_volunteer());
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
    public boolean delete(int id) {
        String sql = "DELETE FROM asignaciones WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1,id);

            int deletedRows = ps.executeUpdate();
            if(deletedRows > 0){
                System.out.println("La asignacion se ha eliminado con exito");
                return true;
            }else{
                System.out.println("No se ha podido eliminar la asignacion");
                return false;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AppoimentEntity> readAll() {
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
                appoimentEntity.setDate_booked(rs.getString("fecha_de_agenda"));
                appoimentEntity.setDate_event(rs.getString("fecha_realizacion"));
                appoimentEntity.setId_animal(rs.getInt("id_animal"));
                appoimentEntity.setId_volunteer(rs.getInt("id_voluntario"));
                appoimentEntity.set_activity(rs.getString("actividad"));
                appoiments.add(appoimentEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        for(AppoimentEntity appoimentEntity : appoiments){
            System.out.println(appoimentEntity.toString());
        }
        return appoiments;
    }

}
