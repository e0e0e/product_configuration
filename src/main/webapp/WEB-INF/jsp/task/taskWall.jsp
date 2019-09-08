<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:useBean id="chrono" class="java.time.temporal.ChronoUnit"/>--%>
<%@ page import="java.time.temporal.ChronoUnit" %>

<div class="container">

    <div>
        <div class="float-left">
            <label>Sprint:</label>
            <div style="display: inline-block"><a
                    href="/taskWallPrevious?sprintId=${wall.theSprint.id}&previous=${param.previous}"
                    class="btn btn-outline-info btn-sm">
                <span class="glyphicon glyphicon-triangle-left"></span></a></div>

            <div style="display: inline-block">
                <div class="dropdown" style="display: inline-block">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                            data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        ${wall.theSprint.startDate} - ${wall.theSprint.finishDate}

                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <c:forEach var="sprint" items="${wall.sprints}">
                            <a class="dropdown-item"
                               href="/taskWall?sprintId=${sprint.id}&previous=${param.previous}">${sprint.startDate}
                                - ${sprint.finishDate}</a>
                        </c:forEach>
                        <a class="dropdown-item" href="/sprint">Add sprint</a>
                    </div>
                </div>
            </div>
            <div style="display: inline-block"><a
                    href="/taskWallNext?sprintId=${wall.theSprint.id}&previous=${param.previous}"
                    class="btn btn-outline-info btn-sm">
                <span class="glyphicon glyphicon-triangle-right"></span></a></div>
        </div>
        <div>
            <a href="/sprint"><span
                    class="glyphicon glyphicon-plus p-1 m-1">Sprint</span></a>
        </div>
    </div>
    <br/>
    <div class="float-right">
        <c:if test="${param.previous=='unfinished' or empty param.previous}">
            <a href="/taskWall?sprintId=${wall.theSprint.id}&previous=current"><span
                    class="glyphicon glyphicon-eye-close"></span> unfinished</a>
        </c:if>
        <c:if test="${param.previous=='current'}">
            <a href="/taskWall?sprintId=${wall.theSprint.id}&previous=unfinished"><span
                    class="glyphicon glyphicon-eye-open"></span>
                unfinished</a>
        </c:if>

    </div>
    <br/>


    <div>Story Points: ${wall.theSprint.planedStoryPoints}</div>

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
    <c:forEach var="project" items="${wall.projectsInWeek}">
    <div class="row border mt-1">

        <div class="col-3">

            <div class="card bg-light text-center h-100 ">
                <a href="/project/show?projectId=${project.key.id}" class="my-auto">${project.key.projectName}</a>

                </span>
            </div>
        </div>
        <div class="col">
            <div class="row">
                <div class="col">

                    <c:forEach var="task" items="${project.value}">

                    <c:if test="${task.progress=='TO_DO'}">


                    <c:if test="${task.sprint.finishDate<wall.now}">
                    <div class="card bg-danger m-2 text-center p-1">
                        Delayed ${ChronoUnit.DAYS.between(task.sprint.finishDate,wall.now)} days
                        from ${task.sprint.finishDate}

                        </c:if>
                        <c:if test="${!(task.sprint.finishDate<wall.now)}">
                        <div class="card bg-secondary m-2 text-center p-1">
                            </c:if>

                            <div>

                                    ${task.name}
                                <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${task.sprint.id}&previous=${param.previous}"
                                   class="btn btn-outline-light btn-sm">
                                    <span class="glyphicon glyphicon-triangle-right"></span></a>
                            </div>
                            <div class="card-footer">
                                <div class="row">
                                    <div class="col">
                                        <span class="glyphicon glyphicon-star p-2"></span>${task.storyPoints}

                                    </div>
                                    <div class="col text-center float-right">
                                        <img class="img-circle bg-dark p-1"
                                             src="${resourcePath}${task.user.avatar}"
                                             height="40" width="40"/>
                                        <a href="/userProfile?userId=${task.user.id}"
                                           class="btn btn-outline-light d-block"> ${task.user.username}</a>

                                    </div>
                                </div>
                            </div>

                        </div>


                        </c:if>

                        </c:forEach>

                    </div>
                    <div class="col">

                        <c:forEach var="task" items="${project.value}">

                        <c:if test="${task.progress=='IN_PROGRESS'}">
                        <c:if test="${task.sprint.finishDate<wall.now}">
                        <div class="card bg-warning m-2 text-center p-1">
                            Delayed ${ChronoUnit.DAYS.between(task.sprint.finishDate,wall.now)} days
                            from ${task.sprint.finishDate}
                            </c:if>
                            <c:if test="${!(task.sprint.finishDate<wall.now)}">
                            <div class="card bg-primary m-2 text-center p-1">
                                </c:if>

                                <div>
                                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=TO_DO&backToWall=${task.sprint.id}&previous=${param.previous}"
                                       class="btn btn-outline-light btn-sm">
                                        <span class="glyphicon glyphicon-triangle-left"></span></a>

                                        ${task.name}
                                        <%--                                    <c:if test="${user.username==task.user}">--%>
                                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=DONE&backToWall=${task.sprint.id}&previous=${param.previous}"
                                       class="btn btn-outline-light btn-sm">
                                        <span class="glyphicon glyphicon-triangle-right"></span></a>
                                        <%--                                    </c:if>--%>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col">
                                            <span class="glyphicon glyphicon-star p-2"></span>${task.storyPoints}

                                        </div>
                                        <div class="col text-center float-right">
                                            <img class="img-circle bg-dark p-1"
                                                 src="${resourcePath}${task.user.avatar}"
                                                 height="40" width="40"/>
                                            <a href="/userProfile?userId=${task.user.id}"
                                               class="btn btn-outline-light"> ${task.user.username}</a>

                                        </div>
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
                                            <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${task.sprint.id}&previous=${param.previous}"
                                               class="btn btn-outline-light btn-sm">
                                                <span class="glyphicon glyphicon-triangle-left"></span></a>
                                                ${task.name}

                                        </div>
                                        <div class="card-footer">

                                            <div class="row">
                                                <div class="col">
                                                    <span class="glyphicon glyphicon-star p-2"></span>${task.storyPoints}

                                                </div>
                                                <div class="col text-center float-right">
                                                    <img class="img-circle bg-dark p-1"
                                                         src="${resourcePath}${task.user.avatar}"
                                                         height="40" width="40"/>
                                                    <a href="/userProfile?userId=${task.user.id}"
                                                       class="btn btn-outline-light"> ${task.user.username}</a>

                                                </div>
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

