package controllers;

import dao.VolunteerDAO;
import interfaces.IVolunteerDAO;
import models.VolunteerEntity;

import java.sql.SQLException;

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

}
