package org.example;

import controllers.ShelterController;
import controllers.VolunteerController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        VolunteerController volunteerController = new VolunteerController();
        volunteerController.addVolunteer("pepe", "6681118936", "Sebastianescram01@gmail.com", "2005-06-26", "Doctor");
        volunteerController.addVolunteer("juanito", "6681118936", "Sebastianescram01@gmail.com", "2005-06-26", "Doctor");
        volunteerController.addVolunteer("pancho", "6681118936", "Sebastianescram01@gmail.com", "2005-06-26", "Doctor");
        volunteerController.readVolunteer(2);
       // volunteerController.deleteVolunteer(1);
        volunteerController.updateVolunteer(2, "jose", "123456789", "Sebastianescram01@gmail.com", "2005-06-26", "Doctor");
        System.out.println("Lista de voluntarios: ");
        volunteerController.readAllVolunteers();
        System.out.println("-");
        System.out.println("-");

        ShelterController shelterController = new ShelterController();
        shelterController.addShelter("Milagros", "Juan david", 1000, "Obregon");
        shelterController.addShelter("Juanita", "Roberto L", 1000, "Mochis");
        shelterController.addShelter("Pricila", "Juan Manuel", 1000, "Guasave");
        shelterController.readShelter(1);
       // shelterController.deleteShelter(2);
        shelterController.updateShelter(3, "Antonio", "Luis Miguel", 1500, "Guasave");
        System.out.println("Lista de Refugios");
        shelterController.readAllShelters();







    }


}