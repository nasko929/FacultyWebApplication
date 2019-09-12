package servlets.courseServlets;

import databaseOperations.CourseOperations;
import databaseOperations.StudentCourseOperations;
import factory.CourseFactory;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle the information that user gave to it and send it back to database to update the Course.
 */
@WebServlet(name = "PostEditCourseServlet", urlPatterns = {"/courseServlets/PostEditCourseServlet"})
public class PostEditCourseServlet extends HttpServlet {

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

        int courseId = Integer.parseInt(request.getParameter("id"));
        boolean isTheFormProperlyFilled = true;
        if (!request.getParameter("name").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("name_error", "This field is required!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/courseServlets/PreEditCourseServlet")
                    .forward(request, response);
        } else {
            Course course = CourseFactory.createCourseFromForm(request);
            String teacherId = request.getParameter("teacher");
            if (!teacherId.equals("null")) {
                course.setTeacherId(Integer.parseInt(teacherId));
            } else {
                course.setTeacherId(null);
            }
            if (CourseOperations.updateCourse(course, courseId) == 0) {
                request.setAttribute("error", "There was an error in the database while making query. Excuse us.");
            }
            StudentCourseOperations.deleteByCourseId(courseId);
            String[] ids = request.getParameterValues("students");
            if (ids != null) {
                ArrayList<Integer> intIds = new ArrayList<>();
                for (String id : ids) {
                    intIds.add(Integer.parseInt(id));
                }
                for (int id : intIds) {
                    StudentCourseOperations.insertIntoStudentCourse(id, courseId);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/courseServlets/ShowAllCoursesServlet");
    }
}
