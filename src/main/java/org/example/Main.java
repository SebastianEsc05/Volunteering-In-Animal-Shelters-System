package org.example;

import controllers.AnimalController;
import controllers.AppoimentController;
import controllers.ShelterController;
import controllers.VolunteerController;
import models.AnimalEntity;
import models.AppoimentEntity;
import structure.DatabaseInitializer;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.println("\nCreando la base de datos y tablas correspondientes...");
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        ShelterController shelterController = new ShelterController();

    }
}