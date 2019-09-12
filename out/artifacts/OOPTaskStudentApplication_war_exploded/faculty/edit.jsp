<%@ page import="model.Faculty" %>
<%@ page import="model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Edit Faculty</title>
</head>
<body>
    <%
        Faculty faculty = (Faculty) request.getAttribute("faculty");
        ArrayList<Student> students = (ArrayList<Student>) request.getAttribute("students");
    %>
    <form action="<%=request.getContextPath()%>/facultyServlets/PostEditFacultyServlet" method="get"
          accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%=faculty.getId()%>">
        <label>New name of the Faculty:</label>
        <br>
        <input type="text" name="name" required="required" class="textField" value="<%=faculty.getName()%>">
        <br>
        <%
            if(request.getAttribute("name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("name_error") + "</p>");
        %>
        <br>
        <label>Choose the new set of Students for this faculty:</label>
        <br><br>
        <select name="students" multiple>
            <%
                for(Student student: students) {
                    out.print("<option value=\""+student.getId()+"\"");
                    if(student.getFacultyId() == faculty.getId()) {
                        out.print("selected>");
                    } else {
                        out.print(">");
                    }
                    out.print(student.getFirstName() + " " + student.getLastName() + " with ID " + student.getId());
                    out.print("</option>");
                }
            %>
        </select>
        <br><br>
        <input type="submit" value="Edit Faculty" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/facultyServlets/ShowAllFacultiesServlet">
        <input type="submit" value="Back to All Faculties" class="smallButton"/>
    </form>
</body>
</html>
