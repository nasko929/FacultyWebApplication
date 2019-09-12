package model;

/**
 * A class that is representing a teacher.
 */
public class Teacher {
    private int id; // The id of an object.
    private String firstName; // The first name of the object.
    private String lastName; // The last name of the object.


    /**
     * All the getters and setters.
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}