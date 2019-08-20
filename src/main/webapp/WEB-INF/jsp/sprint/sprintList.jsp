<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container bg-info">
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
            <td><a href="/sprint/delete?sprintId=${sprint.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
<%--            <td>${sprint.toString()}</td>--%>
        </tr>



    </c:forEach>
</table>
</div>
