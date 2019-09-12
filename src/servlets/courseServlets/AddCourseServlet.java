package servlets.courseServlets;

import databaseOperations.CourseOperations;
import factory.CourseFactory;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle adding new Courses to the database.
 */
@WebServlet(name = "AddCourseServlet", urlPatterns = {"/courseServlets/AddCourseServlet"})
public class AddCourseServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws Servlet Exception.
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
     * @throws ServletException - throws Servlet Exception.
     * @throws IOException      - throws IOException.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        boolean isTheFormProperlyFilled = true;
        if (!request.getParameter("name").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("name_error", "This field is required!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/course/create.jsp").forward(request, response);
        } else {
            Course course = CourseFactory.createCourseFromForm(request);
            CourseOperations.insertCourse(course);
        }
        response.sendRedirect(request.getContextPath() + "/courseServlets/ShowAllCoursesServlet");
    }
}
