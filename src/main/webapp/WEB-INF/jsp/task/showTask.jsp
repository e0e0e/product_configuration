<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<c:set var="now" value="${LocalDate.now()}"/>


    <c:if test="${task.sprint.finishDate<now}">
        <div class="card bg-warning m-2 text-center p-1">
        Delayed ${ChronoUnit.DAYS.between(task.sprint.finishDate,now)} days
        from ${task.sprint.finishDate}
    </c:if>
    <c:if test="${!(task.sprint.finishDate<wall.now)}">
        <div class="card bg-primary m-2 text-center p-1">
    </c:if>

    <div class="row">
            <%--        <div class="col float-left">--%>

            <%--            <a href="/task/progressToNextChange?taskId=${task.id}&progress=TO_DO&backToWall=${task.sprint.id}&previous=${param.previous}"--%>
            <%--               class="btn btn-outline-light btn-sm">--%>
            <%--                <span class="glyphicon glyphicon-triangle-left"></span></a>--%>
            <%--        </div>--%>
        <div class="col float-none">
                <%--                                            <a href="#" data-toggle="popover"--%>
                <%--                                               title="${task.description}">${task.name}</a>--%>

            <h1>${task.name}</h1>
                    <a href="/task/edit?taskId=${task.id}" class="btn btn-outline-light btn-sm text-right" style="float: right;">
                        <span class="glyphicon glyphicon-edit"></span></a>
            <div style="width:60%;margin: auto;" class="border p-5">${task.description}


                    <%--                                    <c:if test="${user.username==task.user}">--%>
            </div><br/>
                    <div>${task.progress}</div>
                <%--        <div class="col float-right">--%>
                <%--            <a href="/task/progressToNextChange?taskId=${task.id}&progress=DONE&backToWall=${task.sprint.id}&previous=${param.previous}"--%>
                <%--               class="btn btn-outline-light btn-sm">--%>
                <%--                <span class="glyphicon glyphicon-triangle-right"></span></a>--%>
                <%--        </div>--%>
                <%--                                    </c:if>--%>
        </div>
        </div>

        <div class="card-footer">
            <div class="row">
                <div class="col">
                    <span class="glyphicon glyphicon-star p-2"></span>${task.storyPoints}

                </div>
                <div class="col text-center float-right">
                    <img class="img-circle bg-dark p-1"
                         src="${resourcePath}${task.user.avatar}"
                         height="40" width="40"/>
                    <a href="/userProfile?userId=${task.user.id}"
                       class="btn btn-outline-light"> ${task.user.username}</a>

                </div>
            </div>
        </div>

    </div>
    </div>




