package models;

public class VolunteerEntity {

    /*
        VolunteerEntity Attributes
     */
    private int id_volunteer;
    private String name_volunteer;
    private String phone_number;
    private String email;
    private String date_birth;
    private String specialty;

    /**
     * 
     */
    public VolunteerEntity() {
    }

    /**
     * Constructor Method that create a volunteer Entity with all attributes in Database
     * @param id_volunteer
     * @param name_volunteer
     * @param phone_number
     * @param email
     * @param date_birth
     * @param specialty
     */
    public VolunteerEntity(int id_volunteer, String name_volunteer, String phone_number, String email, String date_birth, String specialty) {
        this.id_volunteer = id_volunteer;
        this.name_volunteer = name_volunteer;
        this.phone_number = phone_number;
        this.email = email;
        this.date_birth = date_birth;
        this.specialty = specialty;

    }

    /**
     * Constructor method that create a volunteer Entity without an ID
     * @param name_volunteer
     * @param phone_number
     * @param email
     * @param date_birth
     * @param specialty
     */
    public VolunteerEntity(String name_volunteer, String phone_number, String email, String date_birth, String specialty) {
        this.name_volunteer = name_volunteer;
        this.phone_number = phone_number;
        this.email = email;
        this.date_birth = date_birth;
        this.specialty = specialty;

    }

    /**
     * Gets the volunteer's ID
     * @return id_volunteer
     */
    public int getId_volunteer() {
        return id_volunteer;
    }

    /**
     * Sets the volunteer's ID
     * @param id_volunteer
     */
    public void setId_volunteer(int id_volunteer) {
        this.id_volunteer = id_volunteer;
    }

    /**
     * Gets volunteer's name
     * @return name_volunteer
     */
    public String getName_volunteer() {
        return name_volunteer;
    }

    /**
     * Sets volunteer's name
     * @param name_volunteer
     */
    public void setName_volunteer(String name_volunteer) {
        this.name_volunteer = name_volunteer;
    }

    /**
     * Gets volunteer's phone number
     * @return phone_number
     */
    public String getPhone_number() {
        return phone_number;
    }

    /**
     * Sets volunteer's phone number
     * @param phone_number
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Gets volunteer's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets volunteer's email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets volunteer's date birth
     * @return date_birth
     */
    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * to String method that returns a formated String with volunteer attributes
     */
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
