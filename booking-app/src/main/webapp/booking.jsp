<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="header.jsp"%>
<style>
.element-margin {
	margin-bottom: 5px;
}

.button-margin {
	margin-right: 5px;
}
</style>
<script type="text/javascript" charset="utf-8">
	function selectPayOption() {
		var e = document.getElementById("payOption");
		var strUser = e.value;
		document.getElementById("loadingchargespay").value = strUser;
		document.getElementById("doorpickchargespay").value = strUser;
		(document.getElementById("freightvalue").value = "");
		(document.getElementById("loadingcharges").value = "");
		(document.getElementById("doorpickcharges").value = "");
		document.getElementById("topay").value = "";
		document.getElementById("paid").value = "";
		if(strUser=='FS'){
			document.getElementById("topay").value = 0.0;
			document.getElementById("paid").value = 0.0;
			document.getElementById("freightvalue").value = 0.0;
			document.getElementById("loadingcharges").value = 0.0;
			document.getElementById("doorpickcharges").value = 0.0;
			document.getElementById("freightvalue").readOnly  = true;
			document.getElementById("loadingcharges").readOnly  = true;
			document.getElementById("doorpickcharges").readOnly  = true;
			document.getElementById("total").value = 0.0;
			document.getElementById("cash").value = 0.0;
			document.getElementById("total").readOnly  = true;
			document.getElementById("cash").readOnly  = true;
			document.getElementById("refund").readOnly  = true;
		}else{
			document.getElementById("freightvalue").readOnly  = false;
			document.getElementById("loadingcharges").readOnly  = false;
			document.getElementById("doorpickcharges").readOnly  = false;
			document.getElementById("cash").readOnly  = false;
			document.getElementById("refund").readOnly  = false;
			document.getElementById("total").value = '';
			document.getElementById("cash").value = '';
		}
		document.getElementById("loadingchargespay").readOnly  = true;
		document.getElementById("doorpickchargespay").readOnly  = true;
	}
	function disabledFieldsOnLoad() {
		document.getElementById("loadingchargespay").readOnly  = false;
		document.getElementById("doorpickchargespay").readOnly  = false;
		document.getElementById("refund").disabled = true;
	}
	function checkFromNameExists() {
		var value = document.getElementById("from_phone").value;
		if(!checkIfValidIndianMobileNumber(value)){
			alert('Please Enter a Valid Mobile Number');
			document.getElementById("from_phone").value="";
		}
		else{
				$.ajax({
					url : "/searchFromCustomerName/" + value,
					type : "get",
					cache : false,
					success : function(data) {
						if (data.length > 0) {
							document.getElementById("fromName").value = data;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log('ERROR:' + XMLHttpRequest.status
								+ ', status text: ' + XMLHttpRequest.statusText);
					}
				});
		}
	}
	function checkToNameExists() {
		var value = document.getElementById("to_phone").value;
		if(!checkIfValidIndianMobileNumber(value)){
			alert('Please Enter a Valid Mobile Number');
			document.getElementById("to_phone").value="";
		}
		else{
					$.ajax({
						url : "/searchToCustomerName/" + value,
						type : "get",
						cache : false,
						success : function(data) {
							if (data.length > 0) {
								document.getElementById("toName").value = data;
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							console.log('ERROR:' + XMLHttpRequest.status
									+ ', status text: ' + XMLHttpRequest.statusText);
						}
					});
		}
	}
	function getBillingDoc() {
		$.ajax({
			url : "/searchToCustomerName/" + value,
			type : "get",
			cache : false,
			success : function(data) {
				if (data.length > 0) {
					document.getElementById("toName").value = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('ERROR:' + XMLHttpRequest.status
						+ ', status text: ' + XMLHttpRequest.statusText);
			}
		});
	}
	
	function getCharges() {
		var fromLocation = document.getElementById("fromLocation").value;
		var toLocation = document.getElementById("toLocation").value;
		var payOption = document.getElementById("payOption").value;

		if(fromLocation.length==0 && toLocation.length==0){
			alert('Select From and To Location!');
			return false;
		}

		if(payOption=='FS'){
			document.getElementById("loadingcharges").readOnly  = true;
			document.getElementById("doorpickcharges").readOnly  = true;
			document.getElementById("freightvalue").value=0;
			document.getElementById("loadingcharges").value=0;
			document.getElementById("doorpickcharges").value=0;	
			return true;
		}
		$.ajax({
			url : "/getCharges",
			type : "POST",
			cache : false,
			data: { 
				fromLocation: $("#fromLocation").val(), 
				toLocation: $("#toLocation").val(),
				loadingchargespay: $("#loadingchargespay").val()
			  },
			success : function(data) {
				if (data.length > 0) {

					var val=data.replace("}","").split((/[,]+/));

							var firstValue = val[0];
							var secondValue = val[1];
							var thirdValue = val[2];

							if (firstValue.includes("FREIGHT") == true) {
								var fv = firstValue.split((/[=]+/))[1]
								document.getElementById("freightvalue").value = fv;
								$("#freightvalue").attr({
									"min" : fv
								});
								sumAmount();

							}

							if (firstValue.includes("FUEL CHARGES") == true) {
								document.getElementById("doorpickcharges").value = firstValue
										.split((/[=]+/))[1];
							} else if (secondValue.includes("FUEL CHARGES") == true) {
								document.getElementById("doorpickcharges").value = secondValue
										.split((/[=]+/))[1];
							}

							var itemcount = document
									.getElementById("item_count").value;

							if (firstValue.includes("LOADING CHARGES") == true) {
								var charge = firstValue.split((/[=]+/))[1];
								charge = charge * itemcount;
								document.getElementById("loadingcharges").value = charge;

							} else if (secondValue.includes("LOADING CHARGES") == true) {
								var charge = secondValue.split((/[=]+/))[1];
								charge = charge * itemcount;
								document.getElementById("loadingcharges").value = charge;
							} else if (thirdValue.includes("LOADING CHARGES") == true) {
								var charge = thirdValue.split((/[=]+/))[1];
								charge = charge * itemcount;
								document.getElementById("loadingcharges").value = charge;
							}
							sumAmount();
							document.getElementById("loadingcharges").readOnly = true;
							document.getElementById("doorpickcharges").readOnly = true;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console
								.log('ERROR:' + XMLHttpRequest.status
										+ ', status text: '
										+ XMLHttpRequest.statusText);
					}
				});
	}
	function getLoadingCharges() {
		var fromLocation = document.getElementById("fromLocation").value;
		var toLocation = document.getElementById("toLocation").value;
		var payOption = document.getElementById("loadingchargespay").value;

		if(fromLocation.length==0 && toLocation.length==0){
			alert('Select From and To Location!');
			return false;
		}

		if(payOption=='FS'){
			document.getElementById("loadingcharges").value=0;
			return true;
		}
		$.ajax({
			url : "/getLoadingCharges",
			type : "POST",
			cache : false,
			data: { 
				fromLocation: $("#fromLocation").val(), 
				toLocation: $("#toLocation").val(),
			  },
			success : function(data) {
				if (data.length > 0) {
					document.getElementById("loadingcharges").value = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('ERROR:' + XMLHttpRequest.status
						+ ', status text: ' + XMLHttpRequest.statusText);
			}
		});
	}
	function getFuelCharges() {
		var fromLocation = document.getElementById("fromLocation").value;
		var toLocation = document.getElementById("toLocation").value;
		var payOption = document.getElementById("doorpickchargespay").value;

		if(fromLocation.length==0 && toLocation.length==0){
			alert('Select From and To Location!');
			return false;
		}

		if(payOption=='FS'){
			document.getElementById("doorpickcharges").value=0;
			return true;
		}
		$.ajax({
			url : "/getFuelCharges",
			type : "POST",
			cache : false,
			data: { 
				fromLocation: $("#fromLocation").val(), 
				toLocation: $("#toLocation").val(),
			  },
			success : function(data) {
				if (data.length > 0) {
					document.getElementById("doorpickcharges").value = data;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('ERROR:' + XMLHttpRequest.status
						+ ', status text: ' + XMLHttpRequest.statusText);
			}
		});
	}
	function sumAmount() {
		var option = document.getElementById("loadingchargespay").value;
		var frieght = Number(document.getElementById("freightvalue").value);
		if (frieght < 200) {
			alert('Frieght value should not be less than 200');
		}
		var loading = Number(document.getElementById("loadingcharges").value);
		var doorPick = Number(document.getElementById("doorpickcharges").value);
		let total = frieght + loading + doorPick;
		
		
		
		total =total+(total/100) *5;
		
		var roundOff=0;
		
		
		var modValue= Math.round(total)%10;
		
		if(modValue >= 0 && modValue <=2 )
		{
			roundOff=total-modValue;
		}
		
		if(modValue >= 3 && modValue <=7 )
		{
			roundOff=total-modValue+5;
		}
		
		if(modValue >= 8 && modValue <=9 )
		{
			roundOff=total-modValue+10;
		}
		
		
		if (option == 'TOPAY') {
			document.getElementById("topay").value = Math.round(roundOff);
			document.getElementById("cash").disabled=true;
		} else if (option == 'PAID') {
			document.getElementById("paid").value = Math.round(roundOff);
			document.getElementById("cash").disabled=false;
		}
		
		
		
		document.getElementById("total").value = Math.round(roundOff);
	}
	
	function sumRefund() {
		var total = Number(document.getElementById("total").value);
		var cash = Number(document.getElementById("cash").value);
		let refund = cash -total;
		document.getElementById("refund").value = refund;
	}
	
	function getLRNumberSearch() {
		var x = document.getElementById("lrNumber").value;
		x = x.replaceAll('/', '%2F');
		window.location.href = "/searchBookingParcelLRNO?lrNumber=" + x;

	}
	function loadNonReadBookingForm() {
		var id = document.getElementById("bid").value;
		if (id > 0) {
			document.getElementById("bclear").disabled = true;
			document.getElementById("new").disabled = false;
			$.each($('form').serializeArray(), function(index, value) {
				$('[name="' + value.name + '"]').attr('readonly', 'readonly');
			});
		}else{
			document.getElementById("new").disabled = true;
		}
	}
	function loadReadBookingForm() {
		var id = document.getElementById("bid").value;
		if (id > 0) {
			document.getElementById("bclear").disabled = false;
			document.getElementById("btnSave").disabled = false;
			$.each($('form').serializeArray(), function(index, value) {
				if (value.name == 'lrNumber' || value.name == 'paid' || value.name == 'topay' 
					|| value.name == 'fromValue' || value.name == 'freightvalue' || value.name == 'loadingcharges'
						|| value.name == 'doorpickcharges'|| value.name == 'billValue'|| value.name == 'weight'|| value.name=='bookedOn' 
						|| value.name=='billOptions' || value.name=='item_count' || value.name=='weight' || value.name=='payOption' || value.name=='loadingchargespay' 
						|| value.name=='doorpickchargespay' || value.name=='total' || value.name=='cash' || value.name=='refund' || value.name=='invNo' || value.name=='billNumber'
						|| value.name=='bookedBy' || value.name=='fromLocation' || value.name=='toLocation'){
				} else {
					if(value.name=='fromdummy'){
						
					}
					else{
						$('[name="' + value.name + '"]').removeAttr("readonly");	
					}
					
				}
			});
		}
	}
	function clear_fetch() {
		if (confirm('Are you sure you want to clear?')) {
			$.each($('form').serializeArray(), function(index, value) {
				try {
					if (value.name == 'fromdummy' ){
						
					}else{
						if(value.name=='fromLocation' ||value.name == 'lrNumber'|| value.name=='bookedOn' ||value.name=='userid'|| value.name=='bookedby'){
							
						}
						else{
							$('[name="' + value.name + '"]').val('');
						}
					
					}
				} catch (e) {
					alert(e);
				}
			});
			$('[name="lrNumber"]').removeAttr("readonly");
		}
		document.getElementById("freightvalue").readOnly  = false;
		document.getElementById("loadingcharges").readOnly  = false;
		document.getElementById("doorpickcharges").readOnly  = false;
		document.getElementById("cash").readOnly  = false;
	}
	function enableBillValue() {
		$('[name="billValue"]').removeAttr("readonly");
	}
	
	function getBillValue() {
		var value = document.getElementById("billValue").value;
		var error = document.getElementById("error");
		if (value >= 50000) {
			var eValue = document.getElementById("billNumber").value;
			if (eValue == '' || eValue == 'undedined') {
				error.textContent = "E Way Bill Number should be mandatory."
				error.style.color = "red"
				document.getElementById("btnSave").disabled = true;
			} else {
				error.textContent = ""
				error.style.color = ""
				document.getElementById("btnSave").disabled = false;
			}
		}else{
			error.textContent = ""
			error.style.color = ""
			document.getElementById("btnSave").disabled = false;
		}
	}
	function selectBillValue(){
		var value = document.querySelector('input[name="billOptions"]:checked').value;
		if (value == 1) {
			document.getElementById("invNo").disabled = false;
			document.getElementById("billValue").disabled = false;
			document.getElementById("billNumber").disabled = false;
			document.getElementById("btnVerify").disabled = false;
			document.getElementById("btnSave").disabled = true;
		} else {
			document.getElementById("invNo").disabled = true;
			document.getElementById("billValue").disabled = true;
			document.getElementById("billNumber").disabled = true;
			document.getElementById("btnVerify").disabled = true;
			document.getElementById("btnSave").disabled = false;
		}
	}
	
	function getBookingLuggaeSlip() {
		var x = document.getElementById("lrNumber").value;
		if (x) {
			$.ajax({
				url : "/booking/print?lrNumber=" + x,
				type : "GET",
				cache : false,
				success : function(data) {
					printJS({printable: data, type: 'pdf', base64: true})
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('Could not print the LR. - '+ textStatus);
					console.log('ERROR:' + XMLHttpRequest.status
							+ ', status text: ' + XMLHttpRequest.statusText);
				}
			});
		}
	}
	function add(){
	    var new_chq_no = parseInt($('#total_chq').val())+1;
	    var new_input="<input placeholder='Select Check Point' class ='form-control' type='text' name='new_"+new_chq_no+"' id='new_"+new_chq_no+"'>";
	    $('#new_chq').append(new_input);
	    $('#total_chq').val(new_chq_no)
	  }
	  function remove(){
	    var last_chq_no = $('#total_chq').val();
	    if(last_chq_no>1){
	      $('#new_'+last_chq_no).remove();
	      $('#total_chq').val(last_chq_no-1);
	    }
	  }
	  function addCheckPointVal(){
		     var newval1 = $('#new_1').val();	     
			 var newval2 = $('#new_2').val();
			 var newval3 = $('#new_3').val();
			 var newval4 = $('#new_4').val();
			 var newval5 = $('#new_5').val();		 		 
		 $('#checkpoint').val(newval1+","+newval2+","+newval3+","+newval4+","+newval5)
	   }
	   
	   	function checkIfValidIndianMobileNumber(str) {
		var regexExp = /^[6-9]\d{9}$/gi;
		if (regexExp.test(str)) {
			return true;

		}
		return false;
	}
function lrNumberUpdate() {
	
	var fromLocation=document.getElementById("fromLocation").value;
	var toLocation=document.getElementById("toLocation").value;
	var lrNumber=document.getElementById("lrNumber").value;
	var lrNumberTemp=lrNumber;
	
	var lr=lrNumber.split("/");
	if(lr.length>2){
		document.getElementById("lrNumber").value=fromLocation+"-"+toLocation+"/"+lr[1]+"/"+lr[2];
	}else{
		document.getElementById("lrNumber").value=fromLocation+"-"+toLocation+"/"+lrNumberTemp;
		}
	
	
	
}	   	
	   	
</script>
</head>

<body onLoad="loadNonReadBookingForm()" >
	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<!-- <div style="width: 15%;">
			<h3><b>Booking</b></h3>
				<img src="../../img/product/parcel.jpg" alt="">				
			</div> -->
			<b style="width: 5%;"></b>
			<div class="blog-details-area mg-b-15">
				<div class="container-fluid" style="width: 100%;">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="blog-details-inner">
							<span id="error"></span>
								<form name="bookingForm" action="/booking/save" method="POST"
									onload="disabledFieldsOnLoad();" id="bookingForm" onsubmit="return confirm('Are you sure you want to save?');">
									<input type="hidden" class="form-control" name="bid" id="bid"
										value="${booking.id}">
									<p style="color: red" align="center">${errormsg}</p>
									<p style="color: green; font-weight: bold;" align="center">${bookingsuccessmessage}</p>
									<main>
										<div class="row">
											<div class="col-md-4 control-margin">
												<div class="well">
													<div class="row element-margin">
														<div class="col-sm-4">
															<label for="from" class="form-label">From</label>
														</div>

														<div class="col-sm-8">
															<c:choose>
																<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
																	<select class="form-select bg-info text-dark"
																		id="fromLocation" name="fromLocation" required onchange="lrNumberUpdate();">
																		<option value="">-Select From Location-</option>
																		<c:forEach var="options" items="${locationList}"
																			varStatus="status">
																			<option value="${options.id}"
																				${options.id == booking.fromLocation ? 'selected' : ''}>${options.location} [${options.id}]</option>
																		</c:forEach>
																	</select>
																</c:when>
																<c:otherwise>
																	<input type="hidden" name="fromLocation"
																		id="fromLocation"
																		value="${sessionScope.USER_LOCATIONID}" readonly>
																	<input type="text" class="form-control"
																		name="fromdummy" id="fromdummy"
																		value="${sessionScope.USER_LOCATION}" readonly>
																</c:otherwise>
															</c:choose>
														</div>
														
												<!-- 	  <div class="col-sm-6">	
													  
													  <div class="col-md-18 control-margin">
													  <button type="button" class="btn btn-primary button-margin col-md-5" onclick="add()">Add</button>
													  <button type="button" class="btn btn-primary button-margin col-md-5" onclick="remove()">remove</button>
													  </div>
													   </div>
													   <div class="col-sm-6">
													   	<input class="form-control" type="text" id="new_1" name="new_1" placeholder='Select Check Point'>
													  <div id="new_chq"></div>
													  <input type="hidden" value="1" id="total_chq">
													  <input type="hidden" value="" name="checkpoint" id="checkpoint">
													 	</div> -->
														
													</div>
													<div class="row element-margin">
														<div class="col-sm-4">
															<label for="to" class="form-label">To</label>
														</div>

														<div class="col-sm-8">
															<select class="form-select bg-info text-dark"
																id="toLocation" name="toLocation"  onchange="getCharges();lrNumberUpdate();" required>

																<option value="">-Select To Location-</option>
																<c:forEach var="options" items="${locationList}"
																	varStatus="status">
																	<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
																	<option value="${options.id}"
																		${options.id == booking.toLocation ? 'selected' : ''}>${options.location} [${options.id}]</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="to_phone">To Phone
															No</label>
													</div>
													<div class="col-sm-8">
														<input type="tel" placeholder="1234567890" pattern="[0-9]{10}" class="form-control"
															id="to_phone" name="to_phone" value="${booking.to_phone}"
															onblur="checkToNameExists();" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="toName">To Name</label>
													</div>
													<div class="col-sm-8">
														<input type="tel" class="form-control" id="toName"
															name="toName" maxlength="30" value="${booking.toName}" required>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="item_count">No Of
															Items</label>
													</div>
													<div class="col-sm-8">
														<input type="number" max="99" class="form-control"
															id="item_count" name="item_count"
															value="${booking.item_count}" oninput="getCharges();"
															required>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="weight">Weight in
															KG</label>
													</div>
													<div class="col-sm-8">
														<input type="number" placeholder="0.000" max="999999" step="0.001"  pattern="^\d+(?:\.\d{1,3})?$" class="form-control"
															id="weight" name="weight" value="${booking.weight}">
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="freightvalue">Freight</label>
													</div>
													<div class="col-sm-4">
														<select class="form-select bg-info text-dark"
															id="payOption" name="payOption"
															onchange="getCharges();selectPayOption();" required>
															<option value="">-Select-</option>
															<option value="TOPAY"
																${booking.payOption == 'TOPAY' ? 'selected' : ''}>TOPAY</option>
															<option value="PAID"
																${booking.payOption == 'PAID' ? 'selected' : ''}>PAID</option>
															<option value="FS"
																${booking.payOption == 'freeservice' ? 'selected' : ''}>FreeService</option>
														</select>
													</div>
													<div class="col-sm-4">
														<input type="number" class="form-control"
															id="freightvalue" name="freightvalue"
															value="${booking.freightvalue}" onblur="sumAmount();">
													</div>

												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="loadingcharges">Loading
															Charges</label>
													</div>
													<div class="col-sm-4">
														<select class="form-select bg-info text-dark"
															id="loadingchargespay" name="loadingchargespay" disabled>
															<option value="">-Select-</option>
															<option value="TOPAY"
																${booking.payOption == 'TOPAY' ? 'selected' : ''}>TOPAY</option>
															<option value="PAID"
																${booking.payOption == 'PAID' ? 'selected' : ''}>PAID</option>
															<option value="FS"
																${booking.payOption == 'freeservice' ? 'selected' : ''}>FreeService</option>
														</select>
													</div>
													<div class="col-sm-4">
														<input type="number" class="form-control"
															id="loadingcharges" name="loadingcharges"
															value="${booking.loadingcharges}" onblur="sumAmount();">
													</div>

												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="doorpickcharges"> Fuel Charges</label>
													</div>
													<div class="col-sm-4">
														<select class="form-select bg-info text-dark"
															id="doorpickchargespay" name="doorpickchargespay" disabled>
															<option value="">-Select-</option>
															<option value="TOPAY"
																${booking.payOption == 'TOPAY' ? 'selected' : ''}>TOPAY</option>
															<option value="PAID"
																${booking.payOption == 'PAID' ? 'selected' : ''}>PAID</option>
															<option value="FS"
																${booking.payOption == 'freeservice' ? 'selected' : ''}>FreeService</option>
														</select>
													</div>
													<div class="col-sm-4">
														<input type="number" class="form-control"
															id="doorpickcharges" name="doorpickcharges"
															onblur="sumAmount();" value="${booking.doorpickcharges}">
													</div>

												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Total</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="total"
															name="total" value="${booking.total}" readonly>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtTotal">Cash</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="cash"
															name="cash" value="${booking.cash}" onblur="sumRefund();" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="txtRefund">Refund</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="refund"
															name="refund" value="${booking.refund}" readonly>
													</div>
												</div>
											</div>
											<div class="col-md-4 control-margin">
														
												<div class="row element-margin">
													<div class="col-sm-4">
														<label for="bookedOn" class="form-label">Date</label>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control bg-info text-dark"
															id="bookedOn" placeholder="" name="bookedOn"
															value="${booking.bookedOn}" readonly required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="from_phone">From
															Phone No</label>
													</div>
													<div class="col-sm-8">
														<input type="tel"
															class="form-control" placeholder="1234567890" pattern="[0-9]{10}" id="from_phone" name="from_phone"
															value="${booking.from_phone}"
															onblur="checkFromNameExists();" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="fromName">From Name</label>
													</div>
													<div class="col-sm-8">
														<input type="text" maxlength="30" class="form-control"
															id="fromName" name="fromName" value="${booking.fromName}"
															required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="remarks">Remarks</label>
													</div>
													<div class="col-sm-8">
														<input type="text" maxlength="180" class="form-control"
															id="remarks" name="remarks" value="${booking.remarks}" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="fromValue">Value</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control"
															id="fromValue" name="fromValue"
															value="${booking.fromValue}" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-8">
														<div class="form-check form-check-inline">
															<input class="form-check-input" type="radio"
																name="billOptions" id="withBill"
																value="1" onclick="selectBillValue();" checked> <label class="form-check-label"
																for="inlineRadio1">With Bill</label>
														</div>
														<div class="form-check form-check-inline">
															<input class="form-check-input" type="radio"
																name="billOptions" id="withoutBill"
																value="2" onclick="selectBillValue();"> <label class="form-check-label"
																for="inlineRadio2">Without Bill</label>
														</div>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="invNo">Cons INV No</label>
													</div>
													<div class="col-sm-8">
														<input type="text" maxlength="32" class="form-control"
															id="invNo" name="invNo" value="${booking.invNo}" required>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="billValue">Bill
															Value</label>
													</div>
													<div class="col-sm-8">
														<input type="number"  class="form-control"
															id="billValue" name="billValue"
															value="${booking.billValue}" onkeyup="getBillValue();" required>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="billNumber">E Way
															Bill Number</label>
													</div>
													<div class="col-sm-8">
															<div class="input-group">
															<input type="text" maxlength="12" minlength="12" class="form-control"
															id="billNumber" name="billNumber"
															value="${booking.billNumber}" onkeyup="getBillValue();">
														</div>
														<button type="button" class="btn btn-secondary btn-sm"
																id="btnVerify">Verify</button>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<input type="checkbox" class="form-check-input"
															id="isPrinted" name="isPrinted"
															value="${booking.isPrinted}" disabled> <label
															class="form-check-label" for="isPrinted">Printed</label>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="userid">User ID</label>
													</div>
													<div class="col-sm-8">
														<input type="text" maxlength="180"
															class="form-control bg-info text-dark" id="user_id"
															name="userid" value="${sessionScope.USER_ID}" readonly
															required>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="bookedBy">Booked By</label>
													</div>
													<div class="col-sm-8">
														<input type="text" maxlength="180"
															class="form-control bg-info text-dark" id="bookedby"
															name="bookedby" value="${sessionScope.USER_NAME}" readonly
															required>
															
															<input type="hidden" maxlength="180"
															class="form-control bg-info text-dark" id="bookedby"
															name="bookedBy" value="${sessionScope.USER_LOGIN_ID}" readonly
															required>
													</div>
												</div>

									<%-- <div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="bookedBy">Booked By</label>
													</div>
													<div class="col-sm-8">

														<select name="bookedBy" id="bookedBy"
															class="form-select bg-info text-dark">
															<option value="">-Booked By-</option>
															<c:forEach var="options" items="${bookedNameList}"
																varStatus="status">
																<option value="${options.id}"
																	${options.id==booking.bookedBy ? 'selected' : ''}>${options.name} [${options.id}]</option>
															</c:forEach>
														</select>
													</div>
												</div> --%>

											</div>
											<div class="col-md-4 control-margin">
												<div class="well">
													<div class="row element-margin">
														<div class="input-group">
															<input type="text" class="form-control bg-info text-dark"
																placeholder="LR No" id="lrNumber" name="lrNumber"
																value="${LRnumber}">
															<button type="button" class="btn btn-secondary"
																id="btnSearch" onclick="getLRNumberSearch();">Search</button>
														</div>
														<div class="mt-0">
															<label for="txtSearch" class="form-label"><small>(Type
																	LR No and press Search)</small></label>
														</div>
													</div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="col-md-18 control-margin">
												<button onclick="addCheckPointVal()"type="submit"
													class="btn btn-primary button-margin col-md-2" id="btnSave" disabled>Save</button>
												<button type="button"
													class="btn btn-primary button-margin col-md-2" id="btnEdit"
													onclick="loadReadBookingForm();">Edit</button>
														<button type="button" onclick="getBookingLuggaeSlip()"
													class="btn btn-primary button-margin col-md-2"
													id="btnPrint">Print</button>
													<a href="/menu"><button type="button"
														class="btn btn-primary button-margin col-md-2" id="bc">Back</button></a>
											

											</div>
												
													<div class="col-md-18 control-margin">
													
													<div class="row element-margin">
														<div class="col-sm-4">
															<label class="form-label"></label>
														</div>
														<div class="col-sm-8"></div>
													</div>

													<!-- <a href="/booking"><button type="button"
															class="btn btn-primary button-margin col-md-2" id="new" >New</button></a> -->
													<button type="button" onclick="location.href='/booking'"
															class="btn btn-primary button-margin col-md-2" id="new" >New</button>
													<button type="button"
														class="btn btn-primary button-margin col-md-2" id="bclear"
														onclick="clear_fetch();">Clear</button>

												<a
													onclick="return confirm('Are you sure you want to delete?')"
													href="/bookingReq/delete?bid=${booking.id}"><button
														type="button"
														class="btn btn-primary button-margin col-md-2" id="delete">Delete</button></a>
												<c:choose>
													<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
														<button type="button"
															class="btn btn-primary button-margin col-md-2"
															id="btnBill" onclick="enableBillValue();">Bill</button>
													</c:when>
												</c:choose>
											
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>

												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label"></label>
													</div>
													<div class="col-sm-8"></div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="paid">Paid</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="paid"
															name="paid" value="${booking.paid}" readonly>
													</div>
												</div>
												<div class="row element-margin">
													<div class="col-sm-4">
														<label class="form-label" for="topay">To Pay</label>
													</div>
													<div class="col-sm-8">
														<input type="number" class="form-control" id="topay"
															name="topay" value="${booking.topay}" readonly>
													</div>
												</div>
												

											</div>
										</div>
										<div class="row control-margin">
											
										</div>
									</main>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
function printStatus(status) {
		if(status)
		{
		document.getElementById("isPrinted").checked = true;
		}
}


printStatus(${printStatus});

//Total Round off


</script>

</html>
