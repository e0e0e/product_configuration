<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<div
	class="container-fluid"
	id="all"
	style="min-width: 1000px;">
	<%@include file="../featureNavigation.jsp"%>


	<h3>Orders:</h3>
	<div class="card text-dark bg-light m-1">
		<div class="card-header bg-secondary text-left text-light">
			<div class="row">
				<div class="col-1"></div>
				<div class="col text-center">LP.</div>
				<div class="col text-center">Order name</div>
				<div class="col text-center">Order PL</div>
				<div class="col text-center">
				<div class="row">
				<div class="col">R<input type="checkbox" class="form-check-input" id="chechR" onclick="fProgress()" checked></div>
				<div class="col">D<input type="checkbox" class="form-check-input" id="chechD" onclick="fProgress()" checked></div>
				<div class="col">Q<input type="checkbox" class="form-check-input" id="chechQ" onclick="fProgress()" checked></div>
				<div class="col">P<input type="checkbox" class="form-check-input" id="chechP" onclick="fProgress()" checked></div>
				</div>
				
				</div>
				<div class="col text-center">Date</div>
				<div class="col text-center">Revision</div>
				<div class="col-1"></div>
			</div>
		</div>
	</div>
	<c:forEach
		var="order"
		items="${orders}">

		<div class="card text-dark bg-light m-1">


			<div class="card-header bg-info text-left text-light">
				<div class="row">
					<div class="col-1">
						<a
							class="btn btn-outline-secondary text-dark"
							href="/order/addMore?orderId=${order.id}">
							<span class="glyphicon glyphicon-edit text-light"></span>
						</a>
						<c:if test="${order.noStandard=='TRUE'}">
							<div class="float-right mt-2 text-warning  font-weight-bold">NS</div>
						</c:if>
					</div>
					<div class="col-10">
						<a
							class="btn btn-outline-secondary text-light w-100"
							href="/order/show?orderId=${order.id}">
							<div class="row">
								<div class="col">${order.id}</div>
								<div class="col">${order.orderName}</div>

								<div class="col">${order.plOrder}</div>
								<div class="col">
									<span
										class="m-2 p-2 rounded-pill"
										style="background-color:${order.rs.kolor}">R</span>
									<span
										class="m-2 p-2 rounded-pill"
										style="background-color:${order.documentation.kolor}">D</span>
									<span
										class="m-2 p-2 rounded-pill"
										style="background-color:${order.qad.kolor}">Q</span>
									<span
										class="m-2 p-2 rounded-pill"
										style="background-color:${order.production.kolor}">P</span>

								</div>
								<div class="col">
									<fmt:formatDate
										type="date"
										value="${order.lastModifiedDate}"
										pattern="yyyy-MM-dd, HH:mm" />
								</div>
								<div class="col">${order.revision}</div>
							</div>
						</a>
					</div>
					<c:if test="${user.authorities=='[ADMIN]'}">
						<div class="col-1">
							<a
								class="btn btn-outline-secondary text-dark float-right"
								href="/order/delete?orderId=${order.id}">
								<span class="glyphicon glyphicon-trash text-light"></span>
							</a>
						</div>
					</c:if>
				</div>

			</div>



		</div>
	</c:forEach>


</div>

<script>
function fProgress(){
	let boxes={};
	boxes[0]=document.getElementById("chechR").checked;
	boxes[1]=document.getElementById("chechD").checked;
	boxes[2]=document.getElementById("chechQ").checked;
	boxes[3]=document.getElementById("chechP").checked;
	console.log(boxes);
	
	
}
</script>