package servlets.queryServlets.getAllCoursesByGivenStudent;

import databaseOperations.QueryOperations;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to get all the courses of a given student and send them to JSP to be displayed.
 */
@WebServlet(name = "DisplayAllCoursesServlet", urlPatterns = {"/queryServlets/getAllCoursesByGivenStudent" +
        "/DisplayAllCoursesServlet"})
public class DisplayAllCoursesServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws ServletException.
     * @throws IOException      - throws IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
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
        int studentId = Integer.parseInt(request.getParameter("student"));
        ArrayList<Course> courses = QueryOperations.getAllCoursesOfStudent(studentId);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/query/allCoursesByGivenStudent/displayCourses.jsp").forward(request, response);
    }
}
