<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container bg-info">
    <h1>For project <b>${projects.projectName}<b/> add user:</h1>
    <%--    <input method="post" action="/userSearch">--%>

    <h2>Filter user by email: </h2>
    <form method="post" action="/participant?projectId=${param.projectId}">
    <input type="text" name="filterUserByEmail" class="m-3">
    <input type="submit" value="Filter">
    <%--    <a href="/userSearch" class="btn btn-outline-dark btn-sm">--%>
    <%--    <span class="glyphicon glyphicon-search"></span></a>--%>
    </form>


    <form method="post" class="text-white bg-primary p-10" action="/project/participant?projectId=${projects.id}">
        <%--        <%@include file="../userPicker.jsp" %>--%>
        <c:forEach var="user" items="${users}">
            <input type="radio" name="userId" class="m-2" value="${user.id}"><span
                class="p-5">${user.username}, email: ${user.email}</span><br/>
        </c:forEach>
        <input type="submit" class="text-dark" value="Add User">
    </form>

</div>
