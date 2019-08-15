<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--    <meta name="description" content="">--%>
<%--    <meta name="author" content="">--%>
<%--    <title>Please sign in</title>--%>
<%--    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">--%>
<%--    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--    <form class="form-signin" method="post" action="/login">--%>
<%--        <h2 class="form-signin-heading">Please sign in</h2>--%>
<%--        <p>--%>
<%--            <label for="username" class="sr-only">Username</label>--%>
<%--            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="password" class="sr-only">Password</label>--%>
<%--            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>--%>
<%--        </p>--%>
<%--        <p><input type='checkbox' name='remember-me'/> Remember me on this computer.</p>--%>
<%--        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body></html>--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>My Custom Login Page</title>
</head>
<body style='margin:50px;'>
<h2>My Custom Login Page</h2>
<form action="/my-login" method="post">
    <c:if test="${param.error != null}">
        <p style='color:red'>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p style='color:blue'>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <p><input type='checkbox' name='remember-me'/> Remember me on this computer.</p>
    <button type="submit">Log in</button>
</form>
<a href="/users">Create an account</a>
</body>
</html>