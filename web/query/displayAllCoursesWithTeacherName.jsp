<%@ page import="model.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>Check all students with >40 credits</title>
</head>
<body>
<table class="showAllTable">
    <tr>
        <th>&emsp;Course Id&emsp;</th>
        <th>&emsp;&emsp;&emsp;&emsp;Course Name&emsp;&emsp;&emsp;&emsp;</th>
        <th>&emsp;&emsp;&emsp;&emsp;Teacher's First Name&emsp;&emsp;&emsp;&emsp;</th>
        <th>&emsp;&emsp;&emsp;Teacher's Last Name&emsp;&emsp;&emsp;</th>
    </tr>
    <%
        ArrayList<CourseTeacher> courseTeachers = (ArrayList<CourseTeacher>) request.getAttribute("courseTeachers");
        for (CourseTeacher courseTeacher: courseTeachers) {
            out.print("<tr><td>" + courseTeacher.getCourseId() + "</td><td>" + courseTeacher.getCourseName()
                    + "</td><td>" + courseTeacher.getTeacherFirstName()
                    + "</td><td>" + courseTeacher.getTeacherLastName() + "</td>");
        }
    %>
</table>
<form action="<%=request.getContextPath()%>/chooseQuery.jsp">
    <input type="submit" value="Back" class="smallButton"/>
</form>

</body>
</html>
