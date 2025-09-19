package org.example;

import controllers.AnimalController;
import controllers.AppoimentController;
import controllers.ShelterController;
import controllers.VolunteerController;
import models.AnimalEntity;
import models.AppoimentEntity;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
       System.out.println("-");

       VolunteerController volunteerController = new VolunteerController();
       volunteerController.readAllVolunteers();
//        volunteerController.addVolunteer("pepe", "6681118936", "Sebastianescram01@gmail.com", "2005-06-26", "Doctor");
//        volunteerController.addVolunteer("juanito", "6681119232", "sebaassss@gmail.com", "2005-06-26", "Doctor");
//        volunteerController.addVolunteer("pancho", "6681138222", "sdasdas@gmail.com", "2005-06-26", "Doctor");
//        volunteerController.readVolunteer(3);
//        volunteerController.deleteVolunteer(2);
//        volunteerController.updateVolunteer(3, "alfredo", "123123123", "mariano@gmail.com", "2005-06-26", "Doctor");
//        System.out.println("Lista de voluntarios: ");
//        volunteerController.readAllVolunteers();
//        System.out.println("-");
//        System.out.println("-");
//
//        ShelterController shelterController = new ShelterController();
//        shelterController.addShelter("Milagros", "Juan david", 1000, "Obregon");
//        shelterController.addShelter("Juanita", "Roberto L", 1000, "Mochis");
//        shelterController.addShelter("Pricila", "Juan Manuel", 1000, "Guasave");
//        shelterController.readShelter(1);
//        shelterController.deleteShelter(2);
//        shelterController.updateShelter(3, "Antonio", "Luis Miguel", 1500, "Guasave");
//        System.out.println("Lista de Refugios");
//        shelterController.readAllShelters();
//        System.out.println("-");
//        System.out.println("-");
//
//        AppoimentController appoimentController = new AppoimentController();
//        appoimentController.addAppoiment("el voluntario pasea por el parque al animal", "pendiente", "2025-07-11", null, null, 1, "pasear por el parque");
//        appoimentController.addAppoiment("el animal come", "pendiente", "2025-07-11", null, null, 1, "pasear por el parque");
//        appoimentController.addAppoiment("el voluntario come", "pendiente", "2025-07-11", null, null, 1, "pasear por el parque");
//        appoimentController.readAppoiment(1);
//        appoimentController.updateAppoiment(1, "el voluntario limpia el area", "pendiente", "2025-07-11", null, null, 1, "pasear por el parque");
//        appoimentController.deleteAppoiment(2);
//        appoimentController.readAllAppoiments();
//        System.out.println("-");
//        System.out.println("-");
//
//        AnimalController animalController = new AnimalController();
//        animalController.addAnimal("luna",12,"2025-06-26","grave","Perro",1);
//        animalController.addAnimal("maria",5,"2025-06-26","saludable","gato",1);
//        animalController.addAnimal("diego",2,"2025-06-26","critico","Perro",1);
//        animalController.readAnimal(1);
//        animalController.updateAnimal(1,"juanita",11,"2025-06-26","Recuperada","Perro",1);
//        animalController.deleteAnimal(2);
//        animalController.readAllAnimals();
//        System.out.println("-");
//        System.out.println("-");
    }
}