package databaseOperations;

import databaseAccess.DatabaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCourseOperations {

    /**
     * A method to check in the database whether record with student_id and course_id exists or not.
     *
     * @param student_id - student's id.
     * @param course_id  - courses's id.
     * @return - whether the method exists or not.
     */
    public static boolean ifRecordExists(int student_id, int course_id) {
        String prepStatementForCheckingExistence = "SELECT * FROM students_courses "
                + "WHERE student_id=? AND course_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForCheckingExistence);
            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, course_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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
        return false;
    }

    /**
     * A method which inserts a record into the students_courses table.
     *
     * @param student_id - student's id.
     * @param course_id  - course's id.
     */
    public static void insertIntoStudentCourse(int student_id, int course_id) {
        String prepStatementForInserting = "INSERT INTO students_courses (student_id,course_id) "
                + "VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForInserting);
            preparedStatement.setInt(1, student_id);
            preparedStatement.setInt(2, course_id);
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
     * A method to delete record by given course_id.
     *
     * @param course_id - course's id.
     */
    public static void deleteByCourseId(int course_id) {
        String prepStatementForDeletingByCourseId = "DELETE FROM students_courses WHERE course_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForDeletingByCourseId);
            preparedStatement.setInt(1, course_id);
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
     * A method to delete record by given student_id.
     *
     * @param student_id - student's id.
     */
    public static void deleteRecordByStudentId(int student_id) {
        String prepStatementForDeletingByStudentId = "DELETE FROM students_courses WHERE student_id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForDeletingByStudentId);
            preparedStatement.setInt(1, student_id);
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
