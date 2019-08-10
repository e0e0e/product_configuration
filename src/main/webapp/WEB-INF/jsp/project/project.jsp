<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container bg-info text-light">
<h1>Create Project</h1>

<form method="post" action="/project">

    <label>Project Name:</label><br/>
    <input type="text" class="text-dark" name="projectName"><br/>
    <label>Project Description:</label><br/>
    <textarea rows="4" columns="50" class="text-dark" name="description">Description ...</textarea><br/>
    <label>User:</label><br/>
    <select name="user" class="text-dark">
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">
                    ${user.username}
            </option>
        </c:forEach>
    </select><br/>
    <input type="submit" class="text-dark" value="Dodaj">



</form>
</div>
