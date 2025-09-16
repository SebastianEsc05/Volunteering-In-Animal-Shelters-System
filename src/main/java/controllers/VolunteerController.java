package controllers;

import dao.VolunteerDAO;
import interfaces.IVolunteerDAO;
import models.VolunteerEntity;

import java.sql.SQLException;
import java.util.List;

public class VolunteerController {

    private IVolunteerDAO volunteerDAO;

    public VolunteerController(){
        this.volunteerDAO = new VolunteerDAO();
    }

    public boolean addVolunteer(String name, String phone_number, String email, String date_birth, String specialty) throws SQLException {
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        if(phone_number == null)phone_number = "";
        if(email == null) email = "";
        if(date_birth == null) date_birth = "";
        if(specialty == null)specialty = "";
        VolunteerEntity volunteerEntity = new VolunteerEntity(name,phone_number,email, date_birth, specialty);
        return this.volunteerDAO.create(volunteerEntity);
    }

    public VolunteerEntity readVolunteer(int id) throws SQLException {
        if(id <= 0){
            return null;
        }
        return this.volunteerDAO.read(id);

    }

    public boolean deleteVolunteer(int id) throws SQLException {
        if(id <= 0 ){
            return false;
        }
        return this.volunteerDAO.delete(id);
    }

    public boolean updateVolunteer(int id, String name, String phone_number, String email, String date_birth, String specialty){
        if(name == null){
            return false;
        }
        if(phone_number == null)phone_number = "";
        if(email == null) email = "";
        if(date_birth == null) date_birth = "";
        if(specialty == null)specialty = "";
        VolunteerEntity volunteerEntity = new VolunteerEntity(name,phone_number,email, date_birth, specialty);
        volunteerEntity.setId_volunteer(id);
        return this.volunteerDAO.update(volunteerEntity);

    }

    public List<VolunteerEntity> readAllVolunteers(){
        return this.volunteerDAO.readAll();
    }
}
