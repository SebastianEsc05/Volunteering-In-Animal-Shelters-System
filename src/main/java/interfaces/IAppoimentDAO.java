package interfaces;

import models.AppoimentEntity;

import java.util.List;

public interface IAppoimentDAO {
    void create(AppoimentEntity appoimentEntity);
    AppoimentEntity read(int id);
    void update(AppoimentEntity appoimentEntity);
    void delete(int id);
    List<AppoimentEntity> readAll();
}
