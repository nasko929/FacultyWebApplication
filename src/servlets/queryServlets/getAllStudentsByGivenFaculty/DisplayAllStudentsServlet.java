package servlets.queryServlets.getAllStudentsByGivenFaculty;

import databaseOperations.QueryOperations;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A servlet to get all students by the already user-provided faculty_id and send them to JSP to be displayed.
 */
@WebServlet(name = "DisplayAllStudentsServlet", urlPatterns = {"/queryServlets/getAllStudentsByGivenFaculty/" +
        "DisplayAllStudentsServlet"})
public class DisplayAllStudentsServlet extends HttpServlet {

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
        int facultyId = Integer.parseInt(request.getParameter("faculty"));
        ArrayList<Student> students = QueryOperations.getAllStudentsInAFaculty(facultyId);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/query/allStudentsByGivenFaculty/" +
                "displayStudents.jsp").forward(request, response);
    }
}
