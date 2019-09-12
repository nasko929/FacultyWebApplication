package model;

/**
 * A class that is representing a student.
 */
public class Student {
    private int id; // The id of an object.
    private String firstName; // The first name of the object.
    private String lastName; // The last name of the object.
    private Integer facultyId; // The id of the faculty that this student is in.
    private int credits; // The amount of credits that a student has.

    /**
     * All the getters and setters.
     */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getFacultyId() {
        return this.facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}