<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Orders:<a
			href="/order/add"
			class="btn btn-outline-dark btn-sm"> <span
			class="glyphicon glyphicon-plus"></span>
		</a>
	</h3>
	<c:forEach
		var="order"
		items="${orders}">

		<div class="card text-dark bg-light m-1">


			<div class="card-header bg-info text-left text-light">
				<div>Order name: ${order.orderName}, Price: ${order.price},
					Client Address: ${order.client}, Units: ${order.unitsToProduce}</div>
				<a
					class="btn btn-outline-info text-dark"
					href="/order/delete?orderId=${order.id}"> <span
					class="glyphicon glyphicon-trash text-dark"></span></a> <a
					class="btn btn-outline-info text-dark"
					href="/order/addMore?orderId=${order.id}"> <span
					class="glyphicon glyphicon-plus text-dark"></span></a>
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
	</c:forEach>


</div>