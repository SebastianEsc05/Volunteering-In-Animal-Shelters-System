package controllers;

import dao.ShelterDAO;
import dao.exceptions.PersistenceException;
import interfaces.controller.IShelterController;
import interfaces.dao.IShelterDAO;
import models.ShelterEntity;

import java.util.List;

public class ShelterController implements IShelterController {

    private IShelterDAO shelterDAO;

    public ShelterController(){
        this.shelterDAO = new ShelterDAO();
    }

    @Override
    public void createTableShelters(){
        this.shelterDAO.createTableShelters();
    }

    @Override
    public void insertShelters(){
        this.shelterDAO.insertShelters();
    }

    public boolean addShelter(String name, String responsible, int capacity, String location){
        if(name == null ){
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
        return this.shelterDAO.readById(id);
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
        shelterEntity.setId_shelter(id);
        return this.shelterDAO.update(shelterEntity);
    }

    public boolean deleteShelter(int id){
        if(id < 0){
            return false;
        }
        return this.shelterDAO.deleteById(id);
    }

    public List<ShelterEntity> readAllShelters(){
        return this.shelterDAO.readAll();
    }

    @Override
    public void clieanUpTable() {
        this.shelterDAO.clieanUpTable();
    }

}
