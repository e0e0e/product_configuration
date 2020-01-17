<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib
	prefix="fn"
	uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>

	<h1>Edit Product Feature</h1>
	<sec:authentication
		var="loggedUser"
		property="principal" />
	<form
		method="post"
		action="/productFeatureChange?userId=${loggedUser.id}&productFeatureId=${param.productFeatureId}">
		<c:if test="${errorMessage!=null}">

			<p style="background-color: red;">${errorMessage}</p>

		</c:if>
		<label>Feature Name:</label>
		<br />
		<textarea
			rows="2"
			class="text-dark"
			name="name">${productFeature.name}</textarea>
		<br />
		<label>Project Description:</label>
		<br />
		<textarea
			rows="4"
			class="text-dark"
			name="description">${productFeature.description}</textarea>
		<br />
			 <input type="checkbox" name="parent" value="Chassis"
			 <c:if test="${productFeature.parent=='Chassis'}">
			 checked
			 </c:if>
			 >Chassis feature
		<br />
		<label>Feature Image:</label>
		<br />
		<input
			type="text"
			class="text-dark"
			name="imagePath"
			value="${productFeature.imagePath}">
		<br />
		<br />
		<label>Current feature List:</label>
		<br />
		<c:forEach
			var="feat"
			items="${productFeature.feature}">
			<div>${feat.name}</div>
		</c:forEach>
		<br />
		<label>Change feature List:</label>
		<br />
		<select
			size="${fn:length(featuresList)}"
			name="featureList"
			multiple>
			<c:forEach
				var="feature"
				items="${featuresList}">
				<option value="${feature.id}">${feature.name}</option>
			</c:forEach>
		</select>

		<div>
			<a
				href="/feature/add"
				style="float: left;">
				<span class="glyphicon glyphicon-plus text-dark"></span>
			</a>
		</div>

		<br>
		<input
			type="submit"
			class="text-dark"
			value="Save">


	</form>
</div>