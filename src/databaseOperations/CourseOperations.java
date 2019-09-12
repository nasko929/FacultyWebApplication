package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.CourseFactory;
import model.Course;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class to execute the queries that are sent towards the courses table in database.
 */
public class CourseOperations {

    /**
     * A method which inserts a course object into the database.
     *
     * @param course - the course object.
     */
    public static void insertCourse(Course course) {
        String prepStatementInsert = "INSERT INTO courses (name,teacher_id) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1, course.getName());
            if (course.getTeacherId() == null) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else preparedStatement.setInt(2, course.getTeacherId());
            preparedStatement.executeUpdate();
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
    }

    /**
     * A method which fetches a course record from the courses table and turns it into course object.
     *
     * @param id - the id that we're looking for in the database.
     * @return - the course object.
     */
    public static Course getCourseById(int id) {
        String prepStatementFindById = "SELECT * FROM courses WHERE id = ?";
        Course course = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = CourseFactory.createCourseFromResultSet(resultSet);
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
        return course;
    }

    /**
     * A method which returns an arraylist of all the courses that are in the courses table.
     *
     * @return - an arraylist.
     */
    public static ArrayList<Course> getAllCourses() {
        String statementForFetchingAllData = "SELECT * FROM courses";
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(statementForFetchingAllData);
            while (resultSet.next()) {
                courses.add(CourseFactory.createCourseFromResultSet(resultSet));
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
        return courses;
    }

    /**
     * A method to update a course in the database.
     *
     * @param course - the new form of the course object.
     * @param id     - the id of the object.
     * @return - number of rows changed.
     */
    public static int updateCourse(Course course, int id) {
        String prepStatementUpdate = "UPDATE courses SET name=?,teacher_id=? WHERE id=?";
        int numberOfRowsChanged = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementUpdate);
            preparedStatement.setString(1, course.getName());
            if (course.getTeacherId() != null) {
                preparedStatement.setInt(2, course.getTeacherId());
            } else {
                preparedStatement.setNull(2, Types.INTEGER);
            }
            preparedStatement.setInt(3, id);
            numberOfRowsChanged = preparedStatement.executeUpdate();
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
        return numberOfRowsChanged;
    }

    /**
     * A method to update all of the courses' teacher_ids by getting a list of courses' ids we have to change.
     *
     * @param ids        - list of courses by their ids to be changed
     * @param teacher_id - the new value of teacher_id
     */
    public static void updateAllTeacherIdsByListOfStudentIds(ArrayList<Integer> ids, int teacher_id) {
        String prepStatementForSettingTeacherId = "UPDATE courses SET teacher_id=" + teacher_id
                + " WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForSettingTeacherId);
            for (int currId : ids) {
                preparedStatement.setInt(1, currId);
                preparedStatement.executeUpdate();
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
    }

    /**
     * A method used when deleting a teacher. It sets all of the courses' teacher_ids that are being taught
     * by this teacher to null.
     *
     * @param teacherId - the id of the to-be-deleted teacher.
     */
    public static void setAllTeacherIdToNullInCourse(int teacherId) {
        String prepStatementForSettingNullToTeacherId = "UPDATE courses SET teacher_id=NULL WHERE teacher_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForSettingNullToTeacherId);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.executeUpdate();
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
    }

    /**
     * A method to delete course by id.
     *
     * @param id - the id of the to-be-deleted course.
     */
    public static void deleteCourse(int id) {
        String prepStatementDeleteById = "DELETE FROM courses WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementDeleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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
    }
}
