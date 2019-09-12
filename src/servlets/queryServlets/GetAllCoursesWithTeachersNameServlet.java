package servlets.queryServlets;

import databaseOperations.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle the query about getting all courses with their teacher's names and sending the info to JSP.
 */
@WebServlet(name = "GetAllCoursesWithTeachersNameServlet", urlPatterns = {"/queryServlets/" +
        "GetAllCoursesWithTeachersNameServlet"})
public class GetAllCoursesWithTeachersNameServlet extends HttpServlet {

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
        ArrayList<CourseTeacher> courseTeachers = QueryOperations.getAllCoursesAndTeachersNames();
        request.setAttribute("courseTeachers", courseTeachers);
        request.getRequestDispatcher("/query/displayAllCoursesWithTeacherName.jsp").forward(request, response);
    }
}
