<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Edit Course</title>
</head>
<body>
    <%
        Course course = (Course) request.getAttribute("course");
        ArrayList<Teacher> teachers = (ArrayList<Teacher>) request.getAttribute("teachers");
        ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
        ArrayList<Boolean> isStudyingCurrentCourse = (ArrayList<Boolean>) request.getAttribute("isStudyingCurrentCourse");
    %>
    <form action="<%=request.getContextPath()%>/courseServlets/PostEditCourseServlet" method="get"
          accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%=course.getId()%>">
        <label>New name of the Course:</label>
        <br>
        <input type="text" name="name" required="required" class="textField" value="<%=course.getName()%>">
        <br>
        <%
            if(request.getAttribute("name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("name_error") + "</p>");
        %>
        <br>
        <label>Choose the new teacher for this course:</label>
        <br><br>
        <select name="teacher">
            <%
                out.print("<option value=\"null\" ");
                if (course.getTeacherId() == null) {
                    out.print("selected");
                }
                out.print(">-</option>");
                for(Teacher teacher: teachers) {
                    out.print("<option value=\""+teacher.getId()+"\"");
                    if(course.getTeacherId() != null && course.getTeacherId() == teacher.getId()) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(teacher.getFirstName() + " " + teacher.getLastName()
                            + " with ID " + teacher.getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <label>Choose the new set of Students for this course:</label>
        <br><br>
        <select name="students" multiple>
            <%
                for(int i = 0; i < students.size(); i++) {
                    out.print("<option value=\""+students.get(i).getId()+"\"");
                    if(isStudyingCurrentCourse.get(i)) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(students.get(i).getFirstName() + " " + students.get(i).getLastName()
                            + " with ID " + students.get(i).getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <input type="submit" value="Edit Course" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/courseServlets/ShowAllCoursesServlet">
        <input type="submit" value="Back to All Courses" class="smallButton"/>
    </form>
</body>
</html>
