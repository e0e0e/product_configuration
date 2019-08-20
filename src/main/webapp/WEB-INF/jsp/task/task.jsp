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


        <c:set var="now" value="<%= new java.util.Date()%>"/>
        <br/>
        <label>To be done in week: </label>
        <input type="number" class="text-dark p-2" name="week" min="1" max="52"
               VALUE="<fmt:formatDate type="date" value="${now}" pattern="w"/>">
        <label>Year:</label>
        <input type="number" class="text-dark p-2" name="year"
               min="<fmt:formatDate type="date" value="${now}" pattern="YYYY"/>" max="2050"
               VALUE="<fmt:formatDate type="date" value="${now}" pattern="YYYY"/>"><br/>




        <label>Story points:</label><br/>
        <input type="number" name="storyPoints" class="text-dark p-2" min="1" max="500" VALUE="1"><br/>


        <label>Weight (1-5):</label><br/>
        <input type="number" class="text-dark p-2" name="weight" min="1" max="5" VALUE="1"><br/>




        <label>User:</label><br/>
        <%@include file="../userPicker.jsp" %>

        <input type="submit" class="text-dark " value="Dodaj">


    </form>
</div>
