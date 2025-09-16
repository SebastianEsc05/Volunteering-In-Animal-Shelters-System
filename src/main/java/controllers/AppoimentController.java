package controllers;

import dao.AppoimentDAO;
import interfaces.IAppoimentDAO;
import interfaces.IShelterDAO;
import models.AppoimentEntity;

import java.util.List;

public class AppoimentController {

    private IAppoimentDAO appoimentDAO;

    public AppoimentController(){
        this.appoimentDAO = new AppoimentDAO();
    }

    public boolean addAppoiment(String comments, String status, String date_booked, String date_event, Integer id_animal, int id_volunteer, String activity){
        if(comments == null || comments.trim().isEmpty() || status == null || date_booked.trim().isEmpty() || activity == null || activity.trim().isEmpty()){
            return false;
        }
        if(id_animal != null && id_animal < 0) {
            return false;
        }
        if(id_volunteer < 0){
            return false;
        }

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

}
