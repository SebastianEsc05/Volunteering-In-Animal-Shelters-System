package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AnimalEntity;

import java.util.List;

public interface IAnimalDAO {
    boolean create(AnimalEntity animalEntity) throws PersistenceException;
    AnimalEntity read(int id) throws PersistenceException;
    boolean update(AnimalEntity animalEntity) throws PersistenceException;
    boolean delete(int id) throws PersistenceException;
    List<AnimalEntity> readAll() throws PersistenceException;
}
