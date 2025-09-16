package interfaces;

import models.AppoimentEntity;

import java.util.List;

public interface IAppoimentDAO {
    boolean create(AppoimentEntity appoimentEntity);
    AppoimentEntity read(int id);
    boolean update(AppoimentEntity appoimentEntity);
    boolean delete(int id);
    List<AppoimentEntity> readAll();
}
