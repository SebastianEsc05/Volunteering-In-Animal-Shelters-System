package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AnimalEntity;

import java.util.List;

public interface IAnimalDAO {

    void insertAnimals() throws PersistenceException;

    boolean create(AnimalEntity animalEntity) throws PersistenceException;

    AnimalEntity readById(int id) throws PersistenceException;

    AnimalEntity readByHealthSituation(String healthSituation) throws PersistenceException;

    boolean update(AnimalEntity animalEntity) throws PersistenceException;

    boolean deleteById(int id) throws PersistenceException;

    List<AnimalEntity> readAll() throws PersistenceException;

    boolean isNotEmpty();
}
