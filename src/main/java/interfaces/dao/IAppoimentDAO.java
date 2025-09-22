package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AppoimentEntity;

import java.util.List;

public interface IAppoimentDAO {

    void insertAppoiments() throws PersistenceException;

    boolean create(AppoimentEntity appoimentEntity) throws PersistenceException;

    AppoimentEntity readById(int id) throws PersistenceException;

    boolean update(AppoimentEntity appoimentEntity) throws PersistenceException;

    boolean deleteById(int id) throws PersistenceException;

    List<AppoimentEntity> readAll() throws PersistenceException;

    List<AppoimentEntity> searchByState(Integer id, String estado) throws PersistenceException;

    boolean isNotEmpty();
}
