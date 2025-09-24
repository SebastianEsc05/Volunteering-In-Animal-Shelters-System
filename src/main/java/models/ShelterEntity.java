package models;

public class ShelterEntity {
    private int idShelter;
    private String nameShelter;
    private String responsible;
    private int animalCount;
    private int capacity;
    private String location;

    public ShelterEntity(){}

    public ShelterEntity(int idShelter, String nameShelter, String responsible, int animalCount, int capacity, String location) {
        this.idShelter = idShelter;
        this.nameShelter = nameShelter;
        this.responsible = responsible;
        this.animalCount = animalCount;
        this.capacity = capacity;
        this.location = location;
    }

    public int getIdShelter() {
        return idShelter;
    }

    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    public String getNameShelter() {
        return nameShelter;
    }

    public void setNameShelter(String nameShelter) {
        this.nameShelter = nameShelter;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getAnimalCount() {
        return animalCount;
    }

    public void setAnimalCount(int animalCount) {
        this.animalCount = animalCount;
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

        String formato = "| %-5d | %-15s | %-15s | %-5d | %-15s |";
        return String.format(formato, getIdShelter(), getNameShelter(),getResponsible(),getCapacity(),getLocation());
        /**return "ShelterEntity{" +
                "id_shelter=" + id_shelter + //1
                ", name_shelter='" + name_shelter + '\'' + //2
                ", responsible='" + responsible + '\'' + //3
                ", capacity=" + capacity + //4
                ", location='" + location + '\'' + //5
                '}';**/
    }
}
