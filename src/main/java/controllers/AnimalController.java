package controllers;

import config.ConexionDB;
import dao.AnimalDAO;
import interfaces.controller.IAnimalController;
import interfaces.dao.IAnimalDAO;
import models.AnimalEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalController implements IAnimalController {

    public IAnimalDAO animalDAO;

    public AnimalController(){
        this.animalDAO = new AnimalDAO();
    }

    public boolean addAnimal(String name, int age, String date_entry, String health_situation, String specie, int id_shelter){
        if(!checkStatus(health_situation)){
            System.out.println("estado de salud no valido");
            return false;
        }
        if(name == null ){
            System.out.println("El nombre no puede ser nulo");
            return false;
        }
        if(date_entry == null) {
            System.out.println("la fecha no puede estar vacia");
            return false;
        }
        if(specie == null) {
            System.out.println("la especie no puede estar vacia");
            return false;
        }

        if(!shelterExists(id_shelter)) {
            System.out.println("no se ha encontrado el refugio");
            return false;
        }
        AnimalEntity animalEntity = new AnimalEntity(name, age, date_entry, health_situation, specie, id_shelter);
        return this.animalDAO.create(animalEntity);
    }

    public AnimalEntity readAnimal(int id ){
        if(id <= 0){
            return null;
        }
        return this.animalDAO.read(id);

    }

    public boolean updateAnimal(int id, String name, int age, String date_entry, String health_situation, String specie, int id_shelter){
        if(name == null || name.trim().isEmpty() || date_entry == null || health_situation == null || specie == null){
            return false;
        }
        if(id_shelter < 0 ){
            return false;
        }
        AnimalEntity animalEntity = new AnimalEntity(name, age, date_entry, health_situation, specie, id_shelter);
        animalEntity.setId(id);
        return this.animalDAO.update(animalEntity);
    }

    public boolean deleteAnimal(int id){
        if(id <= 0){
            return false;
        }
        return this.animalDAO.delete(id);
    }

    public List<AnimalEntity> readAllAnimals(){
        return this.animalDAO.readAll();
    }

    boolean checkStatus(String value){
        Pattern p = Pattern.compile("^(?:saludable|grave|critico)$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        return m.matches();
    }

    public boolean shelterExists(int id){
        String sql = "SELECT COUNT(*) FROM refugios WHERE id = ?";
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
