package interfaces.controller;

import dao.exceptions.PersistenceException;
import models.VolunteerEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface IVolunteerController {

    boolean addVolunteer(String name, String phone_number, String email, Date date_birth, String specialty);

    VolunteerEntity readVolunteer(int idVolunteer);

    boolean updateVolunteer(int id, String name, String phone_number, String email, Date date_birth, String specialty);

    boolean deleteVolunteer(int idVolunteer);

    List<VolunteerEntity> readAllVolunteers();

    DefaultTableModel getVolunteerTable();

    DefaultTableModel getVooluntersByIdTable(int id);

}
