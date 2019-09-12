package model;

/**
 * A class that is representing a faculty.
 */
public class Faculty {
    private int id; // The id of an object.
    private String name; // The name of the object.

    /**
     * All the getters and setters.
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}