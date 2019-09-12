package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class to execute the queries that are sent towards database directly from the user.
 */
public class QueryOperations {

    /**
     * A method to implement the first of the given queries. It fetches from the database all
     * courses of a given student.
     *
     * @param student_id - the student's id.
     * @return - an array list of courses, that student with student_id studies.
     */
    public static ArrayList<Course> getAllCoursesOfStudent(int student_id) {
        String prepSQLQuery = "SELECT c.id, c.name, c.teacher_id FROM courses AS c JOIN " +
                "students_courses AS sc ON c.id = sc.course_id WHERE sc.student_id = ?";
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepSQLQuery);
            preparedStatement.setInt(1, student_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(CourseFactory.createCourseFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }

    /**
     * A method to implement the second of the given queries. It fetches from the database all
     * students of a given faculty.
     *
     * @param faculty_id - the faculty's id.
     * @return - an array list of students, that study in faculty with faculty_id.
     */
    public static ArrayList<Student> getAllStudentsInAFaculty(int faculty_id) {
        String prepSQLQuery = "SELECT s.id, s.first_name, s.last_name, s.faculty_id,s.credits FROM students AS s " +
                "WHERE s.faculty_id = ?";
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepSQLQuery);
            preparedStatement.setInt(1, faculty_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(StudentFactory.createStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    /**
     * A method which fetches all the students with over 40 credits from the database.
     *
     * @return - an array list of students with >40 credits.
     */
    public static ArrayList<Student> getAllStudentsWithOver40Credits() {
        String sqlQuery = "SELECT * FROM students WHERE credits > 40";
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                students.add(StudentFactory.createStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    /**
     * A method to get all the students that are in given faculty and study given course.
     *
     * @param faculty_id - faculty's id.
     * @param course_id  - course's id.
     * @return - an array list of all students that study in faculty with faculty_id and study course with course_id.
     */
    public static ArrayList<Student> getStudentsByGivenFacultyIdAndCourseId(int faculty_id, int course_id) {
        String prepSQLQuery = "SELECT * FROM students AS s JOIN students_courses AS sc ON " +
                "sc.student_id = s.id WHERE s.faculty_id = ? AND sc.course_id = ?";
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepSQLQuery);
            preparedStatement.setInt(1, faculty_id);
            preparedStatement.setInt(2, course_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(StudentFactory.createStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    /**
     * A method which fetches all the courses from the table and their teacher's names.
     *
     * @return - an array list of mixed object type.
     */
    public static ArrayList<CourseTeacher> getAllCoursesAndTeachersNames() {
        String sqlQuery = "SELECT c.id, c.name, t.first_name, t.last_name FROM " +
                "courses AS c JOIN teachers AS t ON t.id = c.teacher_id";
        Connection connection = null;
        Statement statement = null;
        ArrayList<CourseTeacher> courseTeachers = new ArrayList<>();
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                CourseTeacher courseTeacher = new CourseTeacher();
                courseTeacher.setCourseId(resultSet.getInt("id"));
                courseTeacher.setCourseName(resultSet.getString("name"));
                courseTeacher.setTeacherFirstName(resultSet.getString("first_name"));
                courseTeacher.setTeacherLastName(resultSet.getString("last_name"));
                courseTeachers.add(courseTeacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseTeachers;
    }
}
