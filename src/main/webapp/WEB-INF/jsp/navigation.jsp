<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<div style="text-align: right;">--%>
<%--    <sec:authentication var="user" property="principal" />--%>
<%--    you're logged as: ${user.username}--%>
<%--    <br/>--%>
<%--    <a href="/logout">Log out</a>--%>
<%--    <br/>--%>
<%--</div>--%>



<div>
    <nav class="navbar navbar-expand-md navbar-dark bg-info">
<ul class="d-flex w-50 order-0">
<%--    <li><a href="/login">Login</a></li>--%>
        <li><a href="/users">Add User</a></li>
    <li class="navbar-toggler"><a class="nav-warning" href="/project">Add Project</a></li>
    <li class="navbar-info"><a class="nav-warning" href="/users/projectList">Projects List</a></li>
    <li class="nav-info"><a class="nav-warning" href="/users/list">Users List</a></li>
<%--    <li class="nav-info"><a class="nav-warning" href="/participants">Add Participants</a></li>--%>
    <li class="nav-info"><a class="nav-warning" href="/sprint">Add Sprint</a></li>
    <li class="nav-info"><a class="nav-warning" href="/sprintList">Sprint List</a></li>
    <li class="nav-info"><a class="nav-warning" href="/tasks">Add task</a></li>
</ul>
    </nav>

<%--    <nav class="navbar navbar-expand-md navbar-dark bg-primary">--%>
<%--        <div class="d-flex w-50 order-0">--%>

<%--            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsingNavbar">--%>
<%--                <span class="navbar-toggler-icon"></span>--%>
<%--            </button>--%>
<%--        </div>--%>
<%--        <div class="navbar-collapse collapse justify-content-center order-2" id="collapsingNavbar">--%>
<%--            <ul class="navbar-nav">--%>
<%--                <li class="nav-item active">--%>
<%--                    <a class="nav-link" href="#">Link <span class="sr-only">Home</span></a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="//codeply.com">Codeply</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Link</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <span class="navbar-text small text-truncate mt-1 w-50 text-right order-1 order-md-last">always show</span>--%>
<%--    </nav>--%>

</div>
<br/>