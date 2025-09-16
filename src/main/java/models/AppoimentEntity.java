package models;

public class AppoimentEntity {

    private int id;
    private String comments;
    private String status;
    private String date_booked;
    private String date_event;
    private Integer id_animal;
    private int id_volunteer;
    private String activity;

    public AppoimentEntity(){}

    public AppoimentEntity(int id, String comments, String status, String date_booked, String date_event, Integer id_animal, int id_volunteer, String activity) {
        this.comments = comments;
        this.status = status;
        this.date_booked = date_booked;
        this.date_event = date_event;
        this.id_animal = id_animal;
        this.id_volunteer = id_volunteer;
        this.activity = activity;
    }

    public AppoimentEntity(String comments, String status, String date_booked, String date_event, Integer id_animal, int id_volunteer, String activity) {
        this.comments = comments;
        this.status = status;
        this.date_booked = date_booked;
        this.date_event = date_event;
        this.id_animal = id_animal;
        this.id_volunteer = id_volunteer;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public int getId_volunteer() {
        return id_volunteer;
    }

    public void setId_volunteer(int id_volunteer) {
        this.id_volunteer = id_volunteer;
    }

    public String getActivity() {
        return activity;
    }

    public void set_activity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "AppoimentEntity{" +
                "id_appoiment=" + id +
                ", comments='" + comments + '\'' +
                ", status=" + status +
                ", date_booked='" + date_booked + '\'' +
                ", date_event='" + date_event + '\'' +
                ", id_animal=" + id_animal +
                ", id_volunteer=" + id_volunteer +
                ", activity=" + activity +
                '}';
    }
}
