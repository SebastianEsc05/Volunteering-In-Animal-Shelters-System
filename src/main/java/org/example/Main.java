package org.example;

import controllers.VolunteerController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        VolunteerController volunteerController = new VolunteerController();
        volunteerController.addVolunteer("pepe","6681118936","Sebastianescram01@gmail.com","2005-06-26","Doctor");

    }


}