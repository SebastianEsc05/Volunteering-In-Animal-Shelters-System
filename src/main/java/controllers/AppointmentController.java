package controllers;

import config.ConexionDB;
import dao.AppointmentDAO;
import dao.exceptions.PersistenceException;
import interfaces.controller.IAppointmentController;
import interfaces.dao.IAppointmentDAO;
import models.AppointmentEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentController implements IAppointmentController {

    private IAppointmentDAO appoimentDAO;

    public AppointmentController() {
        this.appoimentDAO = new AppointmentDAO();
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

            AppointmentEntity appointmentEntity = new AppointmentEntity();
            appointmentEntity.setDateEvent(todayDate);
            appointmentEntity.setDateBooked(dateBooked);
            appointmentEntity.setIdAnimal(animalId);
            appointmentEntity.setIdVolunteer(volunteerId);
            appointmentEntity.setActivity(activity);
            appointmentEntity.setComments(comments);
            appointmentEntity.setStatus(status);
            appointmentEntity.setAnimalCheck(animalCheck);

            return this.appoimentDAO.create(appointmentEntity);

        } catch (PersistenceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    public AppointmentEntity readAppoiment(int id) throws ControllerException {
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
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(id);
        appointmentEntity.setComments(comments);
        appointmentEntity.setStatus(status);
        appointmentEntity.setDateBooked(date_booked);
        appointmentEntity.setDateEvent(date_event);
        appointmentEntity.setIdAnimal(id_animal);
        appointmentEntity.setIdVolunteer(id_volunteer);
        appointmentEntity.setActivity(activity);
        return this.appoimentDAO.update(appointmentEntity);
    }

    public boolean deleteAppoiment(int id) throws ControllerException {
        if (id < 0) {
            return false;
        }
        return this.appoimentDAO.deleteById(id);
    }

    public List<AppointmentEntity> readAllAppoiments() throws ControllerException {
        return this.appoimentDAO.readAll();
    }

    public List<AppointmentEntity> searchByState(Integer id, String estado) throws PersistenceException {
        return this.appoimentDAO.searchByState(id, estado);
    }

    @Override
    public DefaultTableModel getAppoimentTable() {
        String[] columns = {"Id", "Fecha programada", "Estado", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AppointmentEntity> appoimentList = appoimentDAO.readAll();
        for (AppointmentEntity a : appoimentList) {
            Object[] row = {
                    a.getId(),
                    a.getDateBooked().format(formatter),
                    a.getStatus()
            };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public DefaultTableModel getAppoimentByStatusPendingTable() {
        String[] columns = {"Id", "Fecha programada", "Estado", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AppointmentEntity> appoimentList = appoimentDAO.getAppoimentsByStatusPending();
        for (AppointmentEntity a : appoimentList) {
            Object[] row = {
                    a.getId(),
                    a.getDateBooked().format(formatter),
                    a.getStatus()
            };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public DefaultTableModel getAppoimentByStatusCanceledTable() {
        String[] columns = {"Id", "Fecha programada", "Estado", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AppointmentEntity> appoimentList = appoimentDAO.getAppoimentsByStatusCanceled();
        for (AppointmentEntity a : appoimentList) {
            Object[] row = {
                    a.getId(),
                    a.getDateBooked().format(formatter),
                    a.getStatus()
            };
            model.addRow(row);
        }

        return model;
    }

    @Override
    public DefaultTableModel getAppoimentByStatusCompletedTable() {
        String[] columns = {"Id", "Fecha programada", "Estado", "Ver"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<AppointmentEntity> appoimentList = appoimentDAO.getAppoimentsByStatusCompleted();
        for (AppointmentEntity a : appoimentList) {
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
