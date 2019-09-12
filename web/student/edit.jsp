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
        Student student = (Student) request.getAttribute("student");
        ArrayList<Faculty> faculties = (ArrayList<Faculty>) request.getAttribute("faculties");
        ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
        ArrayList<Boolean> isInThisCourse = (ArrayList<Boolean>) request.getAttribute("isInThisCourse");
    %>
    <form action="<%=request.getContextPath()%>/studentServlets/PostEditStudentServlet" method="get"
          accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%=student.getId()%>">
        <label>New first name of the Student:</label>
        <br>
        <input type="text" name="firstName" required="required" class="textField" value="<%=student.getFirstName()%>">
        <br>
        <%
            if(request.getAttribute("first_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("first_name_error") + "</p>");
        %>
        <br>
        <label>New last name of the Student:</label>
        <br>
        <input type="text" name="lastName" required="required" class="textField" value="<%=student.getLastName()%>">
        <br>
        <%
            if(request.getAttribute("last_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("last_name_error") + "</p>");
        %>
        <br>
        <label>New amount of credits:</label>
        <br>
        <input type="text" name="credits" required="required" class="textField" value="<%=student.getCredits()%>">
        <br>
        <%
            if(request.getAttribute("credits_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("credits_error") + "</p>");
        %>
        <br>
        <label>Choose the new faculty for this student:</label>
        <br><br>
        <select name="faculty">
            <%
                out.print("<option value=\"null\" ");
                if (student.getFacultyId() == null) {
                    out.print("selected");
                }
                out.print(">-</option>");
                for (Faculty faculty : faculties) {
                    out.print("<option value=\"" + faculty.getId() + "\"");
                    if (student.getFacultyId() != null && student.getFacultyId() == faculty.getId()) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(faculty.getName() + " with ID " + faculty.getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <label>Choose the new set of courses for this student:</label>
        <br><br>
        <select name="courses" multiple>
            <%
                for (int i = 0; i < courses.size(); i++) {
                    out.print("<option value=\"" + courses.get(i).getId() + "\"");
                    if (isInThisCourse.get(i)) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(courses.get(i).getName() + " with ID " + courses.get(i).getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <input type="submit" value="Edit Student" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/studentServlets/ShowAllStudentsServlet">
        <input type="submit" value="Back to All Students" class="smallButton"/>
    </form>
</body>
</html>
