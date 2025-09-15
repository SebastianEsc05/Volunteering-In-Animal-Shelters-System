package interfaces;

import models.SpecieEntity;

import java.util.List;

public interface ISpecialtyDAO {
    void cretae(SpecieEntity specieEntity);
    SpecieEntity read(int id);
    void update(SpecieEntity specieEntity);
    void delete(int id);
    List<SpecieEntity> readAll();
}
