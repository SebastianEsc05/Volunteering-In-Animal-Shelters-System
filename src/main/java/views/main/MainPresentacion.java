package views.main;

import controllers.AnimalController;
import controllers.AppointmentController;
import controllers.ShelterController;
import controllers.VolunteerController;
import interfaces.controller.IAnimalController;
import interfaces.controller.IAppointmentController;
import interfaces.controller.IShelterController;
import interfaces.controller.IVolunteerController;
import views.frames.MainFrame;
import structure.DatabaseInitializer;
import java.sql.SQLException;

public class MainPresentacion {
    public static void main(String[] args) throws SQLException {
//        LogInFrame logInFrame = new LogInFrame();
        System.out.println("\nCreando la base de datos y tablas correspondientes...");
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        IShelterController shelterController = new ShelterController();
        IAnimalController animalController = new AnimalController();
        IVolunteerController volunteerController = new VolunteerController();
        IAppointmentController appoimentController = new AppointmentController();

        System.out.println("\nInsertando datos de prueba...\n");
        shelterController.insertShelters();
        animalController.insertAnimals();
        volunteerController.insertVolunteers();
        appoimentController.insertAppoiments();
        MainFrame mainFrame = new MainFrame();
    }
}