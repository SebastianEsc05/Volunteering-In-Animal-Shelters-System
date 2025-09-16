package models;

public class ShelterEntity {
    private int id_shelter;
    private String name_shelter;
    private String responsible;
    private int capacity;
    private String location;

    public ShelterEntity(){}

    public ShelterEntity(int id_shelter,String name_shelter, String responsible, int capacity, String location) {
        this.id_shelter = id_shelter;
        this.name_shelter = name_shelter;
        this.responsible = responsible;
        this.capacity = capacity;
        this.location = location;
    }

    public ShelterEntity(String name_shelter, String responsible, int capacity, String location) {
        this.name_shelter = name_shelter;
        this.responsible = responsible;
        this.capacity = capacity;
        this.location = location;
    }


    public int getId_shelter() {
        return id_shelter;
    }

    public void setId_shelter(int id_shelter) {
        this.id_shelter = id_shelter;
    }

    public String getName_shelter() {
        return name_shelter;
    }

    public void setName_shelter(String name_shelter) {
        this.name_shelter = name_shelter;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ShelterEntity{" +
                "id_shelter=" + id_shelter +
                ", name_shelter='" + name_shelter + '\'' +
                ", responsible='" + responsible + '\'' +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                '}';
    }
}
