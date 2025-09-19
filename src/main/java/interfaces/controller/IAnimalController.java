package interfaces.controller;

import dao.exceptions.PersistenceException;
import models.AnimalEntity;

import java.util.List;

public interface IAnimalController {

    boolean addAnimal(String name, int age, String date_entry, String health_situation, String specie, int id_shelter);

    AnimalEntity readAnimal(int id) throws PersistenceException;

    boolean updateAnimal(int id, String name, int age, String date_entry, String health_situation, String specie, int id_shelter) throws PersistenceException;

    boolean deleteAnimal(int id) throws PersistenceException;

    List<AnimalEntity> readAllAnimals() throws PersistenceException;

}
