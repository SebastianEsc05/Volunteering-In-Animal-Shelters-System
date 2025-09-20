package presentation.main;

import controllers.ShelterController;
import controllers.VolunteerController;
import presentation.frames.LogInFrame;
import presentation.frames.MainFrame;
import structure.DatabaseInitializer;

import java.sql.SQLException;

public class MainPresentacion {
    public static void main(String[] args) throws SQLException {
//        LogInFrame logInFrame = new LogInFrame();
        System.out.println("\nCreando la base de datos y tablas correspondientes...");
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        ShelterController shelterController = new ShelterController();
        VolunteerController volunteerController = new VolunteerController();

        System.out.println("\nInsertando datos de prueba...\n");
        shelterController.insertShelters();
        volunteerController.insertVolunteers();
        MainFrame mainFrame = new MainFrame();
    }
}