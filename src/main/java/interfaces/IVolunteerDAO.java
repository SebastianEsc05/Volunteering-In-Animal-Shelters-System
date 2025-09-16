package interfaces;

import models.VolunteerEntity;

import java.sql.SQLException;
import java.util.List;

public interface IVolunteerDAO {
    boolean create(VolunteerEntity volunteerEntity) throws SQLException;
    VolunteerEntity read(int id) throws SQLException;
    boolean update(VolunteerEntity volunteerEntity);
    boolean delete(int id);
    List<VolunteerEntity> readAll();
}
