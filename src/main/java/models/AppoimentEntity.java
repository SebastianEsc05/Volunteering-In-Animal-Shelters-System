package models;

public class AppoimentEntity {
    public enum Status {Completado, Pendiente, Cancelada}
    private int id_appoiment;
    private String comments;
    private Status status;
    private String date_booked;
    private String date_event;
    private int id_animal;
    private int id_volunteer;
    private int id_activity;

    public AppoimentEntity(int id_appoiment, String comments, Status status, String date_booked, String date_event, int id_animal, int id_volunteer, int id_activity) {
        this.id_appoiment = id_appoiment;
        this.comments = comments;
        this.status = status;
        this.date_booked = date_booked;
        this.date_event = date_event;
        this.id_animal = id_animal;
        this.id_volunteer = id_volunteer;
        this.id_activity = id_activity;
    }

    public int getId_appoiment() {
        return id_appoiment;
    }

    public void setId_appoiment(int id_appoiment) {
        this.id_appoiment = id_appoiment;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDate_booked() {
        return date_booked;
    }

    public void setDate_booked(String date_booked) {
        this.date_booked = date_booked;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_volunteer() {
        return id_volunteer;
    }

    public void setId_volunteer(int id_volunteer) {
        this.id_volunteer = id_volunteer;
    }

    public int getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    @Override
    public String toString() {
        return "AppoimentEntity{" +
                "id_appoiment=" + id_appoiment +
                ", comments='" + comments + '\'' +
                ", status=" + status +
                ", date_booked='" + date_booked + '\'' +
                ", date_event='" + date_event + '\'' +
                ", id_animal=" + id_animal +
                ", id_volunteer=" + id_volunteer +
                ", id_activity=" + id_activity +
                '}';
    }
}
