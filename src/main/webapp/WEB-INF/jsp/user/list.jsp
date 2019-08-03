<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="../css/style.css" %>
    </style>
    <%@include file="../css/boot.jsp" %>

</head>
<body>
<%@include file="navigation.jsp" %>

<c:if test="${createUserResult==true}">

    <h2>User has been successfully created</h2>

</c:if>
<c:if test="${deleteUserResults==true}">

    <h2>User has been successfully deleted</h2>

</c:if>


<%--<table>--%>
<%--    <tr>--%>
<%--        <th>Id</th>--%>
<%--        <th>Email</th>--%>
<%--        <th>Login</th>--%>
<%--        <th>User Name</th>--%>
<%--        <th>Operations</th>--%>
<%--        <th>Admin in projects</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="user" items="${users}">--%>


<%--        <tr>--%>
<%--            <td>${user.id}</td>--%>
<%--            <td> ${user.email} </td>--%>
<%--            <td>${user.login} </td>--%>
<%--            <td>${user.username}</td>--%>
<%--            <td><a href="/users/delete?userId=${user.id}">Delete</a></td>--%>
<%--            <td>${user.toString()}</td>--%>
<%--        </tr>--%>


<%--    </c:forEach>--%>
<%--</table>--%>

<div class="container">

    <h3>Users</h3>

    <div class="row">
        <c:forEach var="user" items="${users}">
            <div class="col-sm-4">

                <div class="card text-dark bg-warning m-1">

                    <div class="card-header bg-info text-left text-light"><h4>User name: ${user.username}<br/>
                        login:${user.login}</h4></div>

                    <div class="card-body ">

                            <%--                    <h5 class="card-title">Services Title 1</h5>--%>

                        <p class="card-text text-left">email: ${user.email}  </p>
                                <br/>
                        <p class="card-text text-left">In projects: ${user.toString()}  </p>

                    </div>

                    <div class="card-footer bg-info text-right text-danger">

                        <a href="/project/user?projectId=${user.id}" class="btn btn-outline-light btn-sm">
                            <span class="glyphicon glyphicon-trash"></span></a>
                            <%--                        <a href="/participant?projectId=${project.id}" class="btn btn-outline-light btn-sm">--%>
                            <%--                            <span class="glyphicon glyphicon-plus"></span></a>--%>
                        <a href="/project/edit?projectId=${user.id}" class="btn btn-outline-light btn-sm">
                            <span class="glyphicon glyphicon-edit"></span></a>
                            <%--                        <div class="btn btn-outline-light btn-sm">--%>
                            <%--                                ${project.showUsersInProject()}</div>--%>

                    </div>

                </div>

            </div>
        </c:forEach>


    </div>

</div>


</body>
</html>
