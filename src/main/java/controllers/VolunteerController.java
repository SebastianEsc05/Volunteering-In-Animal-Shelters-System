package controllers;

import config.ConexionDB;
import dao.VolunteerDAO;
import dao.exceptions.PersistenceException;
import interfaces.controller.IVolunteerController;
import interfaces.dao.IVolunteerDAO;
import models.VolunteerEntity;

import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VolunteerController implements IVolunteerController{

    private IVolunteerDAO volunteerDAO;

    public VolunteerController(){
        this.volunteerDAO = new VolunteerDAO();
    }

    @Override
    public void insertVolunteers() throws PersistenceException {
        this.volunteerDAO.insertVolunteers();
    }

    public boolean addVolunteer(String name, String phone_number, String email, Date date_birth, String specialty) {
        if(phone_number == null || !validate_phone_number(phone_number)){
            System.out.println("Voluntario no agregado Telefono invalido");
            return false;
        }
        if(email == null || !validateEmail(email)){
            System.out.println("Voluntario no agregado email invalido");
            return  false;
        }
        if(phoneExists(phone_number)){
            System.out.println("Voluntario no agregado, El telefono de este voluntario ya se encuentra registrado");
            return false;
        }
        if(emailExists(email)){
            System.out.println("Voluntario no agregado, El email de este voluntario ya se encuentra registrado");
            return false;
        }
        if(name == null ){
            System.out.println("Voluntario no agregado, nombre invalido");
            return false;
        }
        if(date_birth == null){
            return false;
        }
        if(specialty == null)specialty = "";

        if(validateEmail(email)&&validate_phone_number(phone_number)){
            VolunteerEntity volunteerEntity = new VolunteerEntity(name,phone_number,email, date_birth, specialty);
            return this.volunteerDAO.create(volunteerEntity);
        }else{
            return false;
        }

    }

    public VolunteerEntity readVolunteer(int id){
        if(id <= 0){
            return null;
        }
        return this.volunteerDAO.readById(id);

    }

    public boolean updateVolunteer(int id, String name, String phone_number, String email, Date date_birth, String specialty){
        if(name == null){
            return false;
        }
        if(phone_number == null){
            return false;
        }
        if(email == null){
            return false;
        }
        if(date_birth == null){
            return false;
        }
        if(specialty == null)specialty = "";

        VolunteerEntity volunteerEntity = new VolunteerEntity(name,phone_number,email,date_birth,specialty);
        volunteerEntity.setId_volunteer(id);
        return this.volunteerDAO.update(volunteerEntity);
    }

    public boolean deleteVolunteer(int id) {
        if(id <= 0 ){
            return false;
        }
        return this.volunteerDAO.deleteById(id);
    }

    public List<VolunteerEntity> readAllVolunteers(){
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        String formato = "| %-5s | %-15s | %-15s | %-30s | %-19s | %-15s |";
        System.out.println(String.format(formato, "ID", "NOMBRE", "TELEFONO", "EMAIL", "FECHA DE NACIMIENTO", "ESPECIALIDAD"));
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        return this.volunteerDAO.readAll();

    }

    boolean validate_phone_number(String phone){
        String RegexTelefono = "^\\d{10}$";
        Pattern patternTelefonoPattern = Pattern.compile(RegexTelefono);

        Matcher matcherTelefono =  patternTelefonoPattern.matcher(phone);

        return matcherTelefono.matches();
    }

    boolean validateEmail(String email){
        // Expresion regular de Email
        String RegexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern  patternEmail = Pattern.compile(RegexEmail);

        // Verificacion de los datos con las expresiones regulares

        Matcher matcherEmail = patternEmail.matcher(email);

        return  matcherEmail.matches();
    }

    public boolean phoneExists(String phone){
        String sql = "SELECT COUNT(*) FROM voluntarios WHERE telefono = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1,phone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExists(String email){
        String sql = "SELECT COUNT(*) FROM voluntarios WHERE email = ?";
        try(
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1,email);
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
