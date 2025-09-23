package controllers;

import config.ConexionDB;
import dao.AppoimentDAO;
import dao.exceptions.PersistenceException;
import interfaces.controller.IAppoimentController;
import interfaces.dao.IAppoimentDAO;
import models.AppoimentEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppoimentController implements IAppoimentController {

    private IAppoimentDAO appoimentDAO;

    public AppoimentController() {
        this.appoimentDAO = new AppoimentDAO();
    }

    @Override
    public void insertAppoiments() throws PersistenceException {
        appoimentDAO.insertAppoiments();
    }

    public boolean addAppoiment(LocalDateTime todayDate, LocalDateTime dateBooked, Integer animalId, int volunteerId, String activity, String comments, String status, boolean animalCheck) throws ControllerException {
        try {
            if (animalId != null) {
                if (animalId < 0 || !animalExists(animalId)) {
                    System.out.println("no se encontro registro del animal");
                    return false;
                }
            }
            if (volunteerId < 0 || !volunteerExists(volunteerId)) {
                System.out.println("no se encontró registro del voluntario");
                return false;
            }
            if (activity == null) {
                System.out.println("asegurese de proporcionar una actividad");
                return false;
            }
            if (comments == null || comments.trim().isEmpty()) comments = "";

            //Agregar la verificacion de que la fecha del evento no sea anterior a la fecha actual o que sea reservada para "domingo"

            AppoimentEntity appoimentEntity = new AppoimentEntity();
            appoimentEntity.setDateEvent(todayDate);
            appoimentEntity.setDateBooked(dateBooked);
            appoimentEntity.setIdAnimal(animalId);
            appoimentEntity.setIdVolunteer(volunteerId);
            appoimentEntity.setActivity(activity);
            appoimentEntity.setComments(comments);
            appoimentEntity.setStatus(status);
            appoimentEntity.setAnimalCheck(animalCheck);

            return this.appoimentDAO.create(appoimentEntity);

        } catch (PersistenceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    public AppoimentEntity readAppoiment(int id) throws ControllerException {
        if (id < 0) {
            return null;
        }
        return this.appoimentDAO.readById(id);
    }

    public boolean updateAppoiment(int id, String comments, String status, LocalDateTime date_booked, LocalDateTime date_event, Integer id_animal, int id_volunteer, String activity) throws ControllerException {
        if (id < 0) {
            return false;
        }
        if (comments == null || comments.trim().isEmpty() || status == null || date_booked == null || activity == null || activity.trim().isEmpty()) {
            return false;
        }
        if (id_animal != null && id_animal < 0) {
            return false;
        }
        if (id_volunteer < 0) {
            return false;
        }
        AppoimentEntity appoimentEntity = new AppoimentEntity();
        appoimentEntity.setId(id);
        appoimentEntity.setComments(comments);
        appoimentEntity.setStatus(status);
        appoimentEntity.setDateBooked(date_booked);
        appoimentEntity.setDateEvent(date_event);
        appoimentEntity.setIdAnimal(id_animal);
        appoimentEntity.setIdVolunteer(id_volunteer);
        appoimentEntity.setActivity(activity);
        return this.appoimentDAO.update(appoimentEntity);
    }

    public boolean deleteAppoiment(int id) throws ControllerException {
        if (id < 0) {
            return false;
        }
        return this.appoimentDAO.deleteById(id);
    }

    public List<AppoimentEntity> readAllAppoiments() throws ControllerException {
        return this.appoimentDAO.readAll();
    }

    public List<AppoimentEntity> searchByState(Integer id, String estado) throws PersistenceException {
        return this.appoimentDAO.searchByState(id, estado);
    }

    @Override
    public DefaultTableModel getAppoimentTable() {
        String[] columns = {"ID", "Fecha Realización", "Estado", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AppoimentEntity> appoimentList = appoimentDAO.readAll();
        for (AppoimentEntity a : appoimentList) {
            Object[] row = {
                    a.getId(),
                    a.getDateBooked().format(formatter),
                    a.getStatus()
            };
            model.addRow(row);
        }

        return model;
    }

    public boolean animalExists(int id) throws ControllerException {
        String sql = "SELECT COUNT(*) FROM animales WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean volunteerExists(int id) throws ControllerException {
        String sql = "SELECT COUNT(*) FROM voluntarios WHERE id = ?";
        try (
                Connection con = ConexionDB.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
