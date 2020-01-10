<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
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
				action="/product/search">
				<input
					type="submit"
					value="Search">
				<ul>
					<c:forEach
						var="configList"
						items="${configuration.configurationList}">
						<div class="row">
							<div class="col-2 m-1" > ${configList.name}</div>
							<div class="col-2 m-1">
							<select name="${configList.id}" id="${configList.name}" onchange='changeAction(this,${configList.id})'>
								<option
									value=""
									selected
									disabled
									hidden>Ignore</option>
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
					type="submit"
					value="Search">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light">
		

		</div>
	</div>



</div>
<script>
function changeAction(val,da) {
	
	 var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	         if (this.readyState == 4 && this.status == 200) {
	      
	            
	             console.log(this.responseText);
	         }
	    };
	    console.log("/product/matching?featureId="+val.value+"&productFeatureId="+da);
	    xhttp.open("GET", "/product/matching?featureId="+val.value+"&productFeatureId="+da, true);
	    xhttp.setRequestHeader("Content-type", "application/json");
	    xhttp.send("Your JSON Data Here");
	
}
</script>