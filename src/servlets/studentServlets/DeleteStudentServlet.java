package servlets.studentServlets;

import databaseOperations.StudentOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle deleting Students from the database.
 */
@WebServlet(name = "DeleteStudentServlet", urlPatterns = {"/studentServlets/DeleteStudentServlet"})
public class DeleteStudentServlet extends HttpServlet {

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
        StudentOperations.deleteStudent(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect(request.getContextPath() + "/studentServlets/ShowAllStudentsServlet");
    }
}
