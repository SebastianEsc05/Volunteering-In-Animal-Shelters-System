package models;

public class ActivityEntity {
    private String id_activity;
    private String name_activity;
    private String description;

    public ActivityEntity(){}

    public ActivityEntity(String id_activity, String name_activity, String description) {
        this.id_activity = id_activity;
        this.name_activity = name_activity;
        this.description = description;
    }

    public String getId_activity() {
        return id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public String getName_activity() {
        return name_activity;
    }

    public void setName_activity(String name_activity) {
        this.name_activity = name_activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "id_activity='" + id_activity + '\'' +
                ", name_activity='" + name_activity + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
