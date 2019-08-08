<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container bg-info">
<form method="post" action="/tasks?projectId=${project.id}" class="bg-info m-5 p-5 text-light">
    <h1>Add Tasks for project <b>${project.projectName}</b></h1>
    <label>Task name:</label><br/>
    <input type="text" class="text-dark" name="name"><br/>
    <label>Description:</label><br/>
    <input type="text" class="text-dark" name="description"><br/>
    <label>Sprint:</label><br/>

    <c:set var="now" value="<%= new java.util.Date()%>"/>
    <%--    <p>Formatted Date (2): <fmt:formatDate type="date" value="${now}" pattern="mm-dd-yyyy"/></p>--%>

    <label>From:</label><br/>
    <input type="date" name="from" class="text-dark"
           value="C"><br/>
    <label>To:</label><br/>
    <input type="date" name="to" class="text-dark"
           value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>"><br/>
    <label>Story points:</label><br/>
    <%--    <input type="number" name="storyPoints" min="1" max="5" VALUE="1"><br/>--%>
    <%@include file="../pointsPicker.jsp" %>
    <%--    <input type="submit" value="Dodaj">--%>


    <label>Weight (1-5):</label><br/>
    <input type="number" class="text-dark" name="weight" min="1" max="5" VALUE="1"><br/>


    <%--    <label>Progress:</label><br/>--%>
    <%--    <input type="text" name="progress"><br/>--%>

    <label>User:</label><br/>
    <%@include file="../userPicker.jsp" %>

    <input type="submit" class="text-dark" value="Dodaj">


</form>
</div>
<%--private Long id;--%>

<%--private String name;--%>
<%--private String description;--%>

<%--@OneToOne(mappedBy = "task")--%>
<%--private Sprint sprint;--%>

<%--private Integer weight;--%>
<%--private Integer storyPoints;--%>
<%--private Progress progress;--%>

<%--@OneToOne--%>
<%--private User user;--%>