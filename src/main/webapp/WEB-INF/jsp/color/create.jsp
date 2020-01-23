<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<form method="post" action="/color/create">

<h3> Create Color: </h3>
		<div>
			<label>Name:</label>
		</div>
		<input type="text" class="text-dark" name="name" value="">
		<div>
			<label>Ral:</label>
		</div>
		<input type="text" class="text-dark" name="ral" value="">
		<div>
			<label>Hex:</label>
		</div>
		<input type="text" class="text-dark" name="hex" value="">
		<div>
			<input type="submit" class="text-dark mt-5" value="Save">
		</div>
	</form>

</div>