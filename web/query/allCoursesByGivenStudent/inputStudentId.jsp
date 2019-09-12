<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>Choose student</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/queryServlets/getAllCoursesByGivenStudent/DisplayAllCoursesServlet"
    method = "post">
        <label>Choose your fighter:</label>
        <br><br>
        <select name="student">
        <%
            ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
            for(Student student: students) {
                out.print("<option value=\"" + student.getId() + "\">");
                out.print(student.getFirstName() + " " + student.getLastName() + "</option>");
            }
        %>
        </select>
        <br><br>
        <input type="submit" value="Select current student" class="smallButton"/>
    </form>
    <form action="<%=request.getContextPath()%>/chooseQuery.jsp">
        <input type="submit" value="Back" class="smallButton"/>
    </form>
</body>
</html>
