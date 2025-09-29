package views.main;

import controllers.*;
import interfaces.controller.IAnimalController;
import interfaces.controller.IAppointmentController;
import interfaces.controller.IShelterController;
import interfaces.controller.IVolunteerController;
import views.frames.LogInFrame;
import views.frames.MainFrame;
import structure.DatabaseInitializer;
import javax.naming.ldap.Control;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainPresentacion {
    public static void main(String[] args) throws SQLException {
        //LogInFrame logInFrame = new LogInFrame();
        System.out.println("\nCreando la base de datos y tablas correspondientes...");
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        IShelterController shelterController = new ShelterController();
        IAnimalController animalController = new AnimalController();
        IVolunteerController volunteerController = new VolunteerController();
        IAppointmentController appoimentController = new AppointmentController();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Date birthDate = Date.valueOf(LocalDate.parse("22-12-2001", formatter));
        LocalDate animalEntryDate = LocalDate.parse("11-09-2025", formatter);
        LocalDate appointmentBookingDate = LocalDate.parse("15-12-2025", formatter);

        System.out.println("\nInsertando datos de prueba...");
        if (volunteerController.readAllVolunteers().isEmpty()) {
            try {
                volunteerController.addVolunteer("Juan", "6441923147", "correo@hotmail.com", birthDate, "veteriniaria");
                System.out.println("Volntario insertado\n");
            } catch (ControllerException e) {
                System.out.printf("Error: " + e.getMessage());
            }
        }
        if (shelterController.readAllShelters().isEmpty()) {
            try {
                shelterController.addShelter("Refugio California", "Lucia Gomez", 25, "Cajeme");
                System.out.println("Refugio insertado\n");
            } catch (ControllerException e) {
                System.out.printf("Error: " + e.getMessage());
            }
        }
        if (animalController.readAllAnimals().isEmpty()) {
            try {
                animalController.addAnimal("Dash", 5, animalEntryDate, "Saludable", "perro", 1);
                System.out.println("Animal insertado\n");
            } catch (ControllerException e) {
                System.out.printf("Error: " + e.getMessage());
            }
        }
        if (appoimentController.readAllAppoiments().isEmpty()) {
            try {
                appoimentController.addAppoiment(LocalDate.now(), appointmentBookingDate, 1, 1, "Baño", "Baño y desparacitacion", "pendiente", true);
                System.out.println("Asignacion insertado\n");
            } catch (ControllerException e) {
                System.out.printf("Error: " + e.getMessage());
            }

        }

        MainFrame mainFrame = new MainFrame();
    }
}