<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>
	<div>
		<label>Product Features: </label>
		<a href="/feature/addProductFeature">
			<span class="glyphicon glyphicon-plus text-dark"></span>
		</a>
		
	</div>

	<c:forEach
		var="feature"
		items="${features}">
		<div class="card text-light bg-light border m-1">
			<div class="card-header bg-info text-left-light m-2">${feature.name}
				<a 
				class="btn btn-outline-secondry text-light"
				href="/feature/edit?productFeatureId=${feature.id}">
					<span class="glyphicon glyphicon-edit text-light m-2"></span>
				</a>
				<a 
				class="btn btn-outline-secondry text-light"
				href="/feature/copy?productFeatureId=${feature.id}">
					<span class="glyphicon glyphicon-copy text-light  m-2"></span>
				</a>
				<a 
				class="btn btn-outline-secondry text-light"
				href="/feature/delete?productFeatureId=${feature.id}">
					<span class="glyphicon glyphicon-trash text-light  m-2"></span>
				</a>
				<a href="/productFeature/delete/notUsed?productFeatureId=${feature.id}"
				class="btn btn-outline-secondry text-light">
					Remove All with same name not used<span class="glyphicon glyphicon-trash text-dark"></span>
				</a>
				<span class="float-right m-2">included in product: ${feature.productConfiguration.name}</span>
				
			</div>
			<div class="card-body ">

				<c:forEach
					var="feat"
					items="${feature.feature}">
					<div>
						<a
							class="btn btn-outline-info text-dark"
							href="/feature/editFeature?featureId=${feat.id}"
							style="float: left;">${feat.name}
							<span class="glyphicon glyphicon-edit text-dark"></span>
						</a>
					</div>
				</c:forEach>

			</div>
		</div>
	</c:forEach>

</div>