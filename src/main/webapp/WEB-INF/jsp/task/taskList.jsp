<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<table>
    <tr>
<%--        <th>Id</th>--%>
        <th class="col-sm-4 bg-primary">task Name</th>
<%--        <th>User Name</th>--%>
        <th>Start Date</th>
        <th>Finish Date</th>
        <th>Story points</th>
    </tr>
    <c:forEach var="task" items="${project.task}">


        <tr>
<%--            <td>${task.id}</td>--%>
            <td class="bg-primary"> ${task.name} </td>
<%--            <td>${task.user.username} </td>--%>
            <td>${task.sprint.startDate} </td>
            <td class="bg-primary">${task.sprint.finishDate} </td>
            <td>${task.sprint.planedStoryPoints} </td>


                <%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
                <%--            <td>${sprint.toString()}</td>--%>
            <td class="bg-primary">${Period.between(task.sprint.finishDate,task.sprint.startDate).getDays()}</td>
        </tr>


<%--        <tr class="bg-warning">--%>
<%--            <td>Task Description</td>--%>
<%--            <td>${task.description}</td>--%>

<%--        </tr>--%>



    </c:forEach>

</table>
<%--<h1>minStartDate ${start.time-finish.time}" </h1>--%>
<%--<h1>maxFinishDate ${maxFinishDate}</h1>--%>