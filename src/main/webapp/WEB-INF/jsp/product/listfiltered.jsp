<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="container-fluid">
	<%@include file="../featureNavigation.jsp"%>


	<h3>Products matching search criteria</h3>
	<div class="row bg-info text-white" id="shortcuts">
		<c:forEach var="title" items="${columntitles}">
			<div class="col border" id="${title}" name="short" onclick="hideRest(this)">
				${title}
			</div>
		</c:forEach>

	</div>
	</br>
		<div class="bg-secondary text-white p-2" id="${title}" name="short" onclick="showAll(this)">Show all</div>
	</br>
	<div class="row d-flex flex-nowrap ">
		<div class="col-1 border">
			Product title
		</div>
		<c:forEach var="title" items="${columntitles}">
			<div class="col-1 border" name="column" value="${title}">
				${title}
			</div>
		</c:forEach>

	</div>
	<c:forEach var="configuration" items="${configurations}">
		<div class="">
			<div class="row d-flex flex-nowrap ">
				<div class="col-1  border">
					<p style="transform: rotate(-90deg); word-wrap:break-word;" name="product"
						id="${configuration.id}">${configuration.name}
					</p>
				</div>
				<c:forEach var="configList" items="${configuration.configurationList}">

					<div class="col-1 border" name="column" value="${configList.name}">
						<%-- <a class="btn btn-outline-info text-dark"
							href="/feature/edit?productFeatureId=${configList.id}">${configList.name} <span
								class="glyphicon glyphicon-edit text-dark"></span></a> --%>



						<c:forEach var="feature" items="${configList.feature}">
							<div class="bg-info text-light m-1 p-1 rounded">
								<%-- <c:choose>
								<c:when test="${user.authorities=='[ADMIN]'}">
									<p style="word-wrap:break-word">${feature.name}</p>
										<a class="btn btn-outline-info text-dark"
											href="/feature/editFeature?featureId=${feature.id}"><span
												class="glyphicon glyphicon-edit text-dark"  ></span></a>

										<a class="btn btn-outline-info text-dark"
											href="/feature/removeFeature?featureId=${feature.id}&productFeatureId=${configList.id}"><span
												class="glyphicon glyphicon-trash text-dark"></span></a>

									</c:when>
									<c:otherwise> --%>
								<p style="word-wrap:break-word">${feature.name}</p>
								<%-- </c:otherwise>
								</c:choose> --%>

							</div>
						</c:forEach>
					</div>

				</c:forEach>

			</div>
		</div>
	</c:forEach>


</div>
<script>
	function hideRest(i) {

		let titles = document.getElementsByName('titles');
		for (var x = 0; x < titles.length; x++) {
			titles[x].style.display = "none";
		}
		document.getElementById(i.id).style.display = "block";

		columns = document.getElementsByName('column');
		for (var x = 0; x < columns.length; x++) {
			if (columns[x].getAttribute('value') != i.id) {
				columns[x].classList.remove("col-5");
				columns[x].classList.add("col-1")
				columns[x].style.display = "none";

			} else {
				columns[x].style.display = "block";
				columns[x].classList.remove("col-1");
				columns[x].classList.add("col-5")
			}

		}

	}

	function showAll(i) {

		columns = document.getElementsByName('column');
		for (var x = 0; x < columns.length; x++) {

			columns[x].style.display = "block";
			columns[x].classList.remove("col-5");
			columns[x].classList.add("col-1")


		}

	}

	// 	function hideRest(i) {

	// 	console.log(i.id);
	// }
</script>