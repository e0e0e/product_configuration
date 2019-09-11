<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container bg-info">
<form method="post" action="/tasks?projectId=${project.id}" class="bg-info m-5 p-5 text-light">
    <h1>Add Tasks to project <b>${project.projectName}</b></h1>
    <label>Task name:</label><br/>
    <input type="text" class="text-dark" name="name"><br/>
    <label>Description:</label><br/>
    <input type="text" class="text-dark" name="description"><br/>


<label>Sprint:</label><br/>
    <select name="sprintId" class="text-dark" value="${sprints}">
        <%--    <option selected hidden >Choose here</option>--%>
        <c:forEach var="sprint" items="${sprints}">
            <option value="${sprint.id}">
                    ${sprint.startDate} - ${sprint.finishDate}
            </option>
        </c:forEach>

    </select><a href="/sprint"><span class="glyphicon glyphicon-plus text-light p-1 m-1">Sprint</span></a><br/>


        <label>Story points:</label><br/>
        <input type="number" name="storyPoints" class="text-dark p-2" min="1" max="500" VALUE="1"><br/>


        <label>Weight (1-5):</label><br/>
        <input type="number" class="text-dark p-2" name="weight" min="1" max="5" VALUE="1"><br/>

    <label>User:</label><br/>
    <%@include file="../userPicker.jsp" %>

    <input type="submit" class="text-dark" value="Dodaj">


</form>
</div>

