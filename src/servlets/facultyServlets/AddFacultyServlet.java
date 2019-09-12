package servlets.facultyServlets;

import databaseOperations.FacultyOperations;
import factory.FacultyFactory;
import model.Faculty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle adding new Students to the database.
 */
@WebServlet(name = "AddFacultyServlet", urlPatterns = {"/facultyServlets/AddFacultyServlet"})
public class AddFacultyServlet extends HttpServlet {

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
        boolean isTheFormProperlyFilled = true;
        if(!request.getParameter("name").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("name_error", "This field is required!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/faculty/create.jsp").forward(request, response);
        } else {
            Faculty faculty = FacultyFactory.createFacultyFromForm(request);
            FacultyOperations.insertFaculty(faculty);
        }
        response.sendRedirect(request.getContextPath() + "/facultyServlets/ShowAllFacultiesServlet");
    }
}
