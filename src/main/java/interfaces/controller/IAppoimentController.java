package interfaces.controller;

import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import models.AnimalEntity;
import models.AppoimentEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppoimentController {

    void insertAppoiments() throws PersistenceException;

    boolean addAppoiment(LocalDateTime  todayDate, LocalDateTime dateBooked, Integer animalId, int volunteerId, String activity, String comments, String status, boolean animalCheck) throws ControllerException;

    AppoimentEntity readAppoiment(int id) throws ControllerException;

    boolean updateAppoiment(int id, String comments, String status, LocalDateTime date_booked, LocalDateTime  date_event, Integer id_animal, int id_volunteer, String activit) throws ControllerException;

    boolean deleteAppoiment(int id) throws ControllerException;

    List<AppoimentEntity> readAllAppoiments() throws ControllerException;

    List<AppoimentEntity> searchByState(Integer id, String estado) throws PersistenceException;
}
