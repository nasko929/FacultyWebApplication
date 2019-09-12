<%@ page import="model.Teacher" %>
<%@ page import="model.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Edit Teacher</title>
</head>
<body>
    <%
        Teacher teacher = (Teacher) request.getAttribute("teacher");
        ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
    %>
    <form action="<%=request.getContextPath()%>/teacherServlets/PostEditTeacherServlet" method="get"
          accept-charset="UTF-8">

        <input type="hidden" name="id" value="<%=teacher.getId()%>">
        <label>New first name of the Teacher:</label>
        <br>
        <input type="text" name="firstName" required="required" class="textField" value="<%=teacher.getFirstName()%>">
        <br>
        <%
            if(request.getAttribute("first_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("first_name_error") + "</p>");
        %>
        <br>
        <label>New last name of the Teacher:</label>
        <br>
        <input type="text" name="lastName" required="required" class="textField" value="<%=teacher.getLastName()%>">
        <br>
        <%
            if(request.getAttribute("last_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("last_name_error") + "</p>");
        %>
        <br>
        <label>Choose the new set of courses for this teacher:</label>
        <br><br>
        <select name="courses" multiple>
            <%
                for (Course course : courses) {
                    out.print("<option value=\"" + course.getId() + "\"");
                    if (course.getTeacherId() != null && course.getTeacherId() == teacher.getId()) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(course.getName() + " with ID " + course.getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <input type="submit" value="Edit Teacher" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/teacherServlets/ShowAllTeachersServlet">
        <input type="submit" value="Back to All Teachers" class="smallButton"/>
    </form>
</body>
</html>
