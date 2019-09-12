<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Create New Course</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/courseServlets/AddCourseServlet" method="get" accept-charset="UTF-8">
        <label>Name of the Course:</label>
        <br>
        <input type="text" name="name" placeholder="Name" required="required">
        <br>
        <%
            if(request.getAttribute("name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("name_error") + "</p>");
        %>
        <br>
        <input type="submit" value="Create New Course" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/courseServlets/ShowAllCoursesServlet">
        <input type="submit" value="Back to All Courses" class="smallButton"/>
    </form>
</body>
</html>
