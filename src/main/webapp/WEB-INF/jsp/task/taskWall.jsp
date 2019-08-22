<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">

    <label>Sprint:</label><br/>
    <select name="sprintId" class="text-dark" value="${sprints}">
        <%--    <option selected hidden >Choose here</option>--%>
        <c:forEach var="sprint" items="${sprints}">
            <option value="${sprint.id}">
                    ${sprint.startDate} - ${sprint.finishDate}
            </option>
        </c:forEach>
    </select><br/>


    <div class="row">
        <div class="col-3 bg-light border sm-2 m-1">
            Project Name
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
    <c:forEach var="task" items="${sprint.tasks}">
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

                        ---${week}---${weekNumber}---
                    <c:if test="${week<weekNumber}">
                    <div class="card bg-warning m-2 text-center p-1">
                        </c:if>
                        <c:if test="${!(week<weekNumber)}">
                        <div class="card bg-secondary m-2 text-center p-1">
                            </c:if>

                            <div>
                                <a href="/task/show?taskId=${task.id}" >${task.name}</a>
                                <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${weekNumber}"
                                   class="btn btn-outline-light btn-sm">
                                    <span class="glyphicon glyphicon-triangle-right"></span></a>
                            </div>
                            <div class="card-footer">
                                <div><span
                                        class="glyphicon glyphicon-star"></span>${task.sprint.planedStoryPoints}

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
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=TO_DO&backToWall=${weekNumber}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-left"></span></a>
                                            ${task.name}<a
                                            href="/task/progressToNextChange?taskId=${task.id}&progress=DONE&backToWall=${weekNumber}"
                                            class="btn btn-outline-light btn-sm">
                                        <span class="glyphicon glyphicon-triangle-right"></span></a>
                                    </div>
                                    <div class="card-footer">
                                        <div><span
                                                class="glyphicon glyphicon-star"></span>${task.sprint.planedStoryPoints}


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
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${weekNumber}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-left"></span></a>
                                            ${task.name}

                                    </div>
                                    <div class="card-footer">
                                        <div><span
                                                class="glyphicon glyphicon-star"></span>${task.sprint.planedStoryPoints}

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