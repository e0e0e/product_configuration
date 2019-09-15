<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container bg-info">
    <c:if test="${errorMessage!=null}">

        <div style="background-color: red;">${errorMessage}</div>

    </c:if>

    <form method="post" action="/taskChange?taskId=${task.id}" class="bg-info m-5 p-5 text-light">
        <h1>Edit Tasks from project <b>${task.project.projectName}</b></h1>
        <label>Task name:</label><br/>
        <input type="text" class="text-dark" name="name" value="${task.name}"><br/>
        <label>Description:</label><br/>
        <textarea rows="4" columns="80" class="text-dark" name="description">${task.description}</textarea><br/>


        <label>Sprint:</label><br/>
        <select name="sprintId" class="text-dark" value="${sprints}">
            <option value="${task.sprint.id}" selected>${task.sprint.startDate} - ${task.sprint.finishDate}</option>
            <c:forEach var="sprint" items="${sprints}">
                <option value="${sprint.id}">
                        ${sprint.startDate} - ${sprint.finishDate}
                </option>
            </c:forEach>

        </select><a href="/sprint"><span class="glyphicon glyphicon-plus text-light p-1 m-1">Sprint</span></a><br/>


        <label>Story points:</label><br/>
        <input type="number" name="storyPoints" class="text-dark p-2" min="1" max="500"
               VALUE="${task.storyPoints}"><br/>


        <label>Weight (1-5):</label><br/>
        <input type="number" class="text-dark p-2" name="weight" min="1" max="5" VALUE="${task.weight}"><br/>

        <label>User:</label><br/>
        <select name="userId" class="text-dark" value="${users[0].id}">
            <option value="${task.user.id}" selected>${task.user.username}</option>

            <c:forEach var="user" items="${users}">
                <option value="${user.id}">
                        ${user.username}
                </option>
            </c:forEach>
        </select><br/>

        <input type="submit" class="text-dark" value="Save">


    </form>
</div>

