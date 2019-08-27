<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container bg-warning">

    <label>Sprint:</label>
    <div style="display: inline-block">
        <div class="dropdown" style="display: inline-block">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                ${tasks[0].sprint.startDate} - ${tasks[0].sprint.finishDate}
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <c:forEach var="sprint" items="${sprints}">
                    <a class="dropdown-item" href="/taskWall?sprintId=${sprint.id}">${sprint.startDate}
                        - ${sprint.finishDate}</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div style="display: inline-block"><a href="/taskWallNext?sprintId=${tasks[0].sprint.id}"
                                          class="btn btn-outline-info btn-sm">
        <span class="glyphicon glyphicon-triangle-right"></span></a></div>


    <div>Story Points: ${tasks[0].sprint.planedStoryPoints}</div>

    <div class="row">
        <div class="col-3 bg-light border sm-2 m-1">
            Project
        </div>
        <div class="col bg-light border sm-2 m-1">
            To Do
        </div>
        <div class="col bg-light border sm-2 m-1">
            In Progress
        </div>
        <div class="col bg-light border sm-2 m-1">
            Done
        </div>


    </div>
    <%--    <c:set var="currentProject" value="${''}"/>--%>
    <c:forEach var="project" items="${wallMap}">
    <c:forEach var="task" items="${project.value.tasksToDo}">
<%--    <c:forEach var="taskToDo" items="${wall.tasksToDo}">--%>
    <div>${task.name}</div>
    </c:forEach>
<%--    </c:forEach>--%>
    </c:forEach>
    <c:forEach var="project" items="${projectsInWeek}">
        <%--        <br/>--%>
    <div class="row border mt-1">

        <div class="col-3">

            <div class="card bg-light text-center h-100 ">
                <a href="/project/show?projectId=${project.key.id}" class="my-auto">${project.key.projectName}</a>

                </span>
            </div>
        </div>
            <%--            <div class="col">--%>
        <div class="col">
            <div class="row">
                <div class="col">

                    <c:forEach var="task" items="${project.value}">

                    <c:if test="${task.progress=='TO_DO'}">

                    <fmt:parseDate value="${task.sprint.startDate}" type="date"
                                   pattern="yyyy-MM-dd" var="parsedDate"/>
                    <fmt:formatDate value="${parsedDate}" type="date" pattern="w"
                                    var="week"/>

                    <c:if test="${week<weekNumber}">
                    <div class="card bg-warning m-2 text-center p-1">
                        </c:if>
                        <c:if test="${!(week<weekNumber)}">
                        <div class="card bg-secondary m-2 text-center p-1">
                            </c:if>

                            <div>
                                    ${task.name}
                                <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${task.sprint.id}"
                                   class="btn btn-outline-light btn-sm">
                                    <span class="glyphicon glyphicon-triangle-right"></span></a>
                            </div>
                            <div class="card-footer">
                                <div><span
                                        class="glyphicon glyphicon-star"></span>${task.storyPoints}

                                    responsible: <a href="/userProfile?userId=${task.user.id}"
                                                    class="btn btn-outline-light"> ${task.user.username}</a>


                                </div>
                            </div>

                        </div>


                        </c:if>

                        </c:forEach>

                    </div>
                    <div class="col">

                        <c:forEach var="task" items="${project.value}">

                            <c:if test="${task.progress=='IN_PROGRESS'}">
                                <div class="card bg-primary m-2 text-center p-1">
                                    <div>
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=TO_DO&backToWall=${task.sprint.id}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-left"></span></a>
                                            ${task.name}<a
                                            href="/task/progressToNextChange?taskId=${task.id}&progress=DONE&backToWall=${task.sprint.id}"
                                            class="btn btn-outline-light btn-sm">
                                        <span class="glyphicon glyphicon-triangle-right"></span></a>
                                    </div>
                                    <div class="card-footer">
                                        <div><span
                                                class="glyphicon glyphicon-star"></span>${task.storyPoints}


                                            responsible: <a href="/userProfile?userId=${task.user.id}"
                                                            class="btn btn-outline-light"> ${task.user.username}</a>


                                        </div>
                                    </div>
                                </div>
                            </c:if>

                        </c:forEach>

                    </div>
                    <div class="col">

                        <c:forEach var="task" items="${project.value}">

                            <c:if test="${task.progress=='DONE'}">
                                <div class="card bg-success m-2 text-center p-1">

                                    <div>
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${task.sprint.id}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-left"></span></a>
                                            ${task.name}

                                    </div>
                                    <div class="card-footer">
                                        <div><span
                                                class="glyphicon glyphicon-star"></span>${task.storyPoints}

                                            responsible: <a href="/userProfile?userId=${task.user.id}"
                                                            class="btn btn-outline-light"> ${task.user.username}</a>
                                                <%--                                            <div>${task.sprint.startDate}</div>--%>


                                        </div>
                                    </div>
                                </div>
                            </c:if>

                        </c:forEach>

                    </div>
                        <%--                <div class="col bg-primary  m-1">--%>
                        <%--                    <c:if test="${task.progress=='IN_PROGRESS'}">--%>
                        <%--                        ${task.name}<br/>--%>
                        <%--                    </c:if>--%>

                        <%--                </div>--%>

                        <%--                <div class="col bg-info  m-1">--%>
                        <%--                    <c:if test="${task.progress=='DONE'}">--%>
                        <%--                        ${task.name}<br/>--%>
                        <%--                    </c:if>--%>

                        <%--                </div>--%>
                </div>

            </div>
        </div>


            <%--        </div>--%>
        </c:forEach>

    </div>