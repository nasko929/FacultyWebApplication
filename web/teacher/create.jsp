<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Create New Teacher</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/teacherServlets/AddTeacherServlet" method="get" accept-charset="UTF-8">
        <label>First name of the Teacher:</label>
        <br>
        <input type="text" name="firstName" placeholder="First Name" required="required">
        <br>
        <%
            if(request.getAttribute("first_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("first_name_error") + "</p>");
        %>
        <br>
        <label>Last name of the Teacher:</label>
        <br>
        <input type="text" name="lastName" placeholder="Last Name" required="required">
        <br>
        <%
            if(request.getAttribute("last_name_error") != null)
                out.print("<p class=\"errorText\">" + request.getAttribute("last_name_error") + "</p>");
        %>
        <br>
        <input type="submit" value="Create New Teacher" class="smallButton">
    </form>
    <form action="<%=request.getContextPath()%>/teacherServlets/ShowAllTeachersServlet">
        <input type="submit" value="Back to All Teachers" class="smallButton"/>
    </form>
</body>
</html>
