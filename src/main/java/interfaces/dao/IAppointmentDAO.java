package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AppointmentEntity;

import java.util.List;

public interface IAppointmentDAO {

    void insertAppointments() throws PersistenceException;

    boolean create(AppointmentEntity appoimentEntity) throws PersistenceException;

    AppointmentEntity readById(int id) throws PersistenceException;

    boolean update(AppointmentEntity appoimentEntity) throws PersistenceException;

    boolean deleteById(int id) throws PersistenceException;

    List<AppointmentEntity> readAll() throws PersistenceException;

    List<AppointmentEntity> searchByState(Integer id, String estado) throws PersistenceException;

    List<AppointmentEntity>  getAppointmentsByStatusPending() throws PersistenceException;

    List<AppointmentEntity> getAppointmentsByStatusCanceled() throws PersistenceException;

    List<AppointmentEntity> getAppointmentsByStatusCompleted() throws PersistenceException;

    List<AppointmentEntity> getAppointmentsById(int id);

    boolean isNotEmpty() throws PersistenceException;
}
