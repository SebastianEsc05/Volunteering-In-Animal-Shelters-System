package controllers;

import dao.ShelterDAO;
import interfaces.controller.IShelterController;
import interfaces.dao.IShelterDAO;
import models.ShelterEntity;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShelterController implements IShelterController {

    private IShelterDAO shelterDAO;

    public ShelterController(){
        this.shelterDAO = new ShelterDAO();
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
        ShelterEntity shelterEntity = new ShelterEntity();
        shelterEntity.setNameShelter(name);
        shelterEntity.setResponsible(responsible);
        shelterEntity.setAnimalCount(0);
        shelterEntity.setCapacity(capacity);
        shelterEntity.setLocation(location);
        return this.shelterDAO.create(shelterEntity);
    }

    public ShelterEntity readShelter(int id){
        if(id < 0){
            return null;
        }
        return this.shelterDAO.readById(id);
    }

    public boolean updateShelter(int id, String name, String responsible, int capacity, String location){
        if(id < 0 || capacity < 0){
            return false;
        }
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        if(responsible == null) responsible = "";
        if(location == null)location = "";

        ShelterEntity shelterEntity = new ShelterEntity();
        shelterEntity.setNameShelter(name);
        shelterEntity.setResponsible(responsible);
        shelterEntity.setAnimalCount(0);
        shelterEntity.setCapacity(capacity);
        shelterEntity.setLocation(location);
        shelterEntity.setIdShelter(id);
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
    public DefaultTableModel getShelterTable() {
        String[] columns = {"Id", "Nombre", "Responsable", "Animales", "Cap. Maxima", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<ShelterEntity> shelterList = shelterDAO.readAll();
        for (ShelterEntity s : shelterList) {
            Object[] row = {
                    s.getIdShelter(),
                    s.getNameShelter(),
                    s.getResponsible(),
                    s.getAnimalCount(),
                    s.getCapacity()
            };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public void clieanUpTable() {
        this.shelterDAO.clieanUpTable();
    }

}
