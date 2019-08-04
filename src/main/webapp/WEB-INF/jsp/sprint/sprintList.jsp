<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: grzes
  Date: 24.07.19
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sprintList</title>
    <style><%@include file="../css/style.css"%></style>
    <%@include file="../css/boot.jsp" %>
</head>
<body>
<%@include file="../navigation.jsp" %>
<table>
    <tr>
        <th>Id</th>
        <th>Start Date</th>
        <th>Finish Date</th>
        <th>Story points</th>
    </tr>
    <c:forEach var="sprint" items="${sprints}">


        <tr>
            <td>${sprint.id}</td>
            <td> ${sprint.startDate} </td>
            <td>${sprint.finishDate} </td>
            <td>${sprint.planedStoryPoints}</td>
<%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
<%--            <td>${sprint.toString()}</td>--%>
        </tr>



    </c:forEach>
</table>
</body>
</html>
