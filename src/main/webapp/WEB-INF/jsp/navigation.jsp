<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>

    <nav class="navbar navbar-expand-lg navbar-dark bg-info">
        <div class="container">
            <%--            <a class="navbar-brand" href="#">--%>
            <%--            <i class="fa d-inline fa-lg fa-circle-o"></i>--%>
            <b class="navbar-brand">PMC</b>
            </a>
            <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse"
                    data-target="#navbar11" aria-expanded="true">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="navbar-collapse collapse show" id="navbar11" style="">
                <ul class="navbar-nav mr-auto bg-info">
                    <li class="nav-item"><a class="nav-link text-light font-weight-bold" href="/users">Add User</a></li>
                    <%--                    <li class="nav-item"> <a class="nav-link text-light font-weight-bold" href="/project">Add Project</a> </li>--%>
                    <li class="nav-item"><a class="nav-link text-light font-weight-bold" href="/users/projectList">My Projects</a></li>
                    <li class="nav-item"><a class="nav-link text-primary font-weight-bold" href="/users/allProjectList">All Projects</a></li>
<%--                    <li class="nav-item"><a class="nav-link text-light font-weight-bold" href="/sprint">Sprint</a></li>--%>
<%--                    <li class="nav-item"><a class="nav-link text-light font-weight-bold" href="/sprintList">Sprint List</a></li>--%>
                    <li class="nav-item"><a class="nav-link text-light font-weight-bold" href="/taskWall">Wall</a></li>
                    <%--                    <li class="nav-item"> <a class="nav-link text-light font-weight-bold" href="/tasks">Add Task</a> </li>--%>
                    <%--                    <li class="nav-item"> <a class="nav-link text-light font-weight-bold" href="/taskList">Task List</a> </li>--%>
                </ul>
            </div>
        </div>
        <div style="text-align: right;display: inline-block" class="text-white m-2 p-2">
            <sec:authentication var="user" property="principal"/>
            you're logged as: <a href="/userProfile?userId=" class="btn btn-outline-light btn-sm"> ${user.username}</a>
            <br/>
            <a href="/logout">Log out</a>
            <br/>
        </div>
    </nav>
    <div>

    </div>
</div>