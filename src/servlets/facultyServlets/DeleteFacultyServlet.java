package servlets.facultyServlets;

import databaseOperations.FacultyOperations;
import databaseOperations.StudentOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle deleting Faculties from the database.
 */
@WebServlet(name = "DeleteFacultyServlet", urlPatterns = {"/facultyServlets/DeleteFacultyServlet"})
public class DeleteFacultyServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request
     * @param response - response
     * @throws ServletException - throws ServletException
     * @throws IOException      - throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * A method which is triggered when the servlet gets a GET request.
     *
     * @param request  - request
     * @param response - response
     * @throws ServletException - throws ServletException
     * @throws IOException      - throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<Integer> idsOfStudents = StudentOperations.getAllStudentsIdsInFaculty(id);
        for (int currentId : idsOfStudents) {
            StudentOperations.deleteStudent(currentId);
        }
        FacultyOperations.deleteFaculty(id);
        response.sendRedirect(request.getContextPath() + "/facultyServlets/ShowAllFacultiesServlet");
    }
}
