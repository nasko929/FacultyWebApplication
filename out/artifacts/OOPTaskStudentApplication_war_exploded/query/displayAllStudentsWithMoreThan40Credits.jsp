<%@ page import="model.Student" %>
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
        <th>&emsp;Id&emsp;</th>
        <th>&emsp;&emsp;&emsp;&emsp;First Name&emsp;&emsp;&emsp;&emsp;</th>
        <th>&emsp;&emsp;&emsp;&emsp;Last Name&emsp;&emsp;&emsp;&emsp;</th>
        <th>&emsp;&emsp;&emsp;Credits&emsp;&emsp;&emsp;</th>
        </tr>
    <%
        ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
        for (Student student: students) {
        out.print("<tr><td>" + student.getId() + "</td><td>" + student.getFirstName() + "</td><td>"
        + student.getLastName() +"</td><td>" + student.getCredits() + "</td>");
        }
    %>
    </table>
    <form action="<%=request.getContextPath()%>/chooseQuery.jsp">
        <input type="submit" value="Back" class="smallButton"/>
    </form>

</body>
</html>
