package servlets.studentServlets;

import databaseOperations.StudentCourseOperations;
import databaseOperations.StudentOperations;
import factory.StudentFactory;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle the information that user gave to it and send it back to database to update the Student.
 */
@WebServlet(name = "PostEditStudentServlet", urlPatterns = {"/studentServlets/PostEditStudentServlet"})

public class PostEditStudentServlet extends HttpServlet {

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
            getServletContext().getRequestDispatcher("/studentServlets/PreEditStudentServlet")
                    .forward(request, response);
        } else {
            int studentId = Integer.parseInt(request.getParameter("id"));
            Student student = StudentFactory.createStudentFromForm(request);
            String facultyId = request.getParameter("faculty");
            if (!facultyId.equals("null")) {
                student.setFacultyId(Integer.parseInt(request.getParameter("faculty")));
            } else {
                student.setFacultyId(null);
            }
            StudentOperations.updateStudent(student, studentId);
            StudentCourseOperations.deleteRecordByStudentId(studentId);
            String[] ids = request.getParameterValues("courses");
            if (ids != null) {
                ArrayList<Integer> intIds = new ArrayList<>();
                for (String id : ids) {
                    intIds.add(Integer.parseInt(id));
                }
                for (int id : intIds) {
                    StudentCourseOperations.insertIntoStudentCourse(studentId, id);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/studentServlets/ShowAllStudentsServlet");
    }
}
