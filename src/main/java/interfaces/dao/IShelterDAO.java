package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.ShelterEntity;

import java.util.List;

public interface IShelterDAO {
    boolean create(ShelterEntity shelterEntity) throws PersistenceException;
    ShelterEntity read(int id) throws PersistenceException;
    boolean update(ShelterEntity shelterEntity) throws PersistenceException;
    boolean delete(int id) throws PersistenceException;
    List<ShelterEntity> readAll() throws PersistenceException;
}
