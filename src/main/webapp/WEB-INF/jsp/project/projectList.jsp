<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project list</title>
    <style><%@include file="../users/css/style.css"%></style>
</head>
<body>
<%@include file="../users/navigation.jsp" %>

<c:if test="${deleteProjectResults==true}">
    <h1>Deleted Project successfull</h1>
</c:if>
<table>
    <tr>
        <th>Id</th>
        <th>Project Name</th>
        <th>description</th>
        <th>Admin Name</th>
        <th>Participants</th>
        <th>Operation</th>
    </tr>
    <c:forEach var="project" items="${projects}">


        <tr>
            <td>${project.id}</td>
            <td> ${project.projectName} </td><td>${project.description} </td><td>${project.user.userName}</td>
            <td>${project.showUsersInProject()}</td>
            <td><a href="/project/delete?projectId=${project.id}">Delete</a>
                <a href="/project/participant?projectId=${project.id}">Add participant</a></td>
<%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
        </tr>



    </c:forEach>
</table>

<c:if test="${addingToProjectID!=null}">
<form method="post" action="/participants">
    <h1>Adding to project ${addingToProjectID.get().projectName} User:</h1>
    <label>Project id:</label>
    <input type="text" name="projestID" value ="${addingToProjectID.get().id}" readonly>
    <select name="user">
        <option selected hidden >Choose here</option>
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">
                    ${user.userName}
            </option>

        </c:forEach>
    </select><br/>
    <input type="submit" value="Dodaj" name="">
</form>
</c:if>
</body>
</html>
