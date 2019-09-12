<%@ page import="model.Faculty" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>Choose student</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/queryServlets/getAllStudentsByGivenFaculty/DisplayAllStudentsServlet"
      method = "post">
    <label>Choose your faculty fighter:</label>
    <br><br>
    <select name="faculty">
        <%
            ArrayList<Faculty> faculties = (ArrayList<Faculty>) request.getAttribute("faculties");
            for(Faculty faculty: faculties) {
                out.print("<option value=\"" + faculty.getId() + "\">");
                out.print(faculty.getName() + "</option>");
            }
        %>
    </select>
    <br><br>
    <input type="submit" value="Select current faculty" class="smallButton"/>
</form>
<form action="<%=request.getContextPath()%>/chooseQuery.jsp">
    <input type="submit" value="Back" class="smallButton"/>
</form>
</body>
</html>
