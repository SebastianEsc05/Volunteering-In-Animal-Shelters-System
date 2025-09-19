package interfaces.controller;

import models.ShelterEntity;

import java.util.List;

public interface IShelterController {

    boolean addShelter(String name, String responsible, int capacity, String location);

    ShelterEntity readShelter(int id);

    boolean updateShelter(int id, String name, String responible, int capaciy, String location);

    boolean deleteShelter(int id);

    List<ShelterEntity> readAllShelters();
}
