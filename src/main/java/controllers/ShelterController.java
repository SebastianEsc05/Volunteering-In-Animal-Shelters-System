package controllers;

import dao.ShelterDAO;
import interfaces.IShelterDAO;
import models.ShelterEntity;

import java.util.List;

public class ShelterController {

    private IShelterDAO shelterDAO;

    public ShelterController(){
        this.shelterDAO = new ShelterDAO();
    }

    public boolean addShelter(String name, String responsible, int capacity, String location){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        if(responsible == null)responsible = "";
        if(capacity < 0){
            return false;
        }
        if(location == null) location = "";
        ShelterEntity shelterEntity = new ShelterEntity(name,responsible,capacity,location);
        return this.shelterDAO.create(shelterEntity);
    }

    public ShelterEntity readShelter(int id){
        if(id < 0){
            return null;
        }
        return this.shelterDAO.read(id);
    }

    public boolean deleteShelter(int id){
        if(id < 0){
            return false;
        }
        return this.shelterDAO.delete(id);
    }

    public boolean updateShelter(int id, String name, String responible, int capaciy, String location){
        if(id < 0 || capaciy < 0){
            return false;
        }
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        if(responible == null) responible = "";
        if(location == null)location = "";
        ShelterEntity shelterEntity = new ShelterEntity(name,responible,capaciy,location);
        return this.shelterDAO.update(shelterEntity);
    }

    public List<ShelterEntity> readAllShelters(){
        return this.shelterDAO.readAll();
    }

}
