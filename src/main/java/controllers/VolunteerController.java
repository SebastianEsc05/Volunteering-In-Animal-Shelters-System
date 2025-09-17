package controllers;

import dao.VolunteerDAO;
import interfaces.IVolunteerDAO;
import models.VolunteerEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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


        if(verificarEmail(email)&&verificarTelefono(phone_number)){
            VolunteerEntity volunteerEntity = new VolunteerEntity(name,phone_number,email, date_birth, specialty);
            return this.volunteerDAO.create(volunteerEntity);
        }else{
            return false;
        }

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


    }

    public List<VolunteerEntity> readAllVolunteers(){
        return this.volunteerDAO.readAll();
    }

    boolean verificarTelefono(String telefono){
        // Expresion regular de telefono
        String RegexTelefono = "^\\d{10}$";
        Pattern patternTelefonoPattern = Pattern.compile(RegexTelefono);

        Matcher matcherTelefono =  patternTelefonoPattern.matcher(telefono);

        return matcherTelefono.matches();
    }

    boolean verificarEmail(String email){
        // Expresion regular de Email
        String RegexEmail = "^\\w+@+\\w+.+\\w";
        Pattern  patternEmail = Pattern.compile(RegexEmail);

        // Verificacion de los datos con las expresiones regulares

        Matcher matcherEmail = patternEmail.matcher(email);

        return  matcherEmail.matches();
    }
}
