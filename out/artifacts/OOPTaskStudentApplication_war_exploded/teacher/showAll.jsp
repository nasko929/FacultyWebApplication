<%@ page import="model.Teacher" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Show All Teachers</title>
</head>
<body>
    <% if (request.getAttribute("error") != null) {
        out.print("<p class=\"errorPar\">" + request.getAttribute("error") + "</p>");
    }
    %>
    <table class="showAllTable">
        <tr>
            <th>&emsp;Id&emsp;</th>
            <th>&emsp;&emsp;&emsp;&emsp;First Name&emsp;&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;&emsp;&emsp;Last Name&emsp;&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;Change Record&emsp;&emsp;</th>
            <th>&emsp;&emsp;Delete Record&emsp;&emsp;</th>
        </tr>
        <%
            ArrayList<Teacher> teachers = (ArrayList<Teacher>) request.getAttribute("teachers");
            for (Teacher teacher: teachers) {
                out.print("<tr><td>" + teacher.getId() + "</td><td>" + teacher.getFirstName() + "</td>");
                out.print("<td>" + teacher.getLastName() + "</td>");
                String formHtml = "<form action=\"" + request.getContextPath() +
                        "/teacherServlets/PreEditTeacherServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + teacher.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Change\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
                formHtml = "<form action=\"" + request.getContextPath() +
                        "/teacherServlets/DeleteTeacherServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + teacher.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Delete\" onclick=\"return " +
                        "confirm('Do you really want to delete this Teacher?')\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/teacher/create.jsp">
        <input type="submit" value="Add new Teacher" class="smallButton"/>
    </form>
    <form action="<%=request.getContextPath()%>/">
        <input type="submit" value="Back to Home Page" class="smallButton"/>
    </form>
</body>
</html>
