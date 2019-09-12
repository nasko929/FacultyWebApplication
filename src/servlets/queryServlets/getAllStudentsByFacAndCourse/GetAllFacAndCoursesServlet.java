package servlets.queryServlets.getAllStudentsByFacAndCourse;

import databaseOperations.CourseOperations;
import databaseOperations.FacultyOperations;
import model.Course;
import model.Faculty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to fetch all courses and faculties from the database and send them to JSP
 * to be shown as options for the user.
 */
@WebServlet(name = "GetAllFacAndCoursesServlet",urlPatterns = {"/queryServlets/getAllStudentsByFacAndCourse/" +
        "GetAllFacAndCoursesServlet"})
public class GetAllFacAndCoursesServlet extends HttpServlet {
    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws ServletException.
     * @throws IOException      - throws IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    /**
     * A method which is triggered when the servlet gets a GET request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws ServletException.
     * @throws IOException      - throws IOException.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Course> courses = CourseOperations.getAllCourses();
        ArrayList<Faculty> faculties = FacultyOperations.getAllFaculties();
        request.setAttribute("faculties",faculties);
        request.setAttribute("courses",courses);
        request.getRequestDispatcher("/query/allStudentsByGivenFacAndCourse/" +
                "inputFacultyAndCourseId.jsp").forward(request,response);
    }
}
