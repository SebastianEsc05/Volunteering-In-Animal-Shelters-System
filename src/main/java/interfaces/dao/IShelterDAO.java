package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AnimalEntity;
import models.ShelterEntity;

import java.util.List;

public interface IShelterDAO {

    boolean create(ShelterEntity shelterEntity) throws PersistenceException;

    ShelterEntity readById(int id) throws PersistenceException;

    boolean update(ShelterEntity shelterEntity) throws PersistenceException;

    boolean deleteById(int id) throws PersistenceException;

    List<ShelterEntity> readAll() throws PersistenceException;

    void cleanUpTable() throws PersistenceException;

    List<ShelterEntity>  getSheltersById(int id);

    boolean isNotEmpty() throws PersistenceException;

    List<AnimalEntity> getAnimals(int idRefugio) throws PersistenceException;
}
