<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
<div class="container bg-info" >
	<div id="info" value="${feature.id}" class="text-center m-2">
		Focus this tab and press
		<kbd>CTRL</kbd>
		+
		<kbd>V</kbd>
		. The image on your clipboard will be rendered on the canvas !
	</div>
	<div class="text-center">
    
    <div class="row bg-secondary text-light">
    <div class="col" id="featureName" value="${feature.name}">
    ${feature.name}
    </div>
    <div class="col">
    ${feature.index}
    </div>
     <div class="col">
    ${feature.imagePath}
    </div>
     <div class="col" id="fileName">

    </div>
    </div>
	<div><button onclick="saveCanvasImage()" id="saveButton">Save to ftp</button></div>

	<image id="theimage"></image>
		<canvas class="p-2 m-2" style="border: 1px solid grey; width: 80%"
			id="mycanvas">
    
	</div>


</div>
<script>

function saveCanvasImage(){
	let myCanvas=document.getElementById("mycanvas");
    let imageData = myCanvas.toDataURL();
    let featureId=document.getElementById("info").getAttribute("value");
    let  featureName=document.getElementById("featureName").getAttribute("value");
    console.log(featureName);
    // document.getElementById("mycanvas").style.display="none";
    // document.getElementById("saveButton").style.display="none";
    

    $.ajax({
        url:'/image/saveCanvasImageToFtp',
        data:{imageBase64: imageData, featureName: featureName},
        type: 'post',
        dataType: 'text',
        timeout: 10000,
        async: false,
        error: function(){
            console.log("WOOPS: send file to server failed");
        },
        success: function(fileName){
            if(fileName.ret==0){
                console.log("FAIL : " + fileName.msg);
                 console.log("No file saved?");
               
            }else{
                console.log("<a href='/feature/saveImageName?imagePath="+fileName+"&featureId="+featureId+">Save?</a>");
                document.getElementById("fileName").innerHTML=fileName;
              document.getElementById("info").innerHTML="<a class='bg-warning m-2 p-2' href='/feature/saveImageName?imagePath="+fileName+"&featureId="+featureId+"'>Save?</a>";
            }
        }
    });
}


function to_image(){
                var canvas = document.getElementById("mycanvas");
                document.getElementById("theimage").src = canvas.toDataURL();
              //  Canvas2Image.saveAsPNG(canvas);
            }

function retrieveImageFromClipboardAsBlob(pasteEvent, callback){
	if(pasteEvent.clipboardData == false){
        if(typeof(callback) == "function"){
            callback(undefined);
        }
    };

    var items = pasteEvent.clipboardData.items;

    if(items == undefined){
        if(typeof(callback) == "function"){
            callback(undefined);
        }
    };

    for (var i = 0; i < items.length; i++) {
        // Skip content if not image
        if (items[i].type.indexOf("image") == -1) continue;
        // Retrieve image on clipboard as blob
        var blob = items[i].getAsFile();

        if(typeof(callback) == "function"){
            callback(blob);
        }
    }
}

window.addEventListener("paste", function(e){

    // Handle the event
    retrieveImageFromClipboardAsBlob(e, function(imageBlob){
        // If there's an image, display it in the canvas
        if(imageBlob){
            var canvas = document.getElementById("mycanvas");
            var ctx = canvas.getContext('2d');
            
            // Create an image to render the blob on the canvas
            var img = new Image();

            // Once the image loads, render the img on the canvas
            img.onload = function(){
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

</script>