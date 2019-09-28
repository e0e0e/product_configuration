<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authentication var="user" property="principal"/>





    <div class="container bg-info text-light">

        <h1>Edit Account</h1>

        <c:if test="${errorMessage!=null}">

            <div style="background-color: red;">${errorMessage}</div>

        </c:if>

        <form method="post" action="/users">

            <label>Login:</label><br/>
            <input type="text"  class="text-dark" name="login" value="${param.login}"><br/>
            <label>Email:</label><br/>
            <input type="email"  class="text-dark" name="email" value="${param.email}"><br/>
            <label>Password:</label><br/>
            <input type="password" class="text-dark" name="password"><br/>
            <label>User Name:</label><br/>
            <input type="text"  class="text-dark" name="username" value="${param.username}"><br/>
            <input type="submit" class="text-dark" value="Dodaj">


        </form>
    </div>

