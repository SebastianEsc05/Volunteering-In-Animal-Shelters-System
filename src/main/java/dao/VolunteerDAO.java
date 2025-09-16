package dao;

import config.ConexionDB;
import controllers.VolunteerController;
import interfaces.IVolunteerDAO;
import models.VolunteerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolunteerDAO implements IVolunteerDAO {


    @Override
    public boolean create(VolunteerEntity volunteerEntity) throws SQLException {
        String sql = "INSERT INTO voluntarios (nombre, telefono, email, fecha_nacimiento, especialidad) VALUES (?,?,?,?,?)";
            try(
                    Connection con = ConexionDB.getConnection();
                    PreparedStatement ps = con.prepareStatement(sql);
            ){
                ps.setString(1,volunteerEntity.getName_volunteer());
                ps.setString(2,volunteerEntity.getPhone_number());
                ps.setString(3,volunteerEntity.getEmail());
                ps.setString(4, volunteerEntity.getDate_birth());
                ps.setString(5, volunteerEntity.getSpecialty());
                System.out.println("El voluntario se ha agregado exitosamente");
                return ps.executeUpdate() > 0;
            }catch (SQLException exception){
                System.out.println("No se ha podido insertar el voluntario");
                exception.printStackTrace();
                return false;
            }
    }

    @Override
    public VolunteerEntity read(int id) throws SQLException {
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
                        volunteerEntity.setDate_birth(rs.getString("fecha_nacimiento"));
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

    @Override
    public boolean update(VolunteerEntity volunteerEntity) {
        String sql = "UPDATE voluntarios SET nombre = ?, telefono = ?, email = ?, fecha_nacimiento = ?, especialidad = ? where id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, volunteerEntity.getName_volunteer());
            ps.setString(2, volunteerEntity.getPhone_number());
            ps.setString(3, volunteerEntity.getEmail());
            ps.setString(4, volunteerEntity.getDate_birth());
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

    @Override
    public boolean delete(int id) {
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

    @Override
    public List<VolunteerEntity> readAll() {
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
                volunteerEntity.setDate_birth(rs.getString("fecha_nacimiento"));
                volunteerEntity.setSpecialty(rs.getString("especialidad"));
                volunteers.add(volunteerEntity);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        for(VolunteerEntity volunteerEntity : volunteers){
            System.out.println(volunteerEntity.toString());
        }
        return volunteers;
    }
}
