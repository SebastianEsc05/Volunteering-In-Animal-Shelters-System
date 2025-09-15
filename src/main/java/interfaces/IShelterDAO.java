package interfaces;

import models.ShelterEntity;

import java.util.List;

public interface IShelterDAO {
    void create(ShelterEntity shelterEntity);
    ShelterEntity read(int id);
    void update(ShelterEntity shelterEntity);
    void delete(int id);
    List<ShelterEntity> readAll();
}
