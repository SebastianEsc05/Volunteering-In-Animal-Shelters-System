package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AppointmentEntity;

import java.util.List;

public interface IAppointmentDAO {

    void insertAppoiments() throws PersistenceException;


    boolean create(AppointmentEntity appoimentEntity) throws PersistenceException;

    AppointmentEntity readById(int id) throws PersistenceException;

    boolean update(AppointmentEntity appoimentEntity) throws PersistenceException;


    boolean deleteById(int id) throws PersistenceException;

    List<AppointmentEntity> readAll() throws PersistenceException;

    List<AppointmentEntity> searchByState(Integer id, String estado) throws PersistenceException;


    boolean isNotEmpty();

    List<AppointmentEntity>  getAppoimentsByStatusPending();

    List<AppointmentEntity>  getAppoimentsByStatusCanceled();

    List<AppointmentEntity>  getAppoimentsByStatusCompleted();

    List<AppointmentEntity>  getAppoimentsById(int id);

}
