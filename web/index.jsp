<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>Main Page</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/studentServlets/ShowAllStudentsServlet">
        <input type="submit" value="Show All Students" class="bigButton"/>
    </form>

    <form action="<%=request.getContextPath()%>/teacherServlets/ShowAllTeachersServlet">
        <input type="submit" value="Show All Teachers" class="bigButton"/>
    </form>

    <form action="<%=request.getContextPath()%>/courseServlets/ShowAllCoursesServlet">
        <input type="submit" value="Show All Courses" class="bigButton"/>
    </form>

    <form action="<%=request.getContextPath()%>/facultyServlets/ShowAllFacultiesServlet">
        <input type="submit" value="Show All Faculties" class="bigButton"/>
    </form>
    <form action="chooseQuery.jsp">
        <input type="submit" value="Go to queries" class="bigButton"/>
    </form>
</body>
</html>
