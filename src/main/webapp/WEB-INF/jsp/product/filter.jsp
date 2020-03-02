<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
		<c:set var = "imagesPath" value = "http://konfigurator.viberti.pl/imagesLd/"/>
		<c:set var = "default" value = "default.png"/>

	<div id="NS"></div>
<div class="container-fluid" id="all"  style="min-width: 1000px;">
	<%@include file="../featureNavigation.jsp"%>

	<div id="rest"></div>
	
	<h3>
		Configuration of order
	</h3>


	<div class="card text-dark m-1">


		<div class="card-header bg-info text-left text-light">
		<div class="row">
		<div class="col-2 m-1">Product Feature</div>
		<div class="col-5 m-1">Select</div>
		<div class="col-1 m-1"></div>
		<div class="col-2 m-">NS details</div>

		</div>
		</div>


		<div class="card-body ">
			<form
				id="myFilterForm"
				method="post"
				action="/filter/orderCreate">

				
					<c:forEach
						var="configList"
						items="${configuration.configurationList}">
						<div class="row">
							<div class="col-2 col-form-label 
							<c:if test="${configList.parent!='Chassis'}">
							bg-info text-light
							</c:if>
							">${configList.name}</div>
							<div class="col-5 ">
								<select required
									name="${configList.id}"
									id="${configList.name}"
									onchange='changeAction(this,${configList.id})'
									class="w-100 custom-select" style="font-size:15px">
									<option
										value=""
										selected
										disabled
										hidden>Select</option>
									<c:forEach
										var="feature"
										items="${configList.feature}">
										<option value="${feature.id}">${feature.name}</option>

									</c:forEach>
								</select>
							
							</div>
							<div class="col-1">
							<div class="btn btn-outline-warning form-control" align="right" onclick="notStandard(this)" value="${configList.name}" id="bns-${configList.name}" style="display:none;">NS</div>
							</div>
							
						
						<div class="col-2">
						<%-- <div class="text-dark" id="NS-${configList.name}"></div> --%>
						<input type="text" class="text-dark form-control-plaintext border-bottom border-warning m-2"  readonly name="nst-${configList.id}" id="NS-${configList.name}" value="" style="display:none;">
						</div>
						<div class="col-1">
							<div class="text-dark">
								<a href="${imagesPath}${configList.imagePath}" target="_blank" class="rys">						
								<img src="${imagesPath}${configList.imagePath}" alt="Flowers in Chania" width="50" id="img-${configList.name}" onerror="imgError(this)" >
								</a>	
							</div>
						</div>
						<div class="col-1"  id="ind-${configList.name}">
						</div>
						</div>
					</c:forEach>
				
				<input
					id="saveButton"
					type="submit"
					value="Save" class="m-4" style="display:none;">
			</form>
		</div>
		<div class="card-footer bg-info text-right text-light"></div>
	</div>



</div>
<div id="table"></div>
<script>
function changeAction(val,da) {
something = "${imagesPath}";
	 var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	         if (this.readyState == 4 && this.status == 200) {
	        	 
	        	 products=JSON.parse(this.responseText);
	        	
	        	 
	        	 for (let key in products){
	        		 let select='<option value="" selected disabled hidden>Select</option>';
	        		   if(products.hasOwnProperty(key)){

	        		    if(products[key].length>1){	        		   
	        		     for (let k in products[key]){
	        		    	
	        		    	
	        		    	 if(products[key][k].selected==false){
	        		    	 select=select.concat('<option value="'+products[key][k].id+'">'+products[key][k].name+'</option>');
						
        		    	 }else{
	        		    		 select=select.concat('<option value="'+products[key][k].id+'" selected>'+products[key][k].name+'</option>'); 
											let imagePath = products[key][k].imagePath;
											let selected=document.getElementById(key);
											 if(selected.style.backgroundColor!=="salmon"){
												selected.style.backgroundColor="PaleGreen";
											}
											   if(imagePath!==''){
													let e=document.getElementById("img-"+key);
													e.src=something.concat(imagePath);
													e.style.width="100px";
													e.parentElement.href=something.concat(imagePath);
											   }else{
													let e=document.getElementById("img-"+key);
													e.src=something.concat('0.png');
													e.style.width="1px";
													e.parentElement.href=something.concat(imagePath);

											   }
											document.getElementById("bns-"+key).style.display="block";
											let ind=document.getElementById("ind-"+key);
											ind.innerHTML=products[key][0].index;
								
								// es.parentElement.href=something.concat(imagePath);
								// es.width="1";


	        		    	 }
	        	 	
	        		    	 }
							 
	        		     }else{
	        		    	 select=select.concat('<option value="'+products[key][0].id+'" selected>'+products[key][0].name+'</option>');  
							 				let selected=document.getElementById(key);
											 if(selected.style.backgroundColor!=="salmon"){
												selected.style.backgroundColor="PaleGreen";
											}
											let e=document.getElementById("img-"+key);
							 				let imagePath = products[key][0].imagePath;
											   if(imagePath!==''){
													
													e.src=something.concat(imagePath);
													e.style.width="100px";
													e.parentElement.href=something.concat(imagePath);
											   }else{
													e.src=something.concat('0.png');
													e.style.width="1px";
													e.parentElement.href=something.concat(imagePath);

											   }
											document.getElementById("bns-"+key).style.display="block";
											document.getElementById("saveButton").style.display="block";
											let ind=document.getElementById("ind-"+key);
											ind.innerHTML=products[key][0].index;
											
	        		     }
	        		    select.concat("</select>");
					
	        		   }
					   if(key!=null){
						   console.log(key);
	        		   document.getElementById(key).innerHTML=select;
					   }
					  
					}
			
		// 	tableString="";
	    //      for (let key in products){
		// 		  tableString=tableString.concat('<div class="row">');
		// 			if(products[key].length>1){	        		   
	    //     		     for (let k in products[key]){
		// 					 tableString=tableString.concat('<div class="col">'+products[key][k].name+'</div>');
		// 				 }
		// 			}
		// 			tableString=tableString.concat('</div');
		// 	}
	        	
	    //   document.getElementById("table").innerHTML=tableString;
	         }
	    };
	    
	    document.getElementById("saveButton").style.display = 'block';
	    let arr={};
  	  for (var i = 0; i < document.getElementById("myFilterForm").elements.length; i++) {
  		  let valueFromForm=document.getElementById("myFilterForm").elements[i].value;
  		
  		  if(valueFromForm!="" && valueFromForm!='Save'){
  			  arr[document.getElementById("myFilterForm").elements[i].id]=valueFromForm;
  	
  		  }
  		}

        let fString=JSON.stringify(arr);
   		console.log(fString);
	    xhttp.open("POST", "/product/matching", true);
	    xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(fString);
	
}

 function notStandard(przycisk)
{
	var poczatek='';

	var koniec="";
	let featureId=przycisk.getAttribute('value');
	var selectedElement=document.getElementById(featureId);
	let wewnatrz=selectedElement.options[selectedElement.selectedIndex].text;
   if (document.getElementById('NS').innerHTML.length!=0){

    document.getElementById('NS').innerHTML='';

    }  else{

    if(false){
    //usuwa skreslenie
         //usun_skr(wewnatrz,poczatek,koniec,przycisk.value);
      } else{
       if (wewnatrz!="Select"){
       //gdy nic nie byl zmienione
	   console.log(selectedElement.options[selectedElement.selectedIndex].text);
       //poczatek=poczatek.concat(wewnatrz);
       poczatek=poczatek.concat(koniec);
      // alert(poczatek);

      //document.getElementById(przycisk.value).innerHTML=poczatek;
       document.getElementById('NS').innerHTML="<div class='ns_form'>No standard: \
      <textarea id='opis_ns' lass='form-control' style='position:relativel;width:100%;height:240px;margin: auto;'></textarea> \
       <br><button type='button'  id_zmiana="+wewnatrz+" class='btn btn-default btn-sm' onclick='conf_ns(this)'>  \
          <span class='glyphicon glyphicon-ok-sign'></span> OK \
        </button> \
        <button type='button' class='btn btn-default btn-sm' onclick='NO_conf_ns(this)'>  \
          <span class='glyphicon glyphicon-remove-sign'></span> Cancel \
        </button> \
        <span id='przekreslone' style='display:none;'>"+poczatek+"</span> \
        <span id='przekreslone2' style='display:none;'value='"+featureId+"'>"+wewnatrz+"</span> \
        </div>";
      }
      }
      }

}


function conf_ns(to){

var new_desc=document.getElementById('przekreslone').innerHTML;
var id_z=document.getElementById('przekreslone2').innerHTML;
let featureId=document.getElementById('przekreslone2').getAttribute('value');
let newId="NS-".concat(featureId);
let e=document.getElementById(featureId);

  new_desc=new_desc.concat("");
  new_desc=new_desc.concat(document.getElementById('opis_ns').value);
  document.getElementById('NS-Chassis').value="No standard";
  document.getElementById('NS-Chassis').style.display="block";

  document.getElementById(newId).value=new_desc;
  document.getElementById(newId).style.display="block";
  document.getElementById(featureId).style.backgroundColor="Salmon";
  document.getElementById('NS').innerHTML='';

}
function NO_conf_ns(to){
    document.getElementById('NS').innerHTML='';

}

</script>