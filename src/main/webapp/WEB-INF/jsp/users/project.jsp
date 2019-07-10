<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzes
  Date: 10.07.19
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project</title>
</head>
<body>
<h1>Create Project</h1>

<form method="post" action="/project">

    <label>Project Name:</label>
    <input type="text" name="projectName"><br/>
    <label>Project Description:</label>
    <input type="text" name="description"><br/>
    <label>User:</label>
    <select name="user">
        <c:forEach var="user" items="${users}">
            <option value="${user.userName}">
                    ${user.userName}
            </option>
        </c:forEach>
    </select><br/>
    <input type="submit" value="Dodaj">



</form>


</body>
</html>
