<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project list</title>
    <style><%@include file="css/style.css"%></style>
</head>
<body>
<%@include file="navigation.jsp" %>

<c:if test="${deleteProjectResults==true}">
    <h1>Deleted Project successfull</h1>
</c:if>
<table>
    <tr>
        <th>Id</th>
        <th>Project Name</th>
        <th>description</th>
        <th>Admin Name</th>
        <th>Operation</th>
    </tr>
    <c:forEach var="project" items="${projects}">


        <tr>
            <td>${project.id}</td>
            <td> ${project.projectName} </td><td>${project.description} </td><td>${project.user.userName}</td>
            <td><a href="/project/delete?projectId=${project.id}">Delete</a></td>
<%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
        </tr>



    </c:forEach>
</table>

</body>
</html>
