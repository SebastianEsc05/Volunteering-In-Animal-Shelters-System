package interfaces.controller;

import dao.exceptions.PersistenceException;
import models.ShelterEntity;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IShelterController {

    void insertShelters() throws PersistenceException;

    boolean addShelter(String name, String responsible, int capacity, String location);

    ShelterEntity readShelter(int id);

    boolean updateShelter(int id, String name, String responsible, int capacity, String location);

    boolean deleteShelter(int id);

    List<ShelterEntity> readAllShelters();

    DefaultTableModel getShelterTable();

    DefaultTableModel getSheltersByIdTable(int id);

    DefaultTableModel getAnimalsByShelterIdTable(int id);

    void clieanUpTable();
}
