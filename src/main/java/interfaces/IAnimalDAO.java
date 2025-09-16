package interfaces;

import models.AnimalEntity;

import java.util.List;

public interface IAnimalDAO {
    boolean create(AnimalEntity animalEntity);
    AnimalEntity read(int id);
    boolean update(AnimalEntity animalEntity);
    boolean delete(int id);
    List<AnimalEntity> readAll();
}
