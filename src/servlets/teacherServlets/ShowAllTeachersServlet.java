package servlets.teacherServlets;

import databaseOperations.TeacherOperations;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to fetch all the data about Teachers from the database and send it to JSP for displaying.
 */
@WebServlet(name = "ShowAllTeachersServlet", urlPatterns = {"/teacherServlets/ShowAllTeachersServlet"})
public class ShowAllTeachersServlet extends HttpServlet {

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
        ArrayList<Teacher> teachers = TeacherOperations.getAllTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/teacher/showAll.jsp").forward(request, response);
    }
}
