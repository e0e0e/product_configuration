<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Participants</title>

    <style>
        <%@include file="../user/css/style.css" %>
    </style>
</head>
<body>
<%@include file="../user/navigation.jsp" %>

<form method="post" action="/project/participant">
<label>Project:</label>
<select name="projectId" value="${projects[0].projectName}">
<%--    <option selected hidden>Choose here</option>--%>
    <c:forEach var="project" items="${projects}">
        <option value="${project.id}">
                ${project.projectName}
        </option>
    </c:forEach>
</select><br/>

<label>User:</label>
<select name="userId" value="${users[0].id}">
<%--    <option selected hidden >Choose here</option>--%>
    <c:forEach var="user" items="${users}">
        <option value="${user.id}">
                ${user.userName}
        </option>
    </c:forEach>
</select><br/>
<input type="submit" value="Dodaj">

</body>
</html>
