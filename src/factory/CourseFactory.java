package factory;

import model.Course;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

/**
 * A class to create Course objects from different sources.
 */
public class CourseFactory {

    /**
     * A method to create a Course object from the form that the user is filling.
     *
     * @param request - the HttpServletRequest object that contains all the data from the form.
     * @return - Course object.
     */
    public static Course createCourseFromForm(HttpServletRequest request) {
        Course course = new Course();
        String name = request.getParameter("name");
        course.setName(name);
        return course;
    }

    /**
     * A method to create a Course object from ResultSet object that we get from executing SQL query.
     * Therefore we can encounter SQLException.
     *
     * @param resultSet - the ResultSet object that contains the needed information.
     * @return - Course object.
     * @throws SQLException - throws SQLException.
     */
    public static Course createCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer teacherId = resultSet.getInt("teacher_id");
        if (resultSet.wasNull()) {
            teacherId = null;
        }
        course.setId(id);
        course.setName(name);
        course.setTeacherId(teacherId);
        return course;
    }
}
