package servlets.courseServlets;

import databaseOperations.CourseOperations;
import databaseOperations.StudentCourseOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle deleting Courses from the database.
 */
@WebServlet(name = "DeleteCourseServlet", urlPatterns = {"/courseServlets/DeleteCourseServlet"})
public class DeleteCourseServlet extends HttpServlet {

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

        StudentCourseOperations.deleteByCourseId(Integer.parseInt(request.getParameter("id")));
        CourseOperations.deleteCourse(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect(request.getContextPath() + "/courseServlets/ShowAllCoursesServlet");
    }
}
