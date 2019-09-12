package servlets.teacherServlets;

import databaseOperations.CourseOperations;
import databaseOperations.TeacherOperations;
import model.Course;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to collect all the needed data to display for the user to change and update the Teacher.
 */
@WebServlet(name = "PreEditTeacherServlet", urlPatterns = {"/teacherServlets/PreEditTeacherServlet"})
public class PreEditTeacherServlet extends HttpServlet {

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
        ArrayList<Course> courses = CourseOperations.getAllCourses();
        Teacher teacher = TeacherOperations.getTeacherById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("courses", courses);
        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("/teacher/edit.jsp").forward(request, response);
    }
}
