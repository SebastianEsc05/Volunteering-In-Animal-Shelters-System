package controllers;

import dao.AnimalDAO;
import interfaces.IAnimalDAO;
import models.AnimalEntity;
import models.VolunteerEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalController {

    public IAnimalDAO animalDAO;

    public AnimalController(){
        this.animalDAO = new AnimalDAO();
    }

    public boolean addAnimal(String name, int age, String date_entry, String health_situation, String specie, int id_shelter){
        if(!checkStatus(health_situation)){
            health_situation = null;
        }
        if(name == null || name.trim().isEmpty() || date_entry == null || health_situation == null || specie == null){
            return false;
        }
        if(id_shelter < 0 ){
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

    public boolean deleteAnimal(int id){
        if(id <= 0){
            return false;
        }
        return this.animalDAO.delete(id);
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

    public List<AnimalEntity> readAllAnimals(){
        return this.animalDAO.readAll();
    }

    boolean checkStatus(String value){
        Pattern p = Pattern.compile("^(?:saludable|grave|critico)$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        return m.matches();
    }


}
