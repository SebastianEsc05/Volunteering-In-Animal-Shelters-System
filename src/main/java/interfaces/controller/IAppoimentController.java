package interfaces.controller;

import controllers.ControllerException;
import dao.exceptions.PersistenceException;
import models.AnimalEntity;
import models.AppoimentEntity;

import java.time.LocalDate;
import java.util.List;

public interface IAppoimentController {

    boolean addAppoiment(LocalDate todayDate, LocalDate dateBooked, Integer animalId, int volunteerId, String activity, String comments, String status, boolean animalCheck) throws ControllerException;

    AppoimentEntity readAppoiment(int id) throws ControllerException;

    boolean updateAppoiment(int id, String comments, String status, LocalDate date_booked, LocalDate date_event, Integer id_animal, int id_volunteer, String activit) throws ControllerException;

    boolean deleteAppoiment(int id) throws ControllerException;

    List<AppoimentEntity> readAllAppoiments() throws ControllerException;

    List<AppoimentEntity> searchByState(Integer id, String estado) throws PersistenceException;
}
