package servlets.queryServlets;

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
 * A Servlet to handle the query to fetch all the students with >40 credits and send the info to the JSP to be shown.
 */
@WebServlet(name = "GetAllStudentsWithOver40CreditsServlet", urlPatterns = {"/queryServlets/" +
        "GetAllStudentsWithOver40CreditsServlet"})
public class GetAllStudentsWithOver40CreditsServlet extends HttpServlet {

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
        ArrayList<Student> students = QueryOperations.getAllStudentsWithOver40Credits();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/query/displayAllStudentsWithMoreThan40Credits.jsp").forward(request, response);
    }
}
