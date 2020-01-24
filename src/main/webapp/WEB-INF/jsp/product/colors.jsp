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
				action="/order/color/saveChanes?orderId=${orderId}">

<div class="row border-bottom">
<div class="col-2 m-1">Product Feature</div>
<div class="col-4 m-1">Selected</div>
<div class="col-2 m-1">Color RAL</div>

</div>
					<c:forEach
						var="configList"
						items="${configuration.configurationList}">
                        <c:if test="${order[configList.name]!=null}">
                        <div class="row border-bottom">
                            <div class="col-2 m-1">${configList.name}</div>
                                <div class="col-4 m-1" id="${configList.name}">${order[configList.name]}</div>
                                
                                <div class="col-2 m-1">
                                    <select required name="${configList.id}" class="text-dark" value="">
                                     <option value="" selected>Select</option>
                                        <c:forEach var="color" items="${colors}">
                                        <option value="${color.id}" style="background-color:${color.hex}">${color.ral}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
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