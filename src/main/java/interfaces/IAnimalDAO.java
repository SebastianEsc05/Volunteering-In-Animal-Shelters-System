package interfaces;

import models.AnimalEntity;

import java.util.List;

public interface IAnimalDAO {
    void create(AnimalEntity animalEntity);
    AnimalEntity read(int id);
    void update(AnimalEntity animalEntity);
    void delete(int id);
    List<AnimalEntity> readAll();
}
