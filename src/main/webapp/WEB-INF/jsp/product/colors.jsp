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


		<div class="card-header bg-info text-left text-light">
		<div class="row">
<div class="col-2 m-1">Product Feature</div>
<div class="col-4 m-1">Selected</div>
<div class="col-2 m-1">Color RAL</div>

</div>
		
		</div>

<div id="error"></div>
<div id="picture" style="position:absolute;top:30px;">

</div>
		<div class="card-body ">
			<form
				id="myFilterForm"
				method="post"
				action="/order/color/saveChanes?orderId=${orderId}">


					<c:forEach
						var="configList"
						items="${orderFeatures}">
                        
                        <div class="row border-bottom">
                            <div class="col-2 m-1">${configList.productFeature.name}</div>
                                <div class="col-4 m-1" id="${configList.productFeature.name}">${configList.feature.name}</div>
                                
                                <div class="col-2 m-1">
                                    <select name="${configList.productFeature.id}" class="text-dark" value="${configList.color.id}">
                                     <option value="" selected>${configList.color.ral}</option>
                                        <c:forEach var="color" items="${colors}">
                                        <option value="${color.id}" style="background-color:${color.hex}">${color.ral}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                       
					</c:forEach>
                </br>
				<input
					id="saveButton"
					type="submit"
					value="Save">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light"></div>
	</div>



</div>