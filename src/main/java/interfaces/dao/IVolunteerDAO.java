package interfaces.dao;

import dao.exceptions.PersistenceException;
import models.AppointmentEntity;
import models.VolunteerEntity;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IVolunteerDAO {

    boolean create(VolunteerEntity volunteerEntity) throws PersistenceException;

    VolunteerEntity readById(int id) throws PersistenceException;

    boolean update(VolunteerEntity volunteerEntity) throws PersistenceException;

    boolean deleteById(int id) throws PersistenceException;

    List<VolunteerEntity> readAll() throws PersistenceException;

    List<VolunteerEntity> getVooluntersByIdTable(int id);

    List<AppointmentEntity> getAppointmentsByVolunteerId(int volunteerId) throws PersistenceException;

    boolean hasAppointments(int id);

    boolean isNotEmpty();
}
