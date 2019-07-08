<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Video details</title>
    <style>
        .row {
            overflow: hidden;
        }

        .caption {
            float: left;
            width: 80px;
            background-color: cadetblue;
            color: white;
            padding: 5px;
            border-bottom: 1px solid white;
        }

        .val {
            float: left;
            width: 300px;
            padding: 5px;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="caption">Tytuł:</div>
    <div class="val">${video.title}</div>
</div>
<div class="row">
    <div class="caption">Kategoria:</div>
    <div class="val">${video.category}</div>
</div>
<div class="row">
    <div class="caption">Ocena:</div>
    <div class="val">${video.rating}</div>
</div>

<a href="/video">Video List</a>
</body>
</html>
