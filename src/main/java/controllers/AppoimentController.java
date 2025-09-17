package controllers;

import config.ConexionDB;
import dao.AppoimentDAO;
import interfaces.IAppoimentDAO;
import interfaces.IShelterDAO;
import models.AppoimentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppoimentController {

    private IAppoimentDAO appoimentDAO;

    public AppoimentController(){
        this.appoimentDAO = new AppoimentDAO();
    }

    public boolean addAppoiment(String comments, String status, String date_booked, String date_event, Integer id_animal, int id_volunteer, String activity){
        if(id_animal != null){
            if(id_animal < 0 || !animalExists(id_animal)){
                System.out.println("no se encontro registro del animal");
                return false;
            }
        }
        if(id_volunteer < 0 || !volunteerExists(id_volunteer)){
            System.out.println("no se encontró registro del voluntario");
            return false;
        }
        if(status == null){
            System.out.println("asegurese de proporcionar un estado correcto");
            return false;
        }
        if(activity == null ){
            System.out.println("asegurese de proporcionar una actividad");
            return false;
        }
        if(comments == null || comments.trim().isEmpty()) comments = "";
        if(date_event == null) date_event = "";

        AppoimentEntity appoimentEntity = new AppoimentEntity(comments,status,date_booked,date_event, id_animal, id_volunteer, activity);
        return this.appoimentDAO.create(appoimentEntity);
    }

    public AppoimentEntity readAppoiment(int id){
        if(id < 0){
            return null;
        }
        return this.appoimentDAO.read(id);
    }

    public boolean deleteAppoiment(int id){
        if(id < 0){
            return false;
        }
        return this.appoimentDAO.delete(id);
    }

    public boolean updateAppoiment(int id, String comments, String status, String date_booked, String date_event, Integer id_animal, int id_volunteer, String activity){
        if(id < 0){
            return  false;
        }
        if(comments == null || comments.trim().isEmpty() || status == null || date_booked.trim().isEmpty() || activity == null || activity.trim().isEmpty()){
            return false;
        }
        if(id_animal != null && id_animal < 0) {
            return false;
        }
        if(id_volunteer < 0){
            return false;
        }
        AppoimentEntity appoimentEntity = new AppoimentEntity(comments, status, date_booked, date_event, id_animal, id_volunteer, activity);
        appoimentEntity.setId(id);
        return this.appoimentDAO.update(appoimentEntity);
    }

    public List<AppoimentEntity> readAllAppoiments(){
        return this.appoimentDAO.readAll();
    }

    public boolean animalExists(int id){
        String sql = "SELECT COUNT(*) FROM animales WHERE id = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean volunteerExists(int id){
        String sql = "SELECT COUNT(*) FROM voluntarios WHERE id = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
