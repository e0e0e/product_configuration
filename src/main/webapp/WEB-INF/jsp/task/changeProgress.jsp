<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <form method="post" action="/task/progressChange?taskId=${task.id}">
        <select name="progress" class="text-dark" value="${task.progress}">
            <c:forEach var="value" items="${progress}">
                <option value="${value}">
                        ${value}
                </option>
            </c:forEach>
        </select>
        <input type="submit" value="Change">
    </form>
</div>
