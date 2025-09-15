package dao;

import interfaces.IActivityDAO;
import models.ActivityEntity;

import java.util.List;

public class ActivityDAO implements IActivityDAO {

    @Override
    public void create(ActivityEntity activityEntity) {
        String sql = "INSERT INTO actividades (id_animal, nombre_animal, edad, fecha_ingreso, estado_salud, id_especie, id_refugio) " +
                "VALUES(?,?,?,?,?,?,?);";
    }

    @Override
    public ActivityEntity read(int id) {
        return null;
    }

    @Override
    public void update(ActivityEntity activityEntity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<ActivityEntity> readAll() {
        return List.of();
    }
}
