package model;

/**
 * A class to represent the object that we get from database while querying.
 */
public class CourseTeacher {
    private int courseId; // The course's id.
    private String courseName; // The course's name.
    private String teacherFirstName; // The teacher's first name.
    private String teacherLastName; // The teacher's last name.

    /**
     * Getters and setters.
     */
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }
}
