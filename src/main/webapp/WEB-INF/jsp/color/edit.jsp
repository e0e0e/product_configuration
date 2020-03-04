<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<form method="post" action="/color/change?colorId=${color.id}">

<h3> Create Color: </h3>
		<div>
			<label>Name:</label>
		</div>
		<input type="text" class="text-dark" name="name" value="${color.name}">
		<div>
			<label>Type:</label>
		</div>
		<input type="text" class="text-dark" name="type" value="${color.type}">

		<div>
			<label>Ral:</label>
		</div>
		<input type="text" class="text-dark" name="ral" value="${color.ral}">
		<div>
			<label>Hex:</label>
		</div>
		<input type="text" class="text-dark" name="hex" value="${color.hex}">
		<div>
			<input type="submit" class="text-dark mt-5" value="Save">
		</div>
	</form>

</div>