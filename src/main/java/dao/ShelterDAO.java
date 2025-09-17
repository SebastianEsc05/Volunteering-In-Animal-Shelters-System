package dao;

import config.ConexionDB;
import interfaces.IShelterDAO;
import models.ShelterEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO implements IShelterDAO {

    @Override
    public boolean create(ShelterEntity shelterEntity) {
        String sql = "INSERT INTO refugios (nombre, responsable, capacidad, ubicacion) VALUES (?,?,?,?);";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){
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
    public ShelterEntity read(int id) {
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
    public boolean update(ShelterEntity shelterEntity) {
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
    public boolean delete(int id) {
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
    public List<ShelterEntity> readAll() {
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

}
