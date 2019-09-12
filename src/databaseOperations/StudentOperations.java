package databaseOperations;

import databaseAccess.DatabaseAccess;
import factory.StudentFactory;
import model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentOperations {

    /**
     * A method which inserts a student object into the database.
     *
     * @param student - the student object.
     */
    public static void insertStudent(Student student) {
        String prepStatementInsert = "INSERT INTO students (first_name,last_name,faculty_id,credits) " +
                "VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementInsert);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            if (student.getFacultyId() == null) {
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, student.getFacultyId());
            }
            preparedStatement.setInt(4, student.getCredits());
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
     * A method which fetches a student record from the students table and turns it into student object.
     *
     * @param id - the id that we're looking for in the database.
     * @return - the student object.
     */
    public static Student getStudentById(int id) {
        String prepStatementFindById = "SELECT * FROM students WHERE id = ?"; // DONE
        Student student = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementFindById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = StudentFactory.createStudentFromResultSet(resultSet);
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
        return student;
    }

    /**
     * A method which returns an array list of ids of students that study in the given faculty.
     *
     * @param faculty_id - faculty's id.
     * @return - an array list of ids.
     */
    public static ArrayList<Integer> getAllStudentsIdsInFaculty(int faculty_id) {
        String prepStatementGetAllStudentsIdInAFaculty = "SELECT id FROM students WHERE faculty_id=?";
        ArrayList<Integer> ids = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementGetAllStudentsIdInAFaculty);
            preparedStatement.setInt(1, faculty_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
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
        return ids;
    }

    /**
     * A method to update all of the students' faculty_ids by getting a list of students's ids we have to change.
     *
     * @param ids        - list of students by their ids to be changed
     * @param faculty_id - the new value of faculty_id
     */
    public static void updateAllFacultyIdsByListOfStudentIds(ArrayList<Integer> ids, int faculty_id) {
        String prepStatementForSettingFacultyId = "UPDATE students SET faculty_id=" + faculty_id
                + " WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForSettingFacultyId);
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
     * A method which returns an array list of all the students that are in the students table.
     *
     * @return - an array list.
     */
    public static ArrayList<Student> getAllStudents() {
        String statementForFetchingAllData = "SELECT * FROM students"; // DONE
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseAccess.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(statementForFetchingAllData);
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
     * A method to update a student in the database.
     *
     * @param student - the new form of the student object.
     * @param id      - the id of the object.
     */
    public static void updateStudent(Student student, int id) {
        String prepStatementUpdate = "UPDATE students SET first_name=?,last_name=?,faculty_id=?,credits=? WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementUpdate);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            if (student.getFacultyId() != null) {
                preparedStatement.setInt(3, student.getFacultyId());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.setInt(4, student.getCredits());
            preparedStatement.setInt(5, id);
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
     * A method used when updating faculty. It sets all of the students' faculty_ids that are studying in faculty_id
     * to null.
     *
     * @param facultyId - the id of the to-be-updated faculty.
     */
    public static void setAllFacultyIdToNullInStudent(int facultyId) {
        String prepStatementForSettingNullToTeacherId = "UPDATE students SET faculty_id=NULL WHERE faculty_id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseAccess.getConnection();
            preparedStatement = connection.prepareStatement(prepStatementForSettingNullToTeacherId);
            preparedStatement.setInt(1, facultyId);
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
     * A method to delete student by id.
     *
     * @param id - the id of the to-be-deleted student.
     */
    public static void deleteStudent(int id) {
        String prepStatementDeleteById = "DELETE FROM students WHERE id = ?";
        StudentCourseOperations.deleteRecordByStudentId(id);
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
