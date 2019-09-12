package factory;

import model.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * A class to create Teacher objects from different sources.
 */
public class TeacherFactory {

    /**
     * A method to create a Teacher object from the form that the user is filling.
     *
     * @param request - the HttpServletRequest object that contains all the data from the form.
     * @return - Teacher object.
     */
    public static Teacher createTeacherFromForm(HttpServletRequest request) {
        Teacher teacher = new Teacher();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return teacher;
    }

    /**
     * A method to create a Teacher object from ResultSet object that we get from executing SQL query.
     * Therefore we can encounter SQLException.
     *
     * @param resultSet - the ResultSet object that contains the needed information.
     * @return - Teacher object.
     * @throws SQLException - throws SQLException.
     */
    public static Teacher createTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        teacher.setId(id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return teacher;
    }
}
