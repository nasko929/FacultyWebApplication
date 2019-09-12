<%@ page import="model.Faculty" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Show All Faculties</title>
</head>
<body>
    <% if (request.getAttribute("error") != null) {
        out.print("<p class=\"errorPar\">" + request.getAttribute("error") + "</p>");
    }
    %>
    <table class="showAllTable">
        <tr>
            <th>&emsp;Id&emsp;</th>
            <th>&emsp;&emsp;&emsp;&emsp;Name&emsp;&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;Change Record&emsp;&emsp;</th>
            <th>&emsp;&emsp;Delete Record&emsp;&emsp;</th>
        </tr>
        <%
            ArrayList<Faculty> faculties = (ArrayList<Faculty>) request.getAttribute("faculties");
            for (Faculty faculty : faculties) {
                out.print("<tr><td>" + faculty.getId() + "</td><td>" + faculty.getName() + "</td>");
                String formHtml = "<form action=\"" + request.getContextPath() +
                        "/facultyServlets/PreEditFacultyServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + faculty.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Change\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
                formHtml = "<form action=\"" + request.getContextPath() +
                        "/facultyServlets/DeleteFacultyServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + faculty.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Delete\" onclick=\"return " +
                        "confirm('Do you really want to delete this Faculty?')\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/faculty/create.jsp">
        <input type="submit" value="Add new Faculty" class="smallButton"/>
    </form>
    <form action="<%=request.getContextPath()%>/">
        <input type="submit" value="Back to Home Page" class="smallButton"/>
    </form>
</body>
</html>
