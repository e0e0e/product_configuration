<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<h1>Add Order Details:</h1>
	<sec:authentication
		var="loggedUser"
		property="principal" />
	<form
		method="post"
		action="/orderToChange?orderId=${order.id}">
		<c:if test="${errorMessage!=null}">

			<p style="background-color: red;">${errorMessage}</p>

		</c:if>


		<label>Order Name:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="orderName"
			value="${order.orderName}">
		<br />
		<label>Price:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="price"
			value="${order.price}">
		<br />
		<label>Client Address:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="client"
			value="${order.client}">
		<br />
		<label>Units:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="unitsToProduce"
			value="${order.unitsToProduce}">
		<br />
		<input
			type="submit"
			class="text-dark"
			value="Save">


	</form>
</div>