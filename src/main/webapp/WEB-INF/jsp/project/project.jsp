<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container bg-info text-light">
<h1>Create Project</h1>
    <sec:authentication var="loggedUser" property="principal"/>
<%--    you're logged as: ${loggedUser.username}--%>
<form method="post" action="/project?username=${loggedUser.username}">

    <label>Project Name:</label><br/>
    <input type="text" class="text-dark" name="projectName"><br/>
    <label>Project Description:</label><br/>
    <textarea rows="4" columns="80" class="text-dark" name="description"></textarea><br/>
    <label>User:</label><br/>



<%--    <select name="user" class="text-dark">--%>
<%--        <c:forEach var="user" items="${users}">--%>
<%--            <option value="${user.id}">--%>
<%--                    ${user.username}--%>
<%--            </option>--%>
<%--        </c:forEach>--%>
    </select><br/>

    <input type="submit" class="text-dark" value="Dodaj">



</form>
</div>
