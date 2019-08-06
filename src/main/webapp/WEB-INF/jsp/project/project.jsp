<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Create Project</h1>

<form method="post" action="/project">

    <label>Project Name:</label>
    <input type="text" name="projectName"><br/>
    <label>Project Description:</label>
    <input type="text" name="description"><br/>
    <label>User:</label>
    <select name="user">
        <c:forEach var="user" items="${users}">
            <option value="${user.id}">
                    ${user.username}
            </option>
        </c:forEach>
    </select><br/>
    <input type="submit" value="Dodaj">



</form>

