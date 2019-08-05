<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table>
    <tr>
        <th>Id</th>
        <th>task Name</th>
        <th>User Name</th>
        <th>Start Date</th>
        <th>Finish Date</th>
        <th>Story points</th>
    </tr>
    <c:forEach var="task" items="${tasks}">


        <tr>
            <td>${task.id}</td>
            <td> ${task.name} </td>
            <td>${task.user.username} </td>
            <td>${task.sprint.startDate} </td>
            <td>${task.sprint.finishDate} </td>
            <td>${task.sprint.planedStoryPoints} </td>
                <%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
                <%--            <td>${sprint.toString()}</td>--%>
        </tr>
        <tr>
            <td>Task Description</td>
            <td>${task.description}</td>

        </tr>



    </c:forEach>
</table>