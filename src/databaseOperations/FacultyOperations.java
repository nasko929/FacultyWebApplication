package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.FacultyFactory;
import model.Faculty;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class to execute the queries that are sent towards the faculties table in database.
 */
public class FacultyOperations {

    /**
     * A method which inserts faculty object into the database.
     *
     * @param faculty - the faculty object.
     */
    public static void insertFaculty(Faculty faculty) {
        String prepStatementInsert = "INSERT INTO faculties (name) VALUES(?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1, faculty.getName());
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
     * A method which fetches a faculty record from the faculties table and turns it into faculty object.
     *
     * @param id - the id that we're looking for in the database.
     * @return - the faculty object.
     */
    public static Faculty getFacultyById(int id) {
        String prepStatementFindById = "SELECT * FROM faculties WHERE id = ?";
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                faculty = FacultyFactory.createFacultyFromResultSet(resultSet);
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
        return faculty;
    }

    /**
     * A method which returns an array list of all the faculties that are in the faculties table.
     *
     * @return an array list.
     */
    public static ArrayList<Faculty> getAllFaculties() {
        String statementForFetchingAllData = "SELECT * FROM faculties";
        ArrayList<Faculty> faculties = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(statementForFetchingAllData);
            while (resultSet.next()) {
                faculties.add(FacultyFactory.createFacultyFromResultSet(resultSet));
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
        return faculties;
    }

    /**
     * A method to update a faculty in the database.
     *
     * @param faculty - the new form of the faculty object.
     * @param id      - the id of the object.
     */
    public static void updateFaculty(Faculty faculty, int id) {
        String prepStatementUpdate = "UPDATE faculties SET name=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementUpdate);
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setInt(2, id);
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
     * A method to delete faculty by id.
     *
     * @param id - the id of the to-be-deleted faculty.
     */
    public static void deleteFaculty(int id) {
        String prepStatementDeleteById = "DELETE FROM faculties WHERE id = ?"; //
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
