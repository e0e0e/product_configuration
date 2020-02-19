<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<title>Login Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body style='margin: 50px;'>

	<div class="container bg-info text-center ">
		<h2>Login:</h2>

		<form action="/my-login" method="post">
			<c:if test="${param.error != null}">
				<p style='color: red'>Invalid user name and password.</p>
			</c:if>
			<c:if test="${param.logout != null}">
				<p style='color: blue'>You have been logged out.</p>
			</c:if>
			<div class="row justify-content-center m-1">
				<div class="col-2">
					<label for="username">User name</label>
				</div>
				<div class="col-2">
					<input type="text" id="username" name="username"  class="custom-select"/>
				</div>
			</div>
			<div class="row justify-content-center m-1">
				<div class="col-2">
					<label for="password">Password</label>
				</div>
				<div class="col-2">
					<input type="password" id="password" name="password"  class="custom-select"/>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

			<p>
	
	<input type='checkbox' name='remember-me' checked /> Remember me on
	this computer.
	</p>
	<button type="submit" class="btn btn-outline-light bg-primary">Log in</button>
	</form>
	<a href="/users">Create an account</a>

	</div>
</body>

</html>