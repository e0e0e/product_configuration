<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<%@ taglib
		prefix="fmt"
		uri="http://java.sun.com/jsp/jstl/fmt"%>
	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light">
			<div>LP. ${order.id}, Order name: ${order.orderName}, Price:
				${order.price}, Client Address: ${order.client},
				 Units: ${order.unitsToProduce}
				 Modified: ${order.lastModifiedDate}
				 </div>
			<a
				class="btn btn-outline-info text-dark"
				href="/order/delete?orderId=${order.id}"> <span
				class="glyphicon glyphicon-trash text-dark"></span></a> <a
				class="btn btn-outline-info text-dark"
				href="/order/addMore?orderId=${order.id}"> <span
				class="glyphicon glyphicon-plus text-dark"></span></a> <a
				class="btn btn-outline-info text-dark"
				href="/order/edit?orderId=${order.id}"> <span
				class="glyphicon glyphicon-edit text-dark"></span></a>
		</div>


		<div class="card-body ">
			<ul>




				<c:forEach
					var="feature"
					items="${order.orderFeatures}">


					<div class="row">
						<div class="col-2 m-1">${feature.productFeature.name}:</div>
						<div class="col-4 m-1">${feature.feature.name}</div>
						<div class="col-2 m-1">${feature.feature.index}</div>
					</div>



				</c:forEach>
			</ul>

		</div>
		<div class="card-footer bg-info text-right text-light">
			<div>created by:</div>

		</div>
	</div>



</div>

<div class="container">
	<c:forEach
		var="order"
		items="${aud}">
		<div class="row border">
		<div class="col  text-danger">${order[0].productFeature.name}</div>
			<div class="col  text-warning">	
				<c:catch var="exception">${order[0].feature.name}</c:catch>
				<c:if test="${not empty exception}">Nothing selected</c:if>
				<%-- <c:if test="${not empty order[0].feature.id}">${order[0].feature.id}</c:if> --%>
			</div>
			
			<div class="col">
				<fmt:formatDate
					type="date"
					value="${order[1].revisionDate}"
					pattern="yyyy-MM-dd, HH:mm" />
			</div>

			<div class="col">${order[1].username}</div>
		</div>

	</c:forEach>

</div>