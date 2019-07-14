<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Participants</title>

    <style>
        <%@include file="css/style.css" %>
    </style>
</head>
<body>
<%@include file="navigation.jsp" %>
<label>Project:</label>
<select name="projects">
    <option selected hidden>Choose here</option>
    <c:forEach var="project" items="${projects}">
        <option value="${project.id}">
                ${project.projectName}
        </option>
    </c:forEach>
</select><br/>

<label>User:</label>
<select name="user">
    <option selected hidden >Choose here</option>
    <c:forEach var="user" items="${users}">
        <option value="${user.id}">
                ${user.userName}
        </option>
    </c:forEach>
</select><br/>

</body>
</html>
