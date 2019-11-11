<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<c:set var="resourcePath" scope="session"
	value="/resources/images/icons/png/" />
<html>
<head>
<title>${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="src/main/webapp/WEB-INF/jsp/script/canvas2image.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		$('[data-toggle="popover"]').popover();
		$('.popover-dismiss').popover({
			trigger : 'focus'
		});
	});

	/**
	 * This handler retrieves the images from the clipboard as a blob and returns it in a callback.
	 * 
	 * @see http://ourcodeworld.com/articles/read/491/how-to-retrieve-images-from-the-clipboard-with-javascript-in-the-browser
	 * @param pasteEvent 
	 * @param callback 
	 */
	function retrieveImageFromClipboardAsBlob(pasteEvent, callback) {
		if (pasteEvent.clipboardData == false) {
			if (typeof (callback) == "function") {
				callback(undefined);
			}
		}
		;

		var items = pasteEvent.clipboardData.items;

		if (items == undefined) {
			if (typeof (callback) == "function") {
				callback(undefined);
			}
		}
		;

		for (var i = 0; i < items.length; i++) {
			// Skip content if not image
			if (items[i].type.indexOf("image") == -1)
				continue;
			// Retrieve image on clipboard as blob
			var blob = items[i].getAsFile();

			if (typeof (callback) == "function") {
				callback(blob);
			}
		}
	}

	window.addEventListener("paste", function(e) {

		// Handle the event
		retrieveImageFromClipboardAsBlob(e, function(imageBlob) {
			// If there's an image, display it in the canvas
			if (imageBlob) {
				var canvas = document.getElementById("mycanvas");
				var ctx = canvas.getContext('2d');

				// Create an image to render the blob on the canvas
				var img = new Image();

				// Once the image loads, render the img on the canvas
				img.onload = function() {
					// Update dimensions of the canvas with the dimensions of the image
					canvas.width = this.width;
					canvas.height = this.height;

					// Draw the image
					ctx.drawImage(img, 0, 0);
				};

				// Crossbrowser support for URL
				var URLObj = window.URL || window.webkitURL;

				// Creates a DOMString containing a URL representing the object given in the parameter
				// namely the original Blob
				img.src = URLObj.createObjectURL(imageBlob);
			}
		});
	}, false);

	function to_image() {
		var canvas = document.getElementById("mycanvas");
		document.getElementById("theimage").src = canvas.toDataURL();
		Canvas2Image.saveAsPNG(canvas);
	}
</script>
<style>
.popover {
	background-color: #9FC53B;
}
</style>
</head>
<body>

	<%@include file="navigation.jsp"%>
	<jsp:include page="${path}.jsp"></jsp:include>
	<footer class="page-footer font-small blue pt-4 text-center mt-5"
		style="position: initial; bottom: 0; font-size: 10px;">
		<p class="wysiwyg-text-align-center">
			<strong><em>Avatars icons made by </em></strong><a
				href="https://www.freepik.com/home"><strong><em>Freepik</em></strong></a><strong><em>
					from </em></strong><strong><em><a href="http://www.flaticon.com/">www.flaticon.com</a></em></strong>
		</p>
	</footer>
</body>
</html>
