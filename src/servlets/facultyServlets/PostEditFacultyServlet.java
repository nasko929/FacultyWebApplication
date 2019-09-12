package servlets.facultyServlets;

import databaseOperations.FacultyOperations;
import databaseOperations.StudentOperations;
import factory.FacultyFactory;
import model.Faculty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Servlet to handle the information that user gave to it and send it back to database to update the Faculty.
 */
@WebServlet(name = "PostEditFacultyServlet", urlPatterns = {"/facultyServlets/PostEditFacultyServlet"})
public class PostEditFacultyServlet extends HttpServlet {

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

        int facultyId = Integer.parseInt(request.getParameter("id"));
        boolean isTheFormProperlyFilled = true;
        System.out.println(request.getParameter("name"));
        if(!request.getParameter("name").matches(".+")) {
            isTheFormProperlyFilled = false;
            request.setAttribute("name_error", "This field is required!");
        }
        if (!isTheFormProperlyFilled) {
            getServletContext().getRequestDispatcher("/facultyServlets/PreEditFacultyServlet")
                    .forward(request,response);
        } else {
            Faculty faculty = FacultyFactory.createFacultyFromForm(request);
            System.out.println(faculty.getName());
            FacultyOperations.updateFaculty(faculty, facultyId);
            StudentOperations.setAllFacultyIdToNullInStudent(facultyId);
            String[] ids = request.getParameterValues("students");
            if (ids != null) {
                ArrayList<Integer> intIds = new ArrayList<>();
                for (String id : ids) {
                    intIds.add(Integer.parseInt(id));
                }
                StudentOperations.updateAllFacultyIdsByListOfStudentIds(intIds, facultyId);
            }
        }
        response.sendRedirect(request.getContextPath() + "/facultyServlets/ShowAllFacultiesServlet");
    }
}
