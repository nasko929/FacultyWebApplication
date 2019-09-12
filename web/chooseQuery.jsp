<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
    <title>Queries</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/queryServlets/getAllCoursesByGivenStudent/GetAllStudentsServlet">
        <input type="submit" value="Check all courses of a student" class="bigButton"/>
    </form>

    <form action="<%=request.getContextPath()%>/queryServlets/getAllStudentsByGivenFaculty/GetAllFacultiesServlet">
        <input type="submit" value="Check all students of a faculty" class="bigButton"/>
    </form>

    <%--  DONE  --%>
    <form action="<%=request.getContextPath()%>/queryServlets/GetAllStudentsWithOver40CreditsServlet">
        <input type="submit" value="Check all students with more than 40 credits" class="bigButton"/>
    </form>

    <form action="<%=request.getContextPath()%>/queryServlets/getAllStudentsByFacAndCourse/GetAllFacAndCoursesServlet">
        <input type="submit" value="Check all students in given faculty studying given course" class="bigButton"/>
    </form>

    <%--  DONE  --%>
    <form action="<%=request.getContextPath()%>/queryServlets/GetAllCoursesWithTeachersNameServlet">
        <input type="submit" value="Check all courses with teachers' names" class="bigButton"/>
    </form>
    <form action="<%=request.getContextPath()%>/">
        <input type="submit" value="Back to Home Page" class="smallButton"/>
    </form>
</body>
</html>
