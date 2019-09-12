package model;

/**
 * A class that is representing a course.
 */
public class Course {
    private int id; // The id of an object.
    private String name; // The name of the object.
    private Integer teacherId; // The id of the teacher, that is teaching this course.ñ

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

    public Integer getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}