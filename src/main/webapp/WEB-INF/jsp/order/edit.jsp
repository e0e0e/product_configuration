<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light">
			<a
				href="/product/edit?productId=${configuration.id}"
				class="nav-link text-light font-weight-bold">
				${configuration.name}</a>
		</div>


		<div class="card-body ">
			<form
				method="post"
				action="/order/saveProductChanges?orderId=${order.id}">

				<c:forEach
					var="orderFeature"
					items="${order.orderFeatures}">
					<div class="row m-3 border box">
						<div class="col-2 m-2">${orderFeature.productFeature.name}</div>
						<div class="col-4 m-2">
							<c:choose>
								<c:when
									test="${fn:length(orderFeature.productFeature.feature)>1}">
									<select name="${orderFeature.id}">
								</c:when>
								<c:otherwise>
									<select
										name="${orderFeature.id}"
										disabled>
								</c:otherwise>
							</c:choose>
							<option
								value="orderFeature.feature.id"
								selected
								disabled
								hidden>${orderFeature.feature.name}</option>
							<c:forEach
								var="feature"
								items="${orderFeature.productFeature.feature}">
								<option value="${feature.id}">${feature.name}</option>
							</c:forEach>
							</select>

						</div>
					</div>
				</c:forEach>

				<input
					type="submit"
					value="Save">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light">
			<div>created by:</div>

		</div>
	</div>



</div>