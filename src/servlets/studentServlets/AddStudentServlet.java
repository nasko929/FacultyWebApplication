package servlets.studentServlets;

import databaseOperations.StudentOperations;
import factory.StudentFactory;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle adding new Students to the database.
 */
@WebServlet(name = "AddStudentServlet", urlPatterns = {"/studentServlets/AddStudentServlet"})
public class AddStudentServlet extends HttpServlet {

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
        boolean isTheFormProperlyFilled = true;
        if (!request.getParameter("firstName").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("first_name_error", "This field is required!");
        }
        if (!request.getParameter("lastName").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("last_name_error", "This field is required!");
        }
        if (!request.getParameter("credits").matches("^[1-9][0-9]*$")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("credits_error", "This field should contain positive integer!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/student/create.jsp").forward(request, response);
        } else {
            Student student = StudentFactory.createStudentFromForm(request);
            StudentOperations.insertStudent(student);
        }

        response.sendRedirect(request.getContextPath() + "/studentServlets/ShowAllStudentsServlet");
    }
}
