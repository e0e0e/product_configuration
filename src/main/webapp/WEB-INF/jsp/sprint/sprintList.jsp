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
            <td class="p-2">${sprint.id}</td>
            <td class="p-2"> ${sprint.startDate} </td>
            <td class="p-2">${sprint.finishDate} </td>
            <td class="p-2">${sprint.planedStoryPoints}</td>
            <td class="p-2"><a href="/sprint/delete?sprintId=${sprint.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
<%--            <td>${sprint.toString()}</td>--%>
        </tr>



    </c:forEach>
</table>
</div>
