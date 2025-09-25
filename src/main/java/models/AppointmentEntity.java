package models;

import java.time.LocalDateTime;

public class AppointmentEntity {

    private int id;
    private LocalDateTime dateEvent;
    private LocalDateTime dateBooked;
    private Integer idAnimal;
    private int idVolunteer;
    private String activity;
    private String comments;
    private String status;
    private boolean animalCheck;

    public AppointmentEntity() {
    }

    public AppointmentEntity(int id, LocalDateTime dateEvent, LocalDateTime dateBooked, Integer idAnimal, int idVolunteer, String activity, String comments, String status, boolean animalCheck) {
        this.id = id;
        this.dateEvent = dateEvent;
        this.dateBooked = dateBooked;
        this.idAnimal = idAnimal;
        this.idVolunteer = idVolunteer;
        this.activity = activity;
        this.comments = comments;
        this.status = status;
        this.animalCheck = animalCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
    }

    public LocalDateTime getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDateTime dateBooked) {
        this.dateBooked = dateBooked;
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVolunteer() {
        return idVolunteer;
    }

    public void setIdVolunteer(int idVolunteer) {
        this.idVolunteer = idVolunteer;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAnimalCheck() {
        return animalCheck;
    }

    public void setAnimalCheck(boolean animalCheck) {
        this.animalCheck = animalCheck;
    }

    @Override
    public String toString() {
//        return String.format(formato, getId(), getComments(), getStatus(), getDate_booked(), getDate_event(), getId_animal(), getId_volunteer(), getActivity());
        String formatTS = "| %-5d | %-15s | %-15s | %-15s | %-15s | %-5d | %-5d | %-15s |";
        return "AppoimentEntity{" +
                "id=" + id +
                ", dateEvent=" + dateEvent +
                ", dateBooked=" + dateBooked +
                ", idAnimal=" + idAnimal +
                ", idVolunteer=" + idVolunteer +
                ", activity='" + activity + '\'' +
                ", comments='" + comments + '\'' +
                ", status='" + status + '\'' +
                ", animalCheck=" + animalCheck +
                '}';
    }

}
