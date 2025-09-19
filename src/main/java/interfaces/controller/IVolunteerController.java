package interfaces.controller;

import dao.exceptions.PersistenceException;
import models.VolunteerEntity;

import java.util.List;

public interface IVolunteerController {

    boolean addVolunteer(String name, String phone_number, String email, String date_birth, String specialty);

    VolunteerEntity readVolunteer(int idVolunteer);

    boolean updateVolunteer(int id, String name, String phone_number, String email, String date_birth, String specialty);

    boolean deleteVolunteer(int idVolunteer);

    List<VolunteerEntity> readAllVolunteers();
}
