<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<br/>
<div class="container">
    <table class="table table-dark">
        <tr>
            <th>task Name</th>
            <th>Description</th>

            <th>Start Date</th>
            <th>Finish Date</th>
            <th>Story points</th>
            <th>Responsible user</th>
            <th>Progress</th>
            <th>Edit</th>
        </tr>
        <c:forEach var="task" items="${tasks}">


            <tr>
                <td>${task.name} </td>
                <td>${task.description} </td>
                <td>${task.sprint.startDate} </td>
                <td>${task.sprint.finishDate} </td>
                <td>${task.storyPoints} </td>
                <td>${task.user.username} </td>
                <td>${task.progress}
                    <a href="/task/progressChange?taskId=${task.id}" class="btn btn-outline-light btn-sm text-right">
                        <span class="glyphicon glyphicon-edit"></span></a>


                </td>
                <td><a href="/task/delete?taskId=${task.id}&projectId=${param.projectId}" class="btn btn-outline-light btn-sm text-right">
                    <span class="glyphicon glyphicon-trash"></span></a></td>
            </tr>

        </c:forEach>


    </table>
</div>
<div class="container-fluid float-left ">
    <div class="flex-nowrap">
        <div class="progress" style="width:${timeLine.size()*35}px">
            <c:forEach var="date" items="${timeLine}">
                <div class="progress-bar progress-bar-white" role="progressbar"
                     style="width:${(1/timeLine.size())*100}%">
                        ${date}
                </div>
            </c:forEach>
        </div>


        <c:forEach var="table" items="${timeTableMap}">
            <div class="progress" style="width:${timeLine.size()*35}px">
                <div class="progress-bar progress-bar" role="progressbar"
                     style="width:${table.value.daysToStart*35}px; background-color: #f4eee9">

                </div>
                <div class="progress-bar progress-bar-success" role="progressbar"
                     style="width:${(table.value.daysToFinish-table.value.daysToStart)*35}px">
                        ${table.value.task.name}

                </div>

            </div>


        </c:forEach>
    </div>
</div>