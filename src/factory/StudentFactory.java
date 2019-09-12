package factory;

import model.Student;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * A class to create Student objects from different sources.
 */
public class StudentFactory {

    /**
     * A method to create a Student object from the form that the user is filling.
     *
     * @param request - the HttpServletRequest object that contains all the data from the form.
     * @return - Student object.
     */
    public static Student createStudentFromForm(HttpServletRequest request) {
        Student student = new Student();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String credits = request.getParameter("credits");
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setCredits(Integer.parseInt(credits));
        return student;
    }

    /**
     * A method to create a Student object from ResultSet object that we get from executing SQL query.
     * Therefore we can encounter SQLException.
     *
     * @param resultSet - the ResultSet object that contains the needed information.
     * @return - Student object.
     * @throws SQLException - throws SQLException
     */
    public static Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        Integer facultyId = resultSet.getInt("faculty_id");
        int credits = resultSet.getInt("credits");
        if (resultSet.wasNull()) {
            facultyId = null;
        }
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setFacultyId(facultyId);
        student.setCredits(credits);
        return student;
    }
}
