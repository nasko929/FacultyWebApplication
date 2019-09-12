<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>All the students</title>
</head>
<body>
<%
    ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
%>
<table class="showAllTable">
    <tr>
        <th>&emsp;&emsp;Student's first name&emsp;&emsp;</th>
        <th>&emsp;&emsp;Student's last name&emsp;&emsp;</th>
        <th>&emsp;&emsp;Student's credits&emsp;&emsp;</th>
    </tr>
    <%
        for(Student student: students) {
            out.print("<tr><td>"+student.getFirstName()+"</td><td>" + student.getLastName() + "</td><td>");
            out.print(student.getCredits()+"</td></tr>");
        }
    %>
</table>
<form action="<%=request.getContextPath()%>/chooseQuery.jsp">
    <input type="submit" value="Back" class="smallButton"/>
</form>
</body>
</html>
