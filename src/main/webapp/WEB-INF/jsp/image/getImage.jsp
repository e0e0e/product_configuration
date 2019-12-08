<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container bg-info">
	<p>
		Focus this tab and press
		<kbd>CTRL</kbd>
		+
		<kbd>V</kbd>
		. The image on your clipboard will be rendered on the canvas !
	</p>
	<div class="text-center">
	<div><button onclick="to_image()">Draw to Image</button></div>
	<div><button onclick="to_ftp_image()">Save to ftp</button></div>
	<div><a href="/image/list"
                    class="btn btn-outline-info btn-sm">Ftp list</a></div>
	<image id="theimage"></image>
		<canvas class="p-2 m-2" style="border: 1px solid grey; width: 80%"
			id="mycanvas">
    
	</div>


</div>
