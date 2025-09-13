package models;

public class SpecieEntity {
    private int id_spicie;
    private String name_spicie;
    private String description;

    public SpecieEntity(){}

    public SpecieEntity(int id_spicie, String name_spicie, String description) {
        this.id_spicie = id_spicie;
        this.name_spicie = name_spicie;
        this.description = description;
    }

    public int getId_spicie() {
        return id_spicie;
    }

    public void setId_spicie(int id_spicie) {
        this.id_spicie = id_spicie;
    }

    public String getName_spicie() {
        return name_spicie;
    }

    public void setName_spicie(String name_spicie) {
        this.name_spicie = name_spicie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SpecieEntity{" +
                "id_spicie=" + id_spicie +
                ", name_spicie='" + name_spicie + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
