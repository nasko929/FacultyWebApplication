package servlets.teacherServlets;

import databaseOperations.CourseOperations;
import databaseOperations.TeacherOperations;
import factory.TeacherFactory;
import model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle the information that user gave to it and send it back to database to update the Teacher.
 */
@WebServlet(name = "PostEditTeacherServlet", urlPatterns = {"/teacherServlets/PostEditTeacherServlet"})
public class PostEditTeacherServlet extends HttpServlet {

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
            getServletContext().getRequestDispatcher("/teacherServlets/PreEditTeacherServlet")
                    .forward(request,response);
        } else {
            int teacherId = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = TeacherFactory.createTeacherFromForm(request);
            TeacherOperations.updateTeacher(teacher, teacherId);
            CourseOperations.setAllTeacherIdToNullInCourse(teacherId);
            String[] ids = request.getParameterValues("courses");
            if (ids != null) {
                ArrayList<Integer> intIds = new ArrayList<>();
                for (String id : ids) {
                    intIds.add(Integer.parseInt(id));
                }
                CourseOperations.updateAllTeacherIdsByListOfStudentIds(intIds, teacherId);
            }
        }
        response.sendRedirect(request.getContextPath() + "/teacherServlets/ShowAllTeachersServlet");
    }
}
