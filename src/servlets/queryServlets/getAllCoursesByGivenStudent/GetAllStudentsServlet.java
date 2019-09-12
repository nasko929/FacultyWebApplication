package servlets.queryServlets.getAllCoursesByGivenStudent;

import databaseOperations.StudentOperations;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to fetch all students from the database and send them to JSP as options for the user to choose.
 */
@WebServlet(name = "GetAllStudentsServlet", urlPatterns = {"/queryServlets/getAllCoursesByGivenStudent/" +
        "GetAllStudentsServlet"})
public class GetAllStudentsServlet extends HttpServlet {

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
        ArrayList<Student> students = StudentOperations.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/query/allCoursesByGivenStudent/inputStudentId.jsp").forward(request, response);
    }
}
