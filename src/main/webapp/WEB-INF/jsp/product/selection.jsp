<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<h3>
		Products<a
			href="/product/add"
			class="btn btn-outline-dark btn-sm"> <span
			class="glyphicon glyphicon-plus"></span>
		</a>
	</h3>


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
				action="/orderCreate?productConfigurationId=${configuration.id}">

				<c:forEach
					var="configList"
					items="${configuration.configurationList}">
					<div class="row m-3 border box">
						<div class="col-2 m-2">${configList.name}</div>
						<div class="col-4 m-2">

							<c:choose>
								<c:when
									test="${fn:length(configList.feature)>1}">
									<select name="${configList.id}">
								</c:when>
								<c:otherwise>
								<select name="${configList.id}" class="greyText">
								</c:otherwise>
							</c:choose>
							<c:forEach
								var="feature"
								items="${configList.feature}">
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