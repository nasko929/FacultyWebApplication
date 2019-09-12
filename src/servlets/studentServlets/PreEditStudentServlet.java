package servlets.studentServlets;

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
 * A Servlet to collect all the needed data to display for the user to change and update the Student.
 */
@WebServlet(name = "PreEditStudentServlet", urlPatterns = {"/studentServlets/PreEditStudentServlet"})
public class PreEditStudentServlet extends HttpServlet {

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
        Student student = StudentOperations.getStudentById(Integer.parseInt(request.getParameter("id")));
        ArrayList<Course> courses = CourseOperations.getAllCourses();
        ArrayList<Faculty> faculties = FacultyOperations.getAllFaculties();
        ArrayList<Boolean> isInThisCourse = new ArrayList<>();
        request.setAttribute("student", student);
        request.setAttribute("faculties", faculties);
        request.setAttribute("courses", courses);
        for (Course course : courses) {
            isInThisCourse.add(StudentCourseOperations.ifRecordExists(student.getId(), course.getId()));
        }
        request.setAttribute("isInThisCourse", isInThisCourse);
        request.getRequestDispatcher("/student/edit.jsp").forward(request, response);
    }
}
