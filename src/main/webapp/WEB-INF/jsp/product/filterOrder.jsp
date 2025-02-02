<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
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
			<form id="myFilterForm" method="post" action="/filter/orderSaveEdited?orderId=${orderId}">

				<ul>
					<c:forEach var="configList" items="${configuration.configurationList}">
						<div class="row">
							<div class="col-3 bg-warning" id="pat-${configList.name}">${order[configList.name]}</div>
							<div class="col-2
							<c:if test=" ${configList.parent!='Chassis' }">
								bg-info text-light
								</c:if>
								">${configList.name}</div>
							<div class="col-4 m-1">
								<select name="${configList.id}" id="${configList.name}" class="w-100 custom-select" style="font-size:15px"
									onchange='changeAction(this,${configList.id})'>
									<option value="" selected disabled hidden>${order[configList.name]}</option>
									<c:forEach var="feature" items="${configList.feature}">
										<option value="${feature.id}">${feature.name}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</c:forEach>
				</ul>
				<input id="saveButton" type="submit" value="Save" class="m-4">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light"></div>
	</div>



</div>
<script>
	function changeAction(val, da) {

		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function () {
			if (this.readyState == 4 && this.status == 200) {

				products = JSON.parse(this.responseText);
				console.log(Object.getOwnPropertyNames(products).length);
				if (Object.getOwnPropertyNames(products).length > 0) {
					for (let key in products) {
						let old = document.getElementById(key);
						let select = '<option value="" disabled hidden></option>';
						if (products.hasOwnProperty(key)) {

							if (products[key].length > 1) {
								for (let k in products[key]) {

									if (products[key][k].selected == false) {
										select = select.concat('<option value="' + products[key][k].id +
											'" onmouseover="normalImg(this)">' + products[key][k].name +
											'</option>');
									} else {
										select = select.concat('<option value="' + products[key][k].id +
											'" selected  class="bg-warning" onmouseover="normalImg(this)">' +
											products[key][k].name + '</option>');

									}
								}
							} else {
								select = select.concat('<option value="' + products[key][0].id +
									'" selected class="bg-danger" onmouseover="normalImg(this)">' + products[key][
										0
									].name + '</option>');

							}

							select.concat("</select>");
						}
						if (key != null) {

							document.getElementById(key).innerHTML = select;


						}
					}
					checkIfChangedNames(products);
				} else {
					document.getElementById("error").innerHTML =
					"No product found, refresh and search different configuration, or mak as not standard order";
					document.getElementById("error").style.color = "red";
					document.getElementById("myFilterForm").style.backgroundColor = "orange";
				}


			}
		};

		document.getElementById("saveButton").style.display = 'block';
		let arr = {};
		for (var i = 0; i < document.getElementById("myFilterForm").elements.length; i++) {
			let valueFromForm = document.getElementById("myFilterForm").elements[i].value;

			if (valueFromForm != "" && valueFromForm != 'Save') {
				arr[document.getElementById("myFilterForm").elements[i].id] = valueFromForm;

			}
		}

		let fString = JSON.stringify(arr);

		xhttp.open("POST", "/product/matching", true);
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(fString);

	}

	function checkIfChangedNames(pro) {
		for (let key in pro) {
			console.log(key);
			let old = document.getElementById("pat-" + key);
			let newOption = document.getElementById(key);
				if (old.innerHTML == newOption.options[newOption.selectedIndex].text) {
				console.log(old.innerHTML + " == " + newOption.options[newOption.selectedIndex].text);
				document.getElementById(key).style.color = "green";
			} else {
				console.log(old.innerHTML + " != " + newOption.options[newOption.selectedIndex].text);
				document.getElementById(key).style.color = "red";
			}
		}

	}
</script>