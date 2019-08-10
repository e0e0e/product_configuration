<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <div class="col bg-secondary sm-2">
            <c:forEach var="task" items="${tasksToDo}">
                ${task.name} ${task.progress}<br/>

            </c:forEach>

        </div>
        <div class="col bg-primary sm-2">
            in progress

        </div>
        <div class="col bg-info sm-2">
            done

        </div>


    </div>



</div>
