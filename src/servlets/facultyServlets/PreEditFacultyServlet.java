package servlets.facultyServlets;

import databaseOperations.FacultyOperations;
import databaseOperations.StudentOperations;
import model.Faculty;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to collect all the needed data to display for the user to change and update the Faculty.
 */
@WebServlet(name = "PreEditFacultyServlet", urlPatterns = {"/facultyServlets/PreEditFacultyServlet"})
public class PreEditFacultyServlet extends HttpServlet {

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

        ArrayList<Student> students = StudentOperations.getAllStudents();
        Faculty faculty = FacultyOperations.getFacultyById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("students", students);
        request.setAttribute("faculty", faculty);
        request.getRequestDispatcher("/faculty/edit.jsp").forward(request, response);
    }
}
