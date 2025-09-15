package interfaces;

import models.ActivityEntity;

import java.util.List;

public interface IActivityDAO {
    void create(ActivityEntity activityEntity);
    ActivityEntity read(int id);
    void update(ActivityEntity activityEntity);
    void delete(int id);
    List<ActivityEntity> readAll();
}
