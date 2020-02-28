<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="message"
	style="background-color: #bce9ff; position:fixed;z-index:9000;left:10%; top: 10%; width: 50%;  min-height:500px; text-align:center;display:none;box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"
	class="rounded"></div>

<div class="container-fluid" id="all">
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
	<div class="bg-secondary text-white p-2" id="" name="" onclick="addFtoPFinProducts(this)">Add to All products in:
		<span id="productFeature"></span></div>
	<div class="row">
		<div id="feature" class="col font-weight-bold bg-secondary" onclick="addFeature(this)">Add feature</div>
		<div id="feature" class="col font-weight-bold bg-warning" onclick="removeFeature(this)">Remove feature</div>
	</div>
	</br>
	<div class="row d-flex flex-nowrap ">
		<div class="col-1 border">
			Product title
		</div>
		<c:forEach var="title" items="${columntitles}">
			<div class="col-1 border" name="column" value="${title}" att="1">
				${title}
			</div>
		</c:forEach>

	</div>
	<c:forEach var="configuration" items="${configurations}">
		<div class="">
			<div class="row d-flex flex-nowrap ">
				<div class="col-1  border-light my-auto pt-4 pb-4">
					<p style="word-wrap:break-word;" name="product" id="${configuration.id}">${configuration.name}
					</p>
				</div>
				<c:forEach var="configList" items="${configuration.configurationList}">

					<div class="col-1 border" name="column" id="" value="${configList.name}" att="0">
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
								<p style="word-wrap:break-word" id="${feature.id}">${feature.name}</p>
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
	let onlyOne = 0;

	function hideRest(i) {

		let titles = document.getElementsByName('titles');
		for (var x = 0; x < titles.length; x++) {
			titles[x].style.display = "none";
		}
		document.getElementById(i.id).style.display = "block";

		let columns = document.getElementsByName('column');
		for (var x = 0; x < columns.length; x++) {
			if (columns[x].getAttribute('value') != i.id) {
				if (onlyOne == 0) {
					columns[x].classList.remove("col-4");
					columns[x].classList.add("col-1")
					columns[x].style.display = "none";
				}
			} else {
				columns[x].style.display = "block";
				columns[x].classList.remove("col-1");
				columns[x].classList.add("col-4");
				document.getElementById("productFeature").innerHTML = i.id;

			}

		}
		onlyOne = 1;
	}

	function showAll(i) {
		onlyOne = 0;
		let columns = document.getElementsByName('column');
		for (var x = 0; x < columns.length; x++) {

			columns[x].style.display = "block";
			columns[x].classList.remove("col-4");
			columns[x].classList.add("col-1")


		}

	}

	function addFtoPFinProducts(featureId, featureName) {
		console.log(featureName);
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {

				// products = JSON.parse(this.responseText);
				alert(this.responseText);
				goBack();
				columns = document.getElementsByName("column");
				columnsArray = Array.prototype.slice.call(columns, 0);
				columnsPf = columnsArray.filter(function (x) {
					return x.getAttribute("value") == productFeatureName;
				});

				for (col of columnsPf) {
					let match = 0;
					if (col.getAttribute("att") == 0) {
						for (row of col.children) {

							console.log(row.children[0].getAttribute("id") == featureId);

							if (row.children[0].getAttribute("id") == featureId) {
								console.log("match in: " + row.children[0].innerHTML);
								match = 1;

							}


						}
						if (match == 0) {
							let newdiv=document.createElement("div");			
							let para = document.createElement("P");
							newdiv.appendChild(para);
							para.innerHTML = featureName;

							newdiv.classList.add("bg-warning");
							newdiv.classList.add("rounded");
							newdiv.classList.add("text-dark");
							newdiv.classList.add("m-1");
							newdiv.classList.add("p-1");
							para.setAttribute("id", featureId);
							col.appendChild(newdiv);

						}
					}


				}


			}
		};



		let arr = {};
		let productsToChange = document.getElementsByName("product");
		productsId = [];
		productFeatureName = document.getElementById("productFeature").innerHTML;

		for (var i = 0; i < productsToChange.length; i++) {
			productsId.push(productsToChange[i].getAttribute('id'));
		}

		let jProductsId = JSON.stringify({
			productsId,
			productFeatureName,
			featureId
		});
		console.log(jProductsId);
		xhttp.open("POST", "/add/feature/products", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(jProductsId);

	}
function deleteFfromPFinProducts(featureId, featureName) {
	console.log(featureName);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {

			// products = JSON.parse(this.responseText);
			alert(this.responseText);
			goBack();
			columns = document.getElementsByName("column");
			columnsArray = Array.prototype.slice.call(columns, 0);
			columnsPf = columnsArray.filter(function (x) {
				return x.getAttribute("value") == productFeatureName;
			});

			for (col of columnsPf) {
				
				if (col.getAttribute("att") == 0) {
					console.log(col.getAttribute("att"));
					for (row of col.children) {
					console.log(row.children.length+" at");

						if (row.children.length > 0) {
							if (row.children[0].getAttribute("id") == featureId) {
								console.log("match in: " + row.children[0].innerHTML);
								row.children[0].style.textDecoration = "line-through";
								row.classList.remove("bg-info");
								row.classList.add("bg-danger");
								

							}

						}
					}
	
				}


			}


		}
	};



		let arr = {};
		let productsToChange = document.getElementsByName("product");
		productsId = [];
		productFeatureName = document.getElementById("productFeature").innerHTML;

		for (var i = 0; i < productsToChange.length; i++) {
			productsId.push(productsToChange[i].getAttribute('id'));
		}

		let jProductsId = JSON.stringify({
			productsId,
			productFeatureName,
			featureId
		});
		console.log(jProductsId);
		xhttp.open("POST", "/delete/feature/products", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(jProductsId);

	}

	function addFeature(input) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {

				features = JSON.parse(this.responseText);
				message = "<h1 class='bat glyphicon glyphicon-arrow-left text-info' onclick='goBack()'></h1>";
				message = message.concat("<h1>Select feature to add:</h1>");

				for (f of features) {
					message = message.concat(
						"<div class='bat row border-bottom  border-light ml-5 mr-5 mt-2' onclick='addFtoPFinProducts(" +
						f["id"] + ",\"" + f["name"] + "\")' id='feat-" + f["id"] + "'><div class='col'>" + f[
							"name"] + "</div><div class='col'>" + f["index"] + "</div></div>");
				}

				let e = document.getElementById("message");
				e.innerHTML = message;
				e.style.display = "block";
				document.getElementById("all").style.display = "none";
			}
		};

		productFeatureName = document.getElementById("productFeature").innerHTML;
		let jProductFeatureName = JSON.stringify(productFeatureName);
		console.log(jProductFeatureName);
		xhttp.open("POST", "/find/featuresByPf", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(productFeatureName);

	}

	function removeFeature(input) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {

				features = JSON.parse(this.responseText);
				message = "<h1 class='bat glyphicon glyphicon-arrow-left text-info' onclick='goBack()'></h1>";
				message = message.concat("<h1>Select feature to remove:</h1>");

				for (f of features) {
					message = message.concat(
						"<div class='bat row border-bottom  border-light ml-5 mr-5 mt-2' onclick='deleteFfromPFinProducts(" +
						f["id"] + ",\"" + f["name"] + "\")' id='feat-" + f["id"] + "'><div class='col'>" + f[
							"name"] + "</div><div class='col'>" + f["index"] + "</div></div>");
				}

				let e = document.getElementById("message");
				e.innerHTML = message;
				e.style.display = "block";
				document.getElementById("all").style.display = "none";
			}
		};

		productFeatureName = document.getElementById("productFeature").innerHTML;
		let jProductFeatureName = JSON.stringify(productFeatureName);
		console.log(jProductFeatureName);
		xhttp.open("POST", "/find/featuresByPf", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(productFeatureName);

	}


	function goBack() {
		document.getElementById("all").style.display = "block";
		document.getElementById("message").style.display = "none";


	}
</script>