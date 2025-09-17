package models;

public class AnimalEntity {
    private int id;
    private String name;
    private int age;
    private String date_entry;
    private String health_situation;
    private String specie;
    private int id_shelter;

    public AnimalEntity() {}

    public AnimalEntity(int id, String name, int age, String date_entry, String health_situation,String specie, int id_shelter) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.date_entry = date_entry;
        this.health_situation = health_situation;
        this.specie = specie;
        this.id_shelter = id_shelter;

    }


    public AnimalEntity(String name, int age, String date_entry, String health_situation,String specie, int id_shelter) {
        this.name = name;
        this.age = age;
        this.date_entry = date_entry;
        this.health_situation = health_situation;
        this.specie = specie;
        this.id_shelter = id_shelter;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDate_entry() {
        return date_entry;
    }

    public void setDate_entry(String date_entry) {
        this.date_entry = date_entry;
    }

    public String getHealth_situation() {
        return health_situation;
    }

    public void setHealth_situation(String health_situation) {
        this.health_situation = health_situation;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getId_shelter() {
        return id_shelter;
    }

    public void setId_shelter(int id_shelter) {
        this.id_shelter = id_shelter;
    }

    @Override
    public String toString() {
        String formato = "| %-5d | %-15s | %-5d | %-15s | %-15s | %-15s | %-5d |";
        return String.format(formato,getId(),getName(),getAge(),getDate_entry(),getHealth_situation(),getSpecie(),getId_shelter());


        /**return "AnimalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date_entry='" + date_entry + '\'' +
                ", health_situation='" + health_situation + '\'' +
                ", specie='" + specie + '\'' +
                ", id_shelter=" + id_shelter +
                '}';**/
    }
}
