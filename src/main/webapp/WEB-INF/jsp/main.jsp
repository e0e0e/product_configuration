<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	contentType="text/html;charset=UTF-8"
	language="java"%>
<c:set
	var="resourcePath"
	scope="session"
	value="/resources/images/icons/png/" />
			<c:set var = "imagesPath" value = "http://konfigurator.viberti.pl/imagesLd/"/>
		<c:set var = "default" value = "default.png"/>
<html>
<head>
<title>${title}</title>
<meta
	name="viewport"
	content="width=device-width, initial-scale=1">
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
	<script src="/resources/js/canvas2image.js"></script>

<script>
	$(document).ready(function() {
		$('[data-toggle="popover"]').popover();
		$('.popover-dismiss').popover({
			trigger : 'focus'
		});
		
	});

function imgError(es)
{
	
var something = "${imagesPath}";
var imagePath = "0.png";
es.src=something.concat(imagePath);
es.parentElement.href=something.concat(imagePath);
es.width="1";

}
	
</script>
<style>


.popover {
	background-color: #9FC53B;
}

.box {
	background: rgb(238, 238, 240);
	
	box-shadow: 9px 9px 16px rgb(87, 87, 87, 0.6), -9px -9px 16px
		rgba(248, 249, 250, 0.5);
	/* For Neumorphism Effect */
}
.greyText{
color: #a9cfee;
}

.border-3 {
    border-width:3px !important;
}
.ns_form{
top: 20%;
right: 5%;
margin-top: -100px; /* Negative half of height. */

display: block;
position:fixed;
background-color: #ffffff;
padding:5% 5%;
 box-shadow: 10px 10px 5px grey;
  border-radius: 10px;
  border: solid 1px #000000;
  z-index: 9999;
  
}
table, th , td {
  border: 1px solid grey;
  border-collapse: collapse;
  padding: 5px;
}

table tr:nth-child(odd) {
  background-color: #f1f1f1;
}

table tr:nth-child(even) {
  background-color: #ffffff;
}

.line:hover {
  background-color: #dddcd7;
}
.selectMono{
    font-family:"Courier New", Courier, monospace;
}
.bat:hover {
  background-color: yellow;
  color:black;
}
.greybat:hover {
  background-color: LightGray;
  color:black;
}
.opis {
    font-size: 20px;
    margin: 20px;
    padding: 20px;
    width: auto;
}
#toc_container {
    background: #f9f9f9 none repeat scroll 0 0;
    border: 1px solid #aaa;
    display: table;
    font-size: 20px;
    margin-bottom: 1em;
    padding: 20px;
    width: auto;
}

.toc_title {
    font-weight: 700;
    text-align: center;
}

#toc_container li, #toc_container ul, #toc_container ul li{
    list-style: outside none none !important;
}

.helpimg {
  border: 4px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 80%;
}
#myBtn {
  display: none;
  position: fixed;
  bottom: 20px;
  right: 30px;
  z-index: 99;
  font-size: 18px;
  border: none;
  outline: none;
  background-color: lightblue;
  color: white;
  cursor: pointer;
  padding: 15px;
  border-radius: 4px;
}

#myBtn:hover {
  background-color: #555;
}
</style>
</head>
<body>
<div id="load">

</div>
<div id="content" style="display:block;">
	<%@include file="navigation.jsp"%>
	<jsp:include page="${path}.jsp"></jsp:include>
	<footer
		class="page-footer font-small blue pt-4 text-center mt-5"
		style="position: initial; bottom: 0; font-size: 10px;">
		<p class="wysiwyg-text-align-center">
			<strong><em>Avatars icons made by </em></strong>
			<a href="https://www.freepik.com/home">
				<strong><em>Freepik</em></strong>
			</a>
			<strong><em> from </em></strong><strong><em><a
						href="http://www.flaticon.com/">www.flaticon.com</a></em></strong>
		</p>
	</footer>
	</div>
</body>
</html>
