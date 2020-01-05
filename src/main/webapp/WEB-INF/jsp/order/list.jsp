<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Orders: <a
			href="/order/add"
			class="btn btn-outline-secondary btn-lg"> <span
			class="glyphicon glyphicon-plus"></span>
		</a>
	</h3>
	<c:forEach
		var="order"
		items="${orders}">

		<div class="card text-dark bg-light m-1">


			<div class="card-header bg-info text-left text-light">
				<div class="m-1">
					<a
						class="btn btn-outline-secondary text-dark"
						href="/order/addMore?orderId=${order.id}"> <span
						class="glyphicon glyphicon-edit text-light"></span>
					</a> <a
						class="btn btn-outline-secondary text-light"
						href="/order/show?orderId=${order.id}"> LP. ${order.id}, Order
						name: ${order.orderName}, Price: ${order.price}, Client Address:
						${order.client}, Units: ${order.unitsToProduce}, Modified: <fmt:formatDate
							type="date"
							value="${order.lastModifiedDate}"
							pattern="yyyy-MM-dd, HH:mm" />, Revision: ${order.revision}
					</a> <a
						class="btn btn-outline-secondary text-dark float-right"
						href="/order/delete?orderId=${order.id}"> <span
						class="glyphicon glyphicon-trash text-light"></span>
					</a>
				</div>

			</div>



		</div>
	</c:forEach>


</div>