<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<sec:authentication var="user" property="principal"/>--%>

 <div class="container bg-info text-light">

        <h1>Edit User Authority</h1>

        <c:if test="${errorMessage!=null}">

            <div style="background-color: red;">${errorMessage}</div>

        </c:if>

        <form method="post" action="/auth/user/save?userId=${user.id}">

            <label>User Authority (ADMIN,KONSTRUKTOR):</label><br/>
            <input type="text"  class="text-dark" name="authorities" value="${user.authorities}" size="150"><br/>
            <input type="submit" class="text-dark mt-5" value="Save">


        </form>
    </div>

