<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../featureNavigation.jsp"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib
	prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set
	var="imagesPath"
	value="http://konfigurator.viberti.pl/imagesLd/" />
<c:set
	var="default"
	value="default.png" />
<sec:authentication
	var="user"
	property="principal" />
<script>
	function hideChassis() {
		var x = document.getElementsByClassName("chassis");
		for (var i = 0; i < x.length; i++) {
			if (x[i].style.display === "none") {
				x[i].style.display = "block";
			} else {
				x[i].style.display = "none";
			}
		}
	}
</script>
<div style="min-width: 1000px;">
	<c:if test="${errorMessage!=null && errorMessage!='null'}">

		<p
			style="background-color: #B24C4C; position: fixed; z-index: 9000; margin-left: 40%"
			class="p-5">${errorMessage}</p>

	</c:if>
	<div class="card text-dark m-1">


		<div class="card-header bg-info text-left text-light">
			<div>
				LP. ${order.id}, Order name: ${order.orderName}, Client:
				${order.client}, Units: ${order.unitsToProduce} Created:
				${order.createdDate}, Modified: ${order.lastModifiedDate}, Revision:
				${order.revision}
				<div>Price : ${order.price}</div>
				<div>Pl order number: ${order.plOrder}</div>
			</div>
			<c:if test="${user.authorities=='[ADMIN]'}">
				<a
					class="btn btn-outline-secondary text-dark"
					href="/order/edit?orderId=${order.id}">
					<span class="glyphicon glyphicon-edit text-warning"></span>
				</a>
				<a
					class="btn btn-outline-secondary text-light"
					href="/matrix?orderId=${order.id}">
					<i class="glyphicon glyphicon-magnet text-warning"></i>
				</a>
			</c:if>
			<a
				class="btn btn-outline-secondary text-dark"
				href="/order/show?orderId=${order.id}&edit=true">
				<span class="glyphicon glyphicon-gift text-light"></span>
			</a>
			<a
				class="btn btn-outline-secondary text-light"
				href="/order/color/edit?orderId=${order.id}">
				<i class="fa fa-paint-brush text-light"></i>
			</a>

			<c:if test="${order.link!=null}">
				<a
					class="btn btn-outline-secondary text-light"
					href="${order.link}"
					target="_blank">Sherpoint</a>
			</c:if>
			<a
				class="btn btn-outline-secondary text-light"
				href="/order/ifNewProductFeatures?orderId=${order.id}">Update</a>
			<a
				class="btn btn-outline-secondary text-light"
				href="/order/print?orderId=${order.id}">
				<span class="glyphicon glyphicon-print text-light"></span>
			</a>
			<c:if test="${order.noStandard=='TRUE'}">
				<span
					class="text-warning text-center align-middle"
					style="font-size: 30px;">No standard order</span>
				<span class="float-right">
					<a
						class="btn btn-outline-info text-dark btn-lg"
						href="/save/product/noStandard?orderId=${order.id}">
						<span class="glyphicon glyphicon-floppy-disk text-warning"></span>
					</a>
				</span>
			</c:if>
			<div class="row">
				<div
					class="ml-5 mr-5 p-3 col rounded-pill text-center"
					id="status_rs_${order.id}"
					style="background-color:${order.rs.kolor}"
					value="${order.rs}">
					<div
						id="arrow_left_rs_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-left text-light btn btn-outline-warning m-2"
						id="arrow_left_${order.id}"
						onclick="changeStatus('${order.id}','rs',0)"></div>
					<div class="d-inline rounded-pill p-2">RS</div>
					<div
						id="arrow_right_rs_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-right text-light btn btn-outline-warning m-2"
						onclick="changeStatus('${order.id}','rs',1)"></div>
				</div>

				<div
					id="status_documentation_${order.id}"
					class="ml-5 mr-5 p-3 col rounded-pill text-center"
					value="${order.documentation}"
					style="background-color:${order.documentation.kolor}">
					<div
						id="arrow_left_documentation_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-left text-light btn btn-outline-warning m-2"
						id="arrow_left_${order.id}"
						onclick="changeStatus('${order.id}','documentation',0)"></div>
					<div class="d-inline  p-2">DOC</div>
					<div
						id="arrow_right_documentation_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-right text-light btn btn-outline-warning m-2"
						onclick="changeStatus('${order.id}','documentation',1)"></div>
				</div>
				<div
					class="ml-5 mr-5 p-3 col rounded-pill text-center"
					id="status_qad_${order.id}"
					style="background-color:${order.qad.kolor}"
					value="${order.qad}">
					<div
						id="arrow_left_qad_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-left text-light btn btn-outline-warning m-2"
						id="arrow_left_${order.id}"
						onclick="changeStatus('${order.id}','qad',0)"></div>
					<div class="d-inline rounded-pill p-2">QAD</div>
					<div
						id="arrow_right_qad_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-right text-light btn btn-outline-warning m-2"
						onclick="changeStatus('${order.id}','qad',1)"></div>
				</div>

				<div
					class="ml-5 mr-5  p-3 col rounded-pill text-center"
					id="status_production_${order.id}"
					style="background-color:${order.production.kolor}"
					value="${order.production}">
					<div
						id="arrow_left_production_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-left text-light btn btn-outline-warning m-2"
						id="arrow_left_${order.id}"
						onclick="changeStatus('${order.id}','production',0)"></div>
					<div class="d-inline rounded-pill p-2">PRO</div>
					<div
						id="arrow_right_production_${order.id}"
						class="d-inline glyphicon glyphicon glyphicon-chevron-right text-light btn btn-outline-warning m-2"
						onclick="changeStatus('${order.id}','production',1)"></div>
				</div>

			</div>
		</div>
		<div class="card-body">
			<c:forEach
				var="feature"
				items="${order.orderFeatures}">
				<c:choose>
					<c:when test="${feature.productFeature.parent=='Chassis'}">
						<div class="chassis border-left  border-bottom  ml-5">
							<div class="row p-1">
								<div class="col-2">
									<div class="ml-5 p-2 bg-info text-light">${feature.productFeature.name}:</div>
								</div>
								<div class="col-3">
									<div class="ml-5">${feature.feature.name}
										<c:if test="${feature.feature.noStandard=='TRUE'}">
											<a
												class="btn btn-outline-info text-dark"
												href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}">
												<span
													class="glyphicon glyphicon-exclamation-sign text-danger"></span>
											</a>
										</c:if>
										<c:if test="${edit=='TRUE'}">
											<a
												class="btn btn-outline-info text-dark"
												href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}&edit=${edit}">
												<span class="glyphicon glyphicon-gift text-warning"></span>
											</a>
										</c:if>
									</div>
								</div>
								<div class="col-1">${feature.feature.index}</div>
								<div class="col-1">${feature.feature.mIndex}</div>
								<c:choose>
									<c:when test="${feature.productFeature.color==true}">
										<div class="col-1 m-1">${feature.color.type}<span
												class="glyphicon glyphicon-tint"
												style="color:${feature.color.hex};"></span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-1 m-1"></div>
									</c:otherwise>
								</c:choose>

								<div class="col-1">
									<c:if test="${feature.feature.imagePath!=''}">
										<a
											href="/im/${feature.feature.imagePath}"
											target="_blank"
											class="rys">
											<img
												src="/im/${feature.feature.imagePath}"
												alt="Flowers in Chania"
												width="100"
												onerror="imgError(this)">
										</a>
									</c:if>
								</div>

								<c:if test="${feature.feature.price!='0.0'}">
									<div class="col-1">${feature.feature.price}</div>
								</c:if>
							</div>
						</div>
					</c:when>
					<c:when test="${feature.productFeature.name=='Chassis'}">
						<div class="row border p-1">
							<div class="col-2 bg-info text-light ">
								<button
									class="btn btn-outline-secondry bg-light text-light p-1 m-1"
									onclick="hideChassis()">
									<span class="glyphicon glyphicon-menu-up text-dark"></span>
								</button>
								${feature.productFeature.name}:
							</div>
							<div class="col-3 m-1">${feature.feature.name}
								<c:if test="${feature.feature.noStandard=='TRUE'}">
									<a
										class="btn btn-outline-info text-dark"
										href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}">
										<span class="glyphicon glyphicon-exclamation-sign text-danger"></span>
									</a>
								</c:if>
								<c:if test="${edit=='TRUE'}">
									<a
										class="btn btn-outline-info text-dark"
										href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}&edit=${edit}">
										<span class="glyphicon glyphicon-gift text-warning"></span>
									</a>
								</c:if>
							</div>
							<div class="col-1 m-1">${feature.feature.index}</div>
							<div class="col-1 m-1">${feature.feature.mIndex}</div>
							<div class="col-1 m-1">${feature.color.type}<span
									class="glyphicon glyphicon-tint"
									style="color:${feature.color.hex};"></span>
							</div>
							<div class="col-1 m-1">
								<c:if test="${feature.feature.imagePath!=''}">
									<a
										href="/im/${feature.feature.imagePath}"
										target="_blank"
										class="rys">
										<img
											src="/im/${feature.feature.imagePath}"
											alt="Flowers in Chania"
											width="100"
											onerror="imgError(this)">
									</a>
								</c:if>
							</div>

							<c:if test="${feature.feature.price!='0.0'}">
								<div class="col-1 m-1">${feature.feature.price}</div>
							</c:if>
						</div>
		</div>
		</c:when>

		<c:otherwise>
			<div class="row border-bottom m-1  p-0">
				<div class="col-2 bg-info text-light p-1">${feature.productFeature.name}:</div>
				<div class="col-3 m-1">${feature.feature.name}
					<c:if test="${feature.feature.noStandard=='TRUE'}">
						<a
							class="btn btn-outline-info text-dark"
							href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}">
							<span class="glyphicon glyphicon-exclamation-sign text-danger"></span>
						</a>
					</c:if>
					<c:if test="${edit=='TRUE'}">
						<a
							class="btn btn-outline-info text-dark"
							href="/feature/editFeatureNoStandard?featureId=${feature.feature.id}&orderId=${order.id}&edit=${edit}">
							<span class="glyphicon glyphicon-gift text-warning"></span>
						</a>
					</c:if>
				</div>
				<div class="col-1 m-1">${feature.feature.index}</div>
				<div class="col-1 m-1">${feature.feature.mIndex}</div>
				<c:choose>
					<c:when test="${feature.productFeature.color==true}">
						<div class="col-1 m-1">${feature.color.type}<span
								class="glyphicon glyphicon-tint"
								style="color:${feature.color.hex};"></span>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-1 m-1"></div>
					</c:otherwise>
				</c:choose>
				<div class="col-1 m-1">
					<c:if test="${feature.feature.imagePath!=''}">
						<a
							href="/im/${feature.feature.imagePath}"
							target="_blank"
							class="rys">
							<img
								src="/im/${feature.feature.imagePath}"
								alt="Flowers in Chania"
								width="100"
								onerror="imgError(this)">
						</a>
					</c:if>
				</div>

				<c:if test="${feature.feature.price!='0.0'}">
					<div class="col-1 m-1">${feature.feature.price}</div>
				</c:if>
			</div>
		</c:otherwise>
		</c:choose>
		</c:forEach>

	</div>
	<div class="card-footer bg-info text-right text-light">
		<div>Modified by: ${order.lastModifiedBy}</div>

	</div>
</div>



</div>



<div class="container p-5">
	<c:forEach
		var="ordAud"
		items="${aud}">
		<div class="row border bg-secondary text-light">
			<div class="col ">
				Order name: ${ordAud.order.orderName}, Price: ${ordAud.order.price},
				Client: ${ordAud.order.client}, Units:
				${ordAud.order.unitsToProduce} , Modified by:
				${ordAud.order.lastModifiedBy} , revision: ${ordAud.order.revision}
				, date: ${ordAud.date}
				<c:catch var="e">
					<span
						class="m-2 p-2 rounded-pill"
						style="background-color:${ordAud.order.rs.kolor}">R</span>
						<span
						class="m-2 p-2 rounded-pill"
						style="background-color:${ordAud.order.documentation.kolor}">D</span>
						<span
						class="m-2 p-2 rounded-pill"
						style="background-color:${ordAud.order.qad.kolor}">Q</span>
						<span
						class="m-2 p-2 rounded-pill"
						style="background-color:${ordAud.order.production.kolor}">P</span>
	<c:if test = "${e != null}"> ${e} </c:if>
				</c:catch>
				<c:if test="${ordAud.order.noStandard=='true'}">
					<span class="text-warning">, NOT STANDARD</span>
				</c:if>
			</div>
		</div>
		<c:forEach
			var="listRow"
			items="${ordAud.featureAuds}">
			<div class="row">
				<div class="col">${listRow.pfName}</div>
				<div class="col">${listRow.oldFeature}</div>
				<div class="col">${listRow.newFeature}</div>
			</div>
		</c:forEach>



	</c:forEach>

</div>

<script>
	function changeStatus(orderId, task, next) {
		let statusId = "status_";
		statusId = statusId.concat(task).concat("_").concat(orderId);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				results = JSON.parse(this.responseText);
				console.log(results);

				document.getElementById(statusId).style.backgroundColor = results[1];
				document.getElementById(statusId).setAttribute("value",
						results[0]);

			}
		};

		let arr = {};

		arr["orderId"] = orderId;
		arr["status"] = document.getElementById(statusId).getAttribute("value");
		arr["task"] = task;
		arr["next"] = next;
		let fString = JSON.stringify(arr);
		console.log(fString);
		xhttp.open("POST", "/order/status/", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(fString);

	}
</script>