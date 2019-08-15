<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">
    Week number is:
    <a href="/taskWall?weekNumber=${weekNumber-1}" class="btn btn-outline-info btn-sm">
        <span class="glyphicon glyphicon-triangle-left"></span></a>
    ${weekNumber}
    <a href="/taskWall?weekNumber=${weekNumber+1}" class="btn btn-outline-info btn-sm">
        <span class="glyphicon glyphicon-triangle-right"></span></a>
    <div class="row">
        <div class="col-3 bg-primary sm-2 m-1">
            Project Name
        </div>
        <div class="col bg-info sm-2 m-1">
            To Do
        </div>
        <div class="col bg-primary sm-2 m-1">
            In Progress
        </div>
        <div class="col bg-info sm-2 m-1">
            Done
        </div>


    </div>
    <%--    <c:set var="currentProject" value="${''}"/>--%>
    <c:forEach var="project" items="${projectsInWeek}">
        <%--        <br/>--%>
        <div class="row border mt-1">

            <div class="col-3">

                <div class="card bg-info m-2 text-center p-1">
                        ${project.key.projectName}

                </div>
            </div>
                <%--            <div class="col">--%>
            <div class="col">
                <div class="row">
                    <div class="col">

                        <c:forEach var="task" items="${project.value}">

                            <c:if test="${task.progress=='TO_DO'}">
                                <div class="card bg-primary m-2 text-center p-1">
                                    <div>
                                            ${task.name}
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${weekNumber}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-right"></span></a>
                                    </div>
                                    <div class="card-footer">
                                        responsible: <a href="/userProfile?userId=${task.user.id}" class="btn btn-outline-light btn-sm"> ${task.user.username}</a>
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
                                        responsible: ${task.user.username}
                                    </div>
                                </div>
                            </c:if>

                        </c:forEach>

                    </div>
                    <div class="col">

                        <c:forEach var="task" items="${project.value}">

                            <c:if test="${task.progress=='DONE'}">
                                <div class="card bg-primary m-2 text-center p-1">

                                    <div>
                                        <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS&backToWall=${weekNumber}"
                                           class="btn btn-outline-light btn-sm">
                                            <span class="glyphicon glyphicon-triangle-left"></span></a>
                                            ${task.name}

                                    </div>
                                    <div class="card-footer">
                                        responsible: ${task.user.username}
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