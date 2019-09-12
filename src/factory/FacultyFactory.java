package factory;

import model.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * A class to create Faculty objects from different sources.
 */
public class FacultyFactory {

    /**
     * A method to create a Faculty object from the form that the user is filling.
     *
     * @param request - the HttpServletRequest object that contains all the data from the form.
     * @return - Faculty object.
     */
    public static Faculty createFacultyFromForm(HttpServletRequest request) {
        Faculty faculty = new Faculty();
        String name = request.getParameter("name");
        faculty.setName(name);
        return faculty;
    }

    /**
     * A method to create a Faculty object from ResultSet object that we get from executing SQL query.
     * Therefore we can encounter SQLException.
     *
     * @param resultSet - the ResultSet object that contains the needed information.
     * @return - Faculty object.
     * @throws SQLException - throws SQLException.
     */
    public static Faculty createFacultyFromResultSet(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        faculty.setId(id);
        faculty.setName(name);
        return faculty;
    }
}
