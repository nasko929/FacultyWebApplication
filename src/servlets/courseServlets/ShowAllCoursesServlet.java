package servlets.courseServlets;

import databaseOperations.CourseOperations;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to fetch all the data about Courses from the database and send it to JSP for displaying.
 */
@WebServlet(name = "ShowAllCoursesServlet", urlPatterns = {"/courseServlets/ShowAllCoursesServlet"})
public class ShowAllCoursesServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request - request.
     * @param response - response.
     * @throws ServletException - throws Servlet Exception.
     * @throws IOException - throws IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * A method which is triggered when the servlet gets a GET request.
     *
     * @param request - request.
     * @param response - response.
     * @throws ServletException - throws Servlet Exception.
     * @throws IOException - throws IOException.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        ArrayList<Course> courses = CourseOperations.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/course/showAll.jsp").forward(request, response);
    }
}
