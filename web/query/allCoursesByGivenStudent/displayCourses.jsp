<%@ page import="model.Course" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>All the courses</title>
</head>
<body>
    <%
        ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
    %>
    <table class="showAllTable">
    <tr>
        <th>&emsp;Course's Name&emsp;</th>
    </tr>
        <%
            for(Course course: courses) {
                out.print("<tr><td>"+course.getName()+"</td></tr>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/chooseQuery.jsp">
        <input type="submit" value="Back" class="smallButton"/>
    </form>
</body>
</html>
