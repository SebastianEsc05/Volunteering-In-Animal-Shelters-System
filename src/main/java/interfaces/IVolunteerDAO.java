package interfaces;

import models.VolunteerEntity;

import java.util.List;

public interface IVolunteerDAO {
    void create(VolunteerEntity volunteerEntity);
    VolunteerEntity read(int id);
    void update(VolunteerEntity volunteerEntity);
    void delete(int id);
    List<VolunteerEntity> readAll();
}
