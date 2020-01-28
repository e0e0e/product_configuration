<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<%@include file="../featureNavigation.jsp"%>


	<form method="post" action="/createFeature">


		<div>
			<label>Name:</label>
		</div>
		<input type="text" class="text-dark" name="name" value="" size="100">
		<div>
			<label>Description:</label>
		</div>
		<input type="text" class="text-dark" name="description" value="">
		<div>
			<label>Price:</label>
		</div>
		<input type="text" class="text-dark" name="price" value="">
		<div>
			<label>Image name:</label>
		</div>
		<input type="text" class="text-dark" name="imagePath" value="">
		<div>
			<label>Index:</label>
		</div>
		<input type="text" class="text-dark" name="index" value="">
<div>
			<label>Index M:</label>
		</div>
		<input type="text" class="text-dark" name="mIndex" value="">
		<div>
			<input type="submit" class="text-dark mt-5" value="Save">
		</div>
	</form>

</div>