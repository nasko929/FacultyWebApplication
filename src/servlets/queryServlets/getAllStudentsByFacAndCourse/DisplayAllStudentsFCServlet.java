package servlets.queryServlets.getAllStudentsByFacAndCourse;

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
 * A Servlet to get all the students that came from the query about
 * the faculty_id and course_id and send them to JSP to be displayed.
 */
@WebServlet(name = "DisplayAllStudentsFCServlet", urlPatterns = {"/queryServlets/getAllStudentsByFacAndCourse" +
        "/DisplayAllStudentsFCServlet"})
public class DisplayAllStudentsFCServlet extends HttpServlet {

    /**
     * A method which is triggered when the servlet gets a POST request.
     *
     * @param request  - request.
     * @param response - response.
     * @throws ServletException - throws ServletException.
     * @throws IOException      - throws IOException.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
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
        int courseId = Integer.parseInt(request.getParameter("course"));
        int facultyId = Integer.parseInt(request.getParameter("faculty"));
        ArrayList<Student> students = QueryOperations.getStudentsByGivenFacultyIdAndCourseId(facultyId,courseId);
        request.setAttribute("students",students);

        request.getRequestDispatcher("/query/allStudentsByGivenFacAndCourse/" +
                "displayStudents.jsp").forward(request,response);
    }
}
