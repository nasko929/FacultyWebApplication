<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Show All Students</title>
</head>
<body>
    <% if (request.getAttribute("error") != null) {
        out.print("<p class=\"errorPar\">" + request.getAttribute("error") + "</p>");
    }
    %>
    </p>
    <table class="showAllTable">
        <tr>
            <th>&emsp;Id&emsp;</th>
            <th>&emsp;&emsp;&emsp;&emsp;First Name&emsp;&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;&emsp;&emsp;Last Name&emsp;&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;&emsp;Credits&emsp;&emsp;&emsp;</th>
            <th>&emsp;&emsp;Change Record&emsp;&emsp;</th>
            <th>&emsp;&emsp;Delete Record&emsp;&emsp;</th>
        </tr>
        <%
            ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
            for (Student student: students) {
                out.print("<tr><td>" + student.getId() + "</td><td>" + student.getFirstName() + "</td><td>"
                        + student.getLastName() +"</td><td>" + student.getCredits() + "</td>");
                String formHtml = "<form action=\"" + request.getContextPath() +
                        "/studentServlets/PreEditStudentServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + student.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Change\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
                formHtml = "<form action=\"" + request.getContextPath() +
                        "/studentServlets/DeleteStudentServlet\" method=\"post\">" +
                        "<input type=\"hidden\" name=\"id\" value=" + student.getId() + ">";
                formHtml += "<input type=\"submit\" value=\"Delete\" onclick=\"return " +
                        "confirm('Do you really want to delete this Student?')\" class=\"smallButton\"></form>";
                out.print("<td>" + formHtml + "</td>");
            }
        %>
    </table>
    <form action="<%=request.getContextPath()%>/student/create.jsp">
        <input type="submit" value="Add new Student" class="smallButton"/>
    </form>
    <form action="<%=request.getContextPath()%>/">
        <input type="submit" value="Back to Home Page" class="smallButton"/>
    </form>
</body>
</html>
