package servlets.queryServlets.getAllStudentsByGivenFaculty;

import databaseOperations.FacultyOperations;
import model.Faculty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A servlet to fetch all faculties from the database and send them to JSP to be shown as options for the user.
 */
@WebServlet(name = "GetAllFacultiesServlet", urlPatterns = {"/queryServlets/getAllStudentsByGivenFaculty/" +
        "GetAllFacultiesServlet"})
public class GetAllFacultiesServlet extends HttpServlet {

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
        response.setContentType("text/html");

        ArrayList<Faculty> faculties = FacultyOperations.getAllFaculties();
        request.setAttribute("faculties", faculties);
        request.getRequestDispatcher("/query/allStudentsByGivenFaculty/inputFacultyId.jsp").forward(request, response);
    }
}
