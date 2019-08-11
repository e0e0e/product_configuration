<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col bg-secondary sm-2 m-1">
            To Do
        </div>
        <div class="col bg-primary sm-2 m-1">
            In Progress
        </div>
        <div class="col bg-info sm-2 m-1">
            Done
        </div>


    </div>

    <div class="row">
        <div class="col bg-secondary sm-2 m-1">
            <c:forEach var="task" items="${tasksToDo}">
                ${task.name} ${task.progress}<br/>

            </c:forEach>

        </div>
        <div class="col bg-primary sm-2 m-1">
            <c:forEach var="task" items="${tasksInProgress}">
                ${task.name} ${task.progress}<br/>

            </c:forEach>


        </div>
        <div class="col bg-info sm-2 m-1">
            <c:forEach var="task" items="${tasksDone}">
                ${task.name} ${task.progress}<br/>

            </c:forEach>

        </div>


    </div>


</div>
