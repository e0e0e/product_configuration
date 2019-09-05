<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container bg-info text-light">
    <h1>Edit Project</h1>
    <sec:authentication var="loggedUser" property="principal"/>
    <form method="post" action="/projectChange?userId=${loggedUser.id}&projectId=${param.projectId}">

        <label>Project Name:</label><br/>
        <input type="text" class="text-dark" name="projectName" value="${project.projectName}"><br/>
        <label>Project Description:</label><br/>
        <textarea rows="4" columns="80" class="text-dark" name="description">${project.description}</textarea><br/>
        </select><br/>

        <input type="submit" class="text-dark" value="Save">


    </form>
</div>