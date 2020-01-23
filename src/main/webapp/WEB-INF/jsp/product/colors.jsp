<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<div id="rest"></div>
	<h3>
		Configuration of order
	</h3>


	<div class="card text-dark bg-light m-1">


		<div class="card-header bg-info text-left text-light"></div>

<div id="error"></div>
<div id="picture" style="position:absolute;top:30px;">

</div>
		<div class="card-body ">
			<form
				id="myFilterForm"
				method="post"
				action="/filter/orderSaveEdited?orderId=${orderId}">

				<ul>
					<c:forEach
						var="configList"
						items="${configuration.configurationList}">
						<div class="row">
							<div class="col-2 m-1">${configList.name}</div>
							<div class="col-2 m-1">
								<select
									name="${configList.id}"
									id="${configList.name}"
									onchange='changeAction(this,${configList.id})'>
									<option
										value=""
										selected
										disabled
										hidden>${order[configList.name]}</option>
									<c:forEach
										var="feature"
										items="${configList.feature}">
										<option value="${feature.id}">${feature.name}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</c:forEach>
				</ul>
				<input
					id="saveButton"
					type="submit"
					value="Save">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light"></div>
	</div>



</div>