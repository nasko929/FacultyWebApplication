package servlets.courseServlets;

import databaseOperations.CourseOperations;
import databaseOperations.StudentCourseOperations;
import databaseOperations.StudentOperations;
import databaseOperations.TeacherOperations;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to collect all the needed data to display for the user to change and update the Course.
 */
@WebServlet(name = "PreEditCourseServlet", urlPatterns = {"/courseServlets/PreEditCourseServlet"})
public class PreEditCourseServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws Servlet Exception.
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
     * @throws ServletException - throws Servlet Exception.
     * @throws IOException      - throws IOException.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Course course = CourseOperations.getCourseById(Integer.parseInt(request.getParameter("id")));
        ArrayList<Student> students = StudentOperations.getAllStudents();
        ArrayList<Teacher> teachers = TeacherOperations.getAllTeachers();
        ArrayList<Boolean> isStudyingCurrentCourse = new ArrayList<>();
        request.setAttribute("course", course);
        request.setAttribute("teachers", teachers);
        request.setAttribute("students", students);
        for (Student student : students) {
            isStudyingCurrentCourse.add(StudentCourseOperations.ifRecordExists(student.getId(), course.getId()));
        }
        request.setAttribute("isStudyingCurrentCourse", isStudyingCurrentCourse);
        request.getRequestDispatcher("/course/edit.jsp").forward(request, response);
    }
}
