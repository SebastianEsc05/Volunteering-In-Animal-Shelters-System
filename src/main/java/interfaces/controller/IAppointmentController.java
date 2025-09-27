package interfaces.controller;

import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import models.AppointmentEntity;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentController {

    void insertAppoiments() throws PersistenceException;

    boolean addAppoiment(LocalDateTime  todayDate, LocalDateTime dateBooked, Integer animalId, int volunteerId, String activity, String comments, String status, boolean animalCheck) throws ControllerException;

    AppointmentEntity readAppoiment(int id) throws ControllerException;

    boolean updateAppoiment(int id, String comments, String status, LocalDateTime date_booked, LocalDateTime  date_event, Integer id_animal, int id_volunteer, String activit) throws ControllerException;

    boolean deleteAppoiment(int id) throws ControllerException;

    List<AppointmentEntity> readAllAppoiments() throws ControllerException;

    List<AppointmentEntity> searchByState(Integer id, String estado) throws PersistenceException;

    DefaultTableModel getAppointmentTable();

    DefaultTableModel getAppointmentByStatusPendingTable();

    DefaultTableModel getAppointmentByStatusCanceledTable();

    DefaultTableModel getAppointmentByStatusCompletedTable();
}
