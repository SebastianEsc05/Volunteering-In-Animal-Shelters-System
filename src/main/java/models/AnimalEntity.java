package models;

public class AnimalEntity {
    private int id_animal;
    private String name_animal;
    private int age;
    private String health_situation;
    private String date_entry;
    private int id_shelter;
    private int id_specie;

    public AnimalEntity() {}

    public AnimalEntity(int id_animal, String name_animal, int age, String health_situation, String date_entry, int id_shelter, int id_specie) {
        this.id_animal = id_animal;
        this.name_animal = name_animal;
        this.age = age;
        this.health_situation = health_situation;
        this.date_entry = date_entry;
        this.id_shelter = id_shelter;
        this.id_specie = id_specie;
    }

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getName_animal() {
        return name_animal;
    }

    public void setName_animal(String name_animal) {
        this.name_animal = name_animal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealth_situation() {
        return health_situation;
    }

    public void setHealth_situation(String health_situation) {
        this.health_situation = health_situation;
    }

    public String getDate_entry() {
        return date_entry;
    }

    public void setDate_entry(String date_entry) {
        this.date_entry = date_entry;
    }

    public int getId_shelter() {
        return id_shelter;
    }

    public void setId_shelter(int id_shelter) {
        this.id_shelter = id_shelter;
    }

    public int getId_specie() {
        return id_specie;
    }

    public void setId_specie(int id_specie) {
        this.id_specie = id_specie;
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id_animal=" + id_animal +
                ", name_animal='" + name_animal + '\'' +
                ", age=" + age +
                ", health_situation='" + health_situation + '\'' +
                ", date_entry='" + date_entry + '\'' +
                ", id_shelter=" + id_shelter +
                ", id_specie=" + id_specie +
                '}';
    }
}
