<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>Tasks</h1>

<form method="post" action="/tasks">

    <label>Task name:</label><br/>
    <input type="text" name="name"><br/>
    <label>Description:</label><br/>
    <input type="text" name="description"><br/>
    <label>Sprint:</label><br/>

    <c:set var="now" value="<%= new java.util.Date()%>" />
    <p>Formatted Date (2): <fmt:formatDate type="date" value="${now}" pattern="mm-dd-yyyy"/></p>

    <label>From:</label>
    <input type="date" name="from" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>"><br/>
    <label>To:</label>
    <input type="date" name="to" value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>"><br/>
    <label>Story points:</label><br/>
    <%--    <input type="number" name="storyPoints" min="1" max="5" VALUE="1"><br/>--%>
    <%@include file="../pointsPicker.jsp" %>
    <%--    <input type="submit" value="Dodaj">--%>



    <label>Weight (1-5):</label><br/>
    <input type="number" name="weight" min="1" max="5" VALUE="1"><br/>


<%--    <label>Progress:</label><br/>--%>
<%--    <input type="text" name="progress"><br/>--%>

    <label>User:</label><br/>
    <%@include file="../userPicker.jsp" %>

    <input type="submit" value="Dodaj">


</form>
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