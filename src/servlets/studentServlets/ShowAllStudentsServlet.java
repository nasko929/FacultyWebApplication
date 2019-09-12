package servlets.studentServlets;

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
 * A Servlet to fetch all the data about Students from the database and send it to JSP for displaying.
 */
@WebServlet(name = "ShowAllStudentsServlet", urlPatterns = {"/studentServlets/ShowAllStudentsServlet"})
public class ShowAllStudentsServlet extends HttpServlet {

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
        request.getRequestDispatcher("/student/showAll.jsp").forward(request, response);
    }
}
