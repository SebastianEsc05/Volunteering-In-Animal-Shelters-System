package interfaces.controller;

import dao.exceptions.PersistenceException;
import models.AnimalEntity;
import models.AppoimentEntity;

import java.time.LocalDate;
import java.util.List;

public interface IAppoimentController {

    boolean addAppoiment(String comments, String status, LocalDate date_booked, LocalDate date_event, Integer id_animal, int id_volunteer, String activity);

    AppoimentEntity readAppoiment(int id);

    boolean updateAppoiment(int id, String comments, String status, LocalDate date_booked, LocalDate date_event, Integer id_animal, int id_volunteer, String activit);

    boolean deleteAppoiment(int id);

    List<AppoimentEntity> readAllAppoiments();
}
