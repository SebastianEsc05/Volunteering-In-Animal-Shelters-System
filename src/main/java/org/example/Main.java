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
        ShelterController shelterController = new ShelterController();

        System.out.println("\nCreando tabla shelters...");
        shelterController.createTableShelters();

        System.out.println("\nPoblando tabla shelters...\n");
        shelterController.insertShelters();
        //System.out.println("Limpiando tabla shelters...\n");
        //shelterController.clieanUpTable();

    }
}