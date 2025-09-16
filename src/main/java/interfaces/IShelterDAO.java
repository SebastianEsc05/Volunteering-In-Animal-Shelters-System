package interfaces;

import models.ShelterEntity;

import java.util.List;

public interface IShelterDAO {
    boolean create(ShelterEntity shelterEntity);
    ShelterEntity read(int id);
    boolean update(ShelterEntity shelterEntity);
    boolean delete(int id);
    List<ShelterEntity> readAll();
}
