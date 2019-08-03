<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Participants</title>

    <style>
        <%@include file="../css/style.css" %>
</style>
    <%@include file="../css/boot.jsp" %>

</head>
<body>
<%@include file="../user/navigation.jsp" %>
<h1>For project <b>${projects.projectName}<b/> add user:</h1>
<form method="post" action="/project/participant?projectId=${projects.id}">



<select name="userId" value="${users[0].id}">
<%--    <option selected hidden >Choose here</option>--%>
    <c:forEach var="user" items="${users}">
        <option value="${user.id}">
                ${user.username}
        </option>
    </c:forEach>
</select><br/>
<input type="submit" value="Dodaj">
</form>

</body>
</html>
