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
            <td class="bg-primary"> ${task.name} </td>
            <td>${task.sprint.startDate} </td>
            <td class="bg-primary">${task.sprint.finishDate} </td>
            <td>${task.sprint.planedStoryPoints} </td>
        </tr>

    </c:forEach>



</table>
<%--<div class="progress">--%>
<%--<c:forEach begin="1" end="12" varStatus="loop">--%>

<%--        <div class="progress-bar progress-bar-white" role="progressbar"--%>
<%--             style="width:${(1/12)*100}%">--%>
<%--                ${loop.index+4}--%>
<%--        </div>--%>





<%--</c:forEach>--%>
<%--</div>--%>
<div class="container">

    <div class="progress">
        <c:forEach var="date" items="${timeLine}">
            <div class="progress-bar progress-bar-white" role="progressbar"
                 style="width:${(1/timeLine.size())*100}%">
                    ${date}
            </div>
        </c:forEach>
    </div>




<c:forEach var="table" items="${timeTableMap}">
    <div class="progress">
        <div class="progress-bar progress-bar" role="progressbar"
             style="width:${(table.value.daysToStart/table.value.numberOfDays)*100}%; background-color: #f4eee9">

        </div>
        <div class="progress-bar progress-bar-success" role="progressbar"
             style="width:${((table.value.daysToFinish-table.value.daysToStart)/table.value.numberOfDays)*100}%">
                ${table.value.task.name} ${table.value.numberOfDays}

        </div>

    </div>


</c:forEach>
</div>


<%--<div class="container">--%>

<%--        <div class="row">--%>
<%--            <c:forEach var="date" items="${timeLine}">--%>
<%--            <div class="col-sm-1 bg-warning text-sm">--%>
<%--               ${date}--%>
<%--            </div>--%>
<%--            </c:forEach>--%>
<%--        </div>--%>


<%--</div>--%>
<%--<div class="container">--%>
<%--<c:forEach var="table" items="${timeTableMap}">--%>
<%--    <div class="row">--%>

<%--            <div class="col-sm-${table.value.daysToStart} bg-light text-sm">--%>
<%--                  daysto statry  ${table.value.daysToStart}--%>
<%--            </div>--%>
<%--            <div class="col-sm-${table.value.daysToFinish-table.value.daysToStart+1} bg-info text-sm">--%>
<%--                   days to finish  ${table.value.daysToFinish}--%>
<%--            </div>--%>
<%--        <div class="col-sm bg-dark text-sm">--%>

<%--        </div>--%>

<%--    </div>--%>
<%--    </c:forEach>--%>

<%--</div>--%>


<%--private LocalDate startDate;--%>
<%--private Integer daysToStart;--%>
<%--private Integer daysToFinish;--%>
<%--private Integer numberOfDays;--%>
<%--private Long sprintId;--%>