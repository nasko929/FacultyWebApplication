package servlets.teacherServlets;

import databaseOperations.CourseOperations;
import databaseOperations.TeacherOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle deleting Teachers from the database.
 */
@WebServlet(name = "DeleteTeacherServlet", urlPatterns = {"/teacherServlets/DeleteTeacherServlet"})
public class DeleteTeacherServlet extends HttpServlet {

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
        int idToDelete = Integer.parseInt(request.getParameter("id"));
        CourseOperations.setAllTeacherIdToNullInCourse(idToDelete);
        TeacherOperations.deleteTeacher(idToDelete);
        response.sendRedirect(request.getContextPath() + "/teacherServlets/ShowAllTeachersServlet");
    }
}
