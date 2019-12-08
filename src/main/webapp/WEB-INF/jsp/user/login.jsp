<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project list</title>

    <%@include file="../css/boot.jsp" %>
</head>
<body>


<div class="container">

    <div class="row justify-content-center">

        <div class="col-sm-4">

            <div class="card text-dark bg-warning mx-auto">

                <div class="card-header bg-info text-left text-light"><h4>Login</h4></div>

                <div class="card-body ">


                    <form method="post" action="/login">
                        <label>User name</label>
                        <input type="text" name="username"><br/>
                        <label>Password</label>
                        <input type="password" name="password">
                        <br/>
                        <input type="submit" value="Login">
                        <br/>

                    </form>
                </div>

                <div class="card-footer bg-info text-right text-danger">

                    <a href="/users">Create an account</a>
                </div>

            </div>

        </div>


    </div>

</div>

</body>
</html>
