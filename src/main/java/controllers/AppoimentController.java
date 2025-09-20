package controllers;

import config.ConexionDB;
import dao.AppoimentDAO;
import interfaces.controller.IAppoimentController;
import interfaces.dao.IAppoimentDAO;
import models.AppoimentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AppoimentController implements IAppoimentController {

    private IAppoimentDAO appoimentDAO;

    public AppoimentController(){
        this.appoimentDAO = new AppoimentDAO();
    }

    public boolean addAppoiment(String comments, String status, LocalDate date_booked, LocalDate date_event, Integer id_animal, int id_volunteer, String activity){
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
        if(activity == null ){
            System.out.println("asegurese de proporcionar una actividad");
            return false;
        }
        if(comments == null || comments.trim().isEmpty()) comments = "";

        AppoimentEntity appoimentEntity = new AppoimentEntity();
        appoimentEntity.setComments(comments);
        appoimentEntity.setStatus("pendiente");
        appoimentEntity.setDateBooked(date_booked);
        appoimentEntity.setDateEvent(date_event);
        appoimentEntity.setIdAnimal(id_animal);
        appoimentEntity.setIdVolunteer(id_volunteer);
        appoimentEntity.setActivity(activity);

        return this.appoimentDAO.create(appoimentEntity);
    }

    public AppoimentEntity readAppoiment(int id){
        if(id < 0){
            return null;
        }
        return this.appoimentDAO.readById(id);
    }

    public boolean updateAppoiment(int id, String comments, String status, LocalDate date_booked, LocalDate date_event, Integer id_animal, int id_volunteer, String activity){
        if(id < 0){
            return  false;
        }
        if(comments == null || comments.trim().isEmpty() || status == null || date_booked == null || activity == null || activity.trim().isEmpty()){
            return false;
        }
        if(id_animal != null && id_animal < 0) {
            return false;
        }
        if(id_volunteer < 0){
            return false;
        }
        AppoimentEntity appoimentEntity = new AppoimentEntity();
        appoimentEntity.setId(id);
        appoimentEntity.setComments(comments);
        appoimentEntity.setStatus(status);
        appoimentEntity.setDateBooked(date_booked);
        appoimentEntity.setDateEvent(date_event);
        appoimentEntity.setIdAnimal(id_animal);
        appoimentEntity.setIdVolunteer(id_volunteer);
        appoimentEntity.setActivity(activity);
        return this.appoimentDAO.update(appoimentEntity);
    }

    public boolean deleteAppoiment(int id){
        if(id < 0){
            return false;
        }
        return this.appoimentDAO.deleteById(id);
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
