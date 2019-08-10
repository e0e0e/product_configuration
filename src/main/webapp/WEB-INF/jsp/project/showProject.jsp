<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">

    <div class="card text-dark bg-warning m-1">

        <div class="card-header bg-info text-left text-light">
            <a href="/project/show?projectId=${project.id}" class="nav-link text-light font-weight-bold">
                ${project.projectName}</a>
            
<%--            <div class="bg-info text-right text-danger" style="display: inline-block;">--%>
                <a href="/project/delete?projectId=${project.id}" class="btn btn-outline-light btn-sm text-right" style="float: right">
                    <span class="glyphicon glyphicon-trash"></span></a>

                <form action="/participant?projectId=${project.id}" method="post" style="display: inline-block;float: right;">
                    <button type="submit" value="your_value" class="btn btn-outline-light" >
                        <span class="glyphicon glyphicon-user"></span></button>
                </form>
                <a href="/tasks?projectId=${project.id}" class="btn btn-outline-light btn-sm" style="float: right;">
                    <span class="glyphicon glyphicon-time"></span></a>

                <a href="/project/edit?projectId=${project.id}" class="btn btn-outline-light btn-sm" style="float: right;">
                    <span class="glyphicon glyphicon-edit"></span></a>
<%--            </div>--%>
        </div>


        <div class="card-body ">

            <%--                    <h5 class="card-title">Services Title 1</h5>--%>

            <p class="card-text text-left">${project.description} </p>

        </div>

        <div class="card-footer bg-info text-right text-danger">

            <div class="text-light">
                admin: ${project.user.username}
            </div>
            <div class="btn btn-outline-light btn-sm">
                In Project: ${project.showUsersInProject()}
            </div>

        </div>

    </div>
</div>
<%@include file="../task/taskList.jsp" %>


