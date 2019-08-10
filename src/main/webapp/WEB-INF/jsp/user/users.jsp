<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container bg-info text-light">
<h1>Create User</h1>

<c:if test="${errorMessage!=null}">

    <div style="background-color: red;">${errorMessage}</div>

</c:if>

<form method="post" action="/users">

    <label>Login:</label><br/>
    <input type="text"  class="text-dark" name="login"><br/>
    <label>Email:</label><br/>
    <input type="email"  class="text-dark" name="email"><br/>
    <label>Password:</label><br/>
    <input type="password" class="text-dark" name="password"><br/>
    <label>User Name:</label><br/>
    <input type="text"  class="text-dark" name="username"><br/>
    <input type="submit" class="text-dark" value="Dodaj">


</form>
</div>