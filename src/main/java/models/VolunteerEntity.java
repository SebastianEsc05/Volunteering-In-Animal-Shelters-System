package models;

import java.sql.Date;

public class VolunteerEntity {
    private int id_volunteer;
    private String name_volunteer;
    private String phone_number;
    private String email;
    private Date date_birth;
    private String specialty;

    public VolunteerEntity() {
    }

    public VolunteerEntity(int id_volunteer, String name_volunteer, String phone_number, String email, Date date_birth, String specialty) {
        this.id_volunteer = id_volunteer;
        this.name_volunteer = name_volunteer;
        this.phone_number = phone_number;
        this.email = email;
        this.date_birth = date_birth;
        this.specialty = specialty;

    }

    public VolunteerEntity(String name_volunteer, String phone_number, String email, Date date_birth, String specialty) {
        this.name_volunteer = name_volunteer;
        this.phone_number = phone_number;
        this.email = email;
        this.date_birth = date_birth;
        this.specialty = specialty;

    }

    public int getId_volunteer() {
        return id_volunteer;
    }

    public void setId_volunteer(int id_volunteer) {
        this.id_volunteer = id_volunteer;
    }

    public String getName_volunteer() {
        return name_volunteer;
    }

    public void setName_volunteer(String name_volunteer) {
        this.name_volunteer = name_volunteer;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        String formato = "| %-5d | %-15s | %-15s | %-30s | %-19s | %-15s |";

        return  String.format(formato,getId_volunteer(),getName_volunteer(),getPhone_number(),getEmail(),getDate_birth(),getSpecialty());
        /**return "VolunteerEntity{" +
                "id_volunteer=" + id_volunteer + //1
                ", name_volunteer='" + name_volunteer + '\'' + //2
                ", phone_number='" + phone_number + '\'' + //3
                ", email='" + email + '\'' + //4
                ", date_birth='" + date_birth + '\'' + //5
                '}';**/
    }
}
