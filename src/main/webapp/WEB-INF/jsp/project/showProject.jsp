<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="user" property="principal"/>
<div class="container">

    <div class="card text-dark bg-warning m-1">

        <div class="card-header bg-info text-left text-light">
            <a href="/project/show?projectId=${project.id}" class="nav-link text-light font-weight-bold">
                ${project.projectName}</a>
            
                <a href="/project/delete?projectId=${project.id}&userId=${user.id}" class="btn btn-outline-light btn-sm text-right" style="float: right;">
                    <span class="glyphicon glyphicon-trash"></span></a>

                <a href="/tasks?projectId=${project.id}" class="btn btn-outline-light btn-sm" style="float: right;">
                    <span class="glyphicon glyphicon-plus"></span><span class="glyphicon glyphicon-briefcase"></span></a>

            <a href="/project/edit?projectId=${project.id}" class="btn btn-outline-light btn-sm text-right" style="float: right;">
                <span class="glyphicon glyphicon-edit"></span></a>

        </div>


        <div class="card-body ">

            <%--                    <h5 class="card-title">Services Title 1</h5>--%>

            <p class="card-text text-left">${project.description} </p>

        </div>

        <div class="card-footer bg-info text-right text-danger">

            <div class="text-light">
                admin: ${project.user.username}
            </div>
            <div class="text-light">
                In Project: ${project.showUsersInProject()}
            </div>

        </div>

    </div>
    <br/>
        <div class="row">
            <div class="col-4 bg-info border">
                To Do
            </div>
            <div class="col-4 bg-primary border">
                In Progress
            </div>
            <div class="col-4 bg-info border">
                Done
            </div>


        </div>


        <div class="row">
            <div class="col-4 bg-info border">
                <c:forEach var="task" items="${tasksToDo}">
                    ${task.name} ${task.progress}
                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS" class="btn btn-outline-dark btn-sm">
                        <span class="glyphicon glyphicon-triangle-right"></span></a><br/>
                </c:forEach>

            </div>
            <div class="col-4 bg-primary border">

                <c:forEach var="task" items="${tasksInProgress}">
                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=TO_DO" class="btn btn-outline-dark btn-sm">
                        <span class="glyphicon glyphicon-triangle-left"></span></a>
                    ${task.name} ${task.progress}
                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=DONE" class="btn btn-outline-dark btn-sm">
                        <span class="glyphicon glyphicon-triangle-right"></span></a><br/>
                </c:forEach>

            </div>
            <div class="col-4 bg-info bg-info border">
                <c:forEach var="task" items="${tasksDone}">
                    <a href="/task/progressToNextChange?taskId=${task.id}&progress=IN_PROGRESS" class="btn btn-outline-dark btn-sm">
                        <span class="glyphicon glyphicon-triangle-left"></span></a>
                    ${task.name} ${task.progress}<br/>

                </c:forEach>
            </div>
        </div>
</div>
<%@include file="../task/taskList.jsp" %>
<hr>
<div class="container" >
    <c:forEach var="project" items="${projectsAud}">
        <div class="row">
            <div class="col">
                ${project[0].projectName}
            </div>
            <div class="col">
                    ${project[0].description}
            </div>
            <div class="col">
            <fmt:formatDate type="date" value="${project[1].revisionDate}" pattern="yyyy-MM-dd, HH:mm"/>
            </div>

            <div class="col">
                    ${project[1].username}
            </div>
        </div>

    </c:forEach>

</div>


