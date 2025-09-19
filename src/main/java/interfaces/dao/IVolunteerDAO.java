package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.VolunteerEntity;

import java.util.List;

public interface IVolunteerDAO {

    boolean create(VolunteerEntity volunteerEntity) throws PersistenceException;

    VolunteerEntity read(int id) throws PersistenceException;

    boolean update(VolunteerEntity volunteerEntity) throws PersistenceException;

    boolean delete(int id) throws PersistenceException;

    List<VolunteerEntity> readAll() throws PersistenceException;
}
