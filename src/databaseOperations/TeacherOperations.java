package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.TeacherFactory;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class TeacherOperations {

    /**
     * A method which inserts a teacher object into the database.
     *
     * @param teacher - the teacher object.
     */
    public static void insertTeacher(Teacher teacher) {
        String prepStatementInsert = "INSERT INTO teachers (first_name,last_name) VALUES(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
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
     * A method which fetches a teacher record from the teachers table and turns it into teacher object.
     *
     * @param id - the id that we're looking for in the database.
     * @return - the teacher object.
     */
    public static Teacher getTeacherById(int id) {
        String prepStatementFindById = "SELECT * FROM teachers WHERE id = ?";
        Teacher teacher = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = TeacherFactory.createTeacherFromResultSet(resultSet);
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
        return teacher;
    }

    /**
     * A method which returns an array list of all the teachers that are in the teachers table.
     *
     * @return - an array list.
     */
    public static ArrayList<Teacher> getAllTeachers() {
        String statementForFetchingAllData = "SELECT * FROM teachers";
        ArrayList<Teacher> teachers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(statementForFetchingAllData);
            while (resultSet.next()) {
                teachers.add(TeacherFactory.createTeacherFromResultSet(resultSet));
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
        return teachers;
    }

    /**
     * A method to update a teacher in the database.
     *
     * @param teacher - the new form of the teacher object.
     * @param id      - the id of the object.
     */
    public static void updateTeacher(Teacher teacher, int id) {
        String prepStatementUpdate = "UPDATE teachers SET first_name=?,last_name=? WHERE id=?"; // DONE
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementUpdate);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setInt(3, id);
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
     * A method to delete teacher by id.
     *
     * @param id - the id of the to-be-deleted teacher.
     */
    public static void deleteTeacher(int id) {
        String prepStatementDeleteById = "DELETE FROM teachers WHERE id = ?";
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
