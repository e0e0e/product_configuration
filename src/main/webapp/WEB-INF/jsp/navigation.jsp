<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page import = "java.io.*,java.util.*" %>
<jsp:useBean id="now" class="java.util.Date" />
<sec:authentication var="user" property="principal" scope="session" />
<div>

	<%
	String headerNames = request.getHeader("host");
	if(headerNames.equals("localhost:8081")){
		out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-secondary'>");
	}else{
		out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-info'>");

	}
%>


	<b class="navbar-brand">LD</b>

	<button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse"
		data-target="#navbar11" aria-expanded="false">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="navbar-collapse collapse" id="navbar11" style="">

		<ul class="navbar-nav m-auto ">


			<c:if test="${user.authorities=='[ADMIN]'}">
				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/product/search">MultiEdit</a> </li>
				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/users/list"> Users</a> </li>

				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/export/products">DB <span
							class="glyphicon glyphicon-floppy-save text-warning"></span></a></li>

				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/upload/db">DB <span class="glyphicon glyphicon-floppy-open text-warning"></span></a>
				</li>


				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/feature/show">Features</a></li>


				<li class="nav-item"><a href="/feature/add"
						class="nav-link text-warning font-weight-bold btn btn-outline-dark">F <span
							class="glyphicon glyphicon-plus text-warning"></span></a></li>

				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/feature/list">Product Features </a></li>
				<li class="nav-item"><a class="nav-link text-warning font-weight-bold btn btn-outline-dark"
						href="/product/moveList">PF Move</a></li>
				<li class="nav-item"><a href="/color/add"
						class="nav-link text-light font-weight-bold btn btn-outline-dark">C <span
							class="glyphicon glyphicon-plus text-light"></span></a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link text-light font-weight-bold btn btn-outline-dark"
					href="/color/list">Colors</a></li>


			<li class="nav-item"><a class="nav-link text-light font-weight-bold btn btn-outline-dark"
					href="/product/show">Product Show</a>


			<li class="nav-item"><a class="nav-link text-light font-weight-bold btn btn-outline-dark"
					href="/orders/list">Orders</a></li>

			<li class="nav-item"><a class="nav-link text-light font-weight-bold btn btn-outline-dark"
					href="/product/filter">
					<span class="glyphicon glyphicon-plus text-light"></span></a>
			</li>
		</ul>

	</div>

	<span class="navbar-text">

		logged as: <a href="/userProfile?username=${user.username}" class="btn btn-outline-light">
			${user.username}</a> <img class="img-circle bg-dark p-1 m-1" src="${resourcePath}${user.avatar}" height="40"
			width="40" /> <a href="/logout"><span class="glyphicon glyphicon-log-out" style="color: white;"></span></a>
		<br />
	</span>

	</nav>

</div>