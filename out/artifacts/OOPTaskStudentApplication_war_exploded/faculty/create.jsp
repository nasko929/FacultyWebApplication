<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Create New Faculty</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/facultyServlets/AddFacultyServlet" method="get" accept-charset="UTF-8">
        <label>Name of the Faculty:</label>
        <br>
        <input type="text" name="name" placeholder="Name" required="required" >
        <br>
        <%
            if(request.getAttribute("name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("name_error") + "</p>");
        %>
        <br>
        <input type="submit" value="Create New Faculty" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/facultyServlets/ShowAllFacultiesServlet">
        <input type="submit" value="Back to All Faculties" class="smallButton"/>
    </form>
</body>
</html>
