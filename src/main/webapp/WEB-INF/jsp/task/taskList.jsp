<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Finish Date</th>
        <th>Story points</th>
    </tr>
    <c:forEach var="task" items="${tasks}">


        <tr>
            <td>${task.id}</td>
            <td> ${task.name} </td>
            <td>${task.user.username} </td>
                <%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
                <%--            <td>${sprint.toString()}</td>--%>
        </tr>



    </c:forEach>
</table>