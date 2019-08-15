<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <div class="container">
        Week number is:
        <a href="/taskWall?weekNumber=${weekNumber-1}" class="btn btn-outline-dark btn-sm">
            <span class="glyphicon glyphicon-triangle-left"></span></a>
        ${weekNumber}
        <a href="/taskWall?weekNumber=${weekNumber+1}" class="btn btn-outline-dark btn-sm">
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

            <div class="row">

                <div class="col-3 bg-primary sm-2 m-1">
                        ${project.key.projectName}
                </div>
                    <%--            <div class="col">--%>
                <div class="col">
                    <div class="row">
                        <div class="col bg-secondary m-1">
                            <c:forEach var="task" items="${project.value}">

                                <c:if test="${task.progress=='TO_DO'}">

                                    ${task.name}<br/>


                                </c:if>

                            </c:forEach>
                        </div>
                        <div class="col bg-secondary m-1">
                            <c:forEach var="task" items="${project.value}">

                                <c:if test="${task.progress=='IN_PROGRESS'}">

                                    ${task.name}<br/>


                                </c:if>

                            </c:forEach>
                        </div>
                        <div class="col bg-secondary m-1">
                            <c:forEach var="task" items="${project.value}">

                                <c:if test="${task.progress=='DONE'}">

                                    ${task.name}<br/>


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