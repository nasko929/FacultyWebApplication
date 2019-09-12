package servlets.teacherServlets;

import databaseOperations.TeacherOperations;
import factory.TeacherFactory;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A Servlet to handle adding new Teachers to the database.
 */
@WebServlet(name = "AddTeacherServlet", urlPatterns = {"/teacherServlets/AddTeacherServlet"})
public class AddTeacherServlet extends HttpServlet {

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
        if(!request.getParameter("firstName").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("first_name_error","This field is required!");
        }
        if(!request.getParameter("lastName").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("last_name_error","This field is required!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/teacher/create.jsp").forward(request, response);
        } else {
            Teacher teacher = TeacherFactory.createTeacherFromForm(request);
            TeacherOperations.insertTeacher(teacher);
        }
        response.sendRedirect(request.getContextPath() + "/teacherServlets/ShowAllTeachersServlet");
    }
}
