package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AppoimentEntity;

import java.util.List;

public interface IAppoimentDAO {
    boolean create(AppoimentEntity appoimentEntity) throws PersistenceException;
    AppoimentEntity read(int id) throws PersistenceException;
    boolean update(AppoimentEntity appoimentEntity) throws PersistenceException;
    boolean delete(int id) throws PersistenceException;
    List<AppoimentEntity> readAll() throws PersistenceException;
}
