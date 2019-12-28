<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:useBean
	id="now"
	class="java.util.Date" />
<sec:authentication
	var="user"
	property="principal" />
<div>

	<nav class="navbar navbar-expand-lg navbar-dark bg-info">
		<div class="container">
			<b class="navbar-brand">LD</b>

			<button
				class="navbar-toggler navbar-toggler-right border-0"
				type="button"
				data-toggle="collapse"
				data-target="#navbar11"
				aria-expanded="true">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div
				class="navbar-collapse collapse show"
				id="navbar11"
				style="">
				<ul class="navbar-nav mr-auto bg-info">
					<li class="nav-item"><a
						class="nav-link text-light font-weight-bold"
						href="/feature/show">Features</a></li>
					<li class="nav-item"><a
						href="/feature/add"
						class="nav-link text-light font-weight-bold">Feature<span
							class="glyphicon glyphicon-plus text-light"></span></a></li>

					<li class="nav-item"><a
						class="nav-link text-light font-weight-bold"
						href="/feature/list">Product Features </a></li>

					<li class="nav-item"><a
						class="nav-link text-light font-weight-bold"
						href="/product/show">Product Show</a></li>
					<li class="nav-item"><a
						class="nav-link text-light font-weight-bold"
						href="/product/listAll">All Products</a></li>

					<li class="nav-item"><a
						class="nav-link text-light font-weight-bold"
						href="/orders/list">Orders</a></li>

				</ul>
			</div>
		</div>
		<div
			style="display: inline-block"
			class="text-white">

			logged as: <a
				href="/userProfile?username=${user.username}"
				class="btn btn-outline-light"> ${user.username}</a> <img
				class="img-circle bg-dark p-1 m-1"
				src="${resourcePath}${user.avatar}"
				height="40"
				width="40" /> <a href="/logout"><span
				class="glyphicon glyphicon-log-out"
				style="color: white;"></span></a> <br />
		</div>
	</nav>
	<div></div>
</div>