<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Incoming Parcel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="header.jsp"%>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script> -->
<script type="text/javascript" charset="utf-8">
	function getSearchParcel() {
		let from = $("#fromLocation").val();
		if (!from) {
			alert("Please select From Location");
			return false;
		}
		let to = $("#toLocation").val();
		if (!to) {
			alert("Please select To Location");
			return false;
		}
		let bookedOn = $("#bookedOn").val();
		window.location.href = "/get/incomingParcel?fromLocation=" + from
				+ "&toLocation=" + to + "&bookedOn=" + bookedOn ;
	}
	/* function today() {
		document.getElementById("bookedOn").valueAsDate = new Date();
	}
	window.onload = function() {
		today();
	}; */
	
	$("document").ready(function(){
		$('#btnSave').click(function(){
		    var files = new Array();
		    $('#data-table tbody tr  input:checkbox').each(function() {
		      if ($(this).is(':checked')) {
		      files.push(this.value);
		      }
		    });
		    document.getElementById("lrnoarray").value = files;
		    
		 });

		})
		
		
	function getIncomeParcel(value){
		//alert("val"+value.value);
		let from = $("#fromLocation").val();
		let to = $("#toLocation").val();
		let bookedOn = $("#bookedOn").val();
		window.location.href = "/load/incomingParcel?fromLocation=" + from
		+ "&toLocation=" + to + "&bookedOn=" + bookedOn + "&ogpl="+value.value;
	}

</script>
</head>
<body>

	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 15%;">
			<h3><b>Incoming Parcel</b></h3>
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<b style="width: 5%;"></b>
			<form action="/incoming/save" id="incomingform" method="POST"  style="width: 100%;">
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="form-row">
					<div class="form-holder">
								<select name="fromLocation" id="fromLocation" class="form-control">
									<option value="">-Select From Location-</option>
									<c:forEach var="options" items="${locationList}"
										varStatus="status">
										<option value="${options.id}"
											${options.id == toLocation ? 'selected="selected"':''}>${options.location} [${options.id}]</option>
									</c:forEach>
								</select>
								<i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;
					<div class="form-holder">
					<c:choose>
							<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
						<select name="toLocation" id="toLocation" class="form-control">
							<option value="">-Select To Location-</option>
							<c:forEach var="options" items="${locationList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id== fromLocation ? 'selected="selected"':''}>${options.location} [${options.id}]</option>
							</c:forEach>
						</select>
						<i class="zmdi zmdi-chevron-down"></i>
						</c:when>
							<c:otherwise>
								<input type="hidden" class="form-control" name="toLocation"
									id="toLocation" value="${sessionScope.USER_LOCATIONID}"
									>
									<input type="text" class="form-control" name="todummy"
									id="todummy" value="${sessionScope.USER_LOCATION}"
									readonly>
							</c:otherwise>
						</c:choose>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="date" class="form-control" id="bookedOn"
						name="bookedOn" placeholder="Date" value="${bookedOn}">
						&nbsp;&nbsp; 
					<a class="btn btn-primary button-margin" id="import"
						onclick="return getSearchParcel();">Import</a>
				</div>
				<div class="form-row">
				<div class="form-holder">
						<select name="ogplNo" id="ogplNo" onchange="getIncomeParcel(this);" class="form-control">
							<option value="">-Select OGPL-</option>
							<c:forEach var="options" items="${ogplList}"
								varStatus="status">
								<option value="${options.ogplNo}"
									${options.ogplNo==ogplno ? 'selected="selected"':''}>${options.ogplNo}</option>
							</c:forEach>
						</select>
						
						<!-- <input type="date"
						class="form-control" id="ogpldate" placeholder="OGPL Date"> -->
											</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- <div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="defaultCheck1"> <label class="form-check-label"
							for="defaultCheck1"> Manual </label>
					</div> -->
				</div>
			
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Nos LR</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">Charges</th>
							<th scope="col">From Name</th>
							<th scope="col">To Name</th>
							<th scope="col">From Phone</th>
							<th scope="col">To Phone</th>
							<th scope="col">E-Way Bill Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="incomelist"  items="${incomeparcelList}"
							varStatus="status">
							<tr>
								<td>${incomelist.toName}</td>

								<c:choose>
									<c:when test="${checkboxchecked=='1'}">
										<td>${incomelist.lrNumber}    <input type="checkbox"
											name="ogp" id="ogp" value="${incomelist.lrNumber}" checked>&nbsp;
										</td>
									</c:when>
									<c:otherwise>
										<td>${incomelist.lrNumber}   <input type="checkbox"
											name="ogp" id="ogp" value="${incomelist.lrNumber}">&nbsp;
										</td>
									</c:otherwise>
								</c:choose>
								<td>${incomelist.paid}</td>
								<td>${incomelist.topay}</td>
								<td>${incomelist.total_charges}</td>
								<td>${incomelist.fromName}</td>
								<td>${incomelist.toName}</td>
								<td>${incomelist.from_phone}</td>
								<td>${incomelist.to_phone}</td>
								<td>${incomelist.billNumber}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<br>
				<div class="form-row">
					<div class="form-holder">
						<select name="vehicleNo" id="vehicleNo" class="form-control" disabled>
							<option value="">-Vehicle No-</option>
							<c:forEach var="options1" items="${vehicleList}"
								varStatus="status">
								<option value="${options1.id}"
				${options1.id==incomeparcel.vehicleNo ? 'selected' : ''}>${options1.vehicle}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="deliveredBy" id="deliveredBy" class="form-control"disabled>
							<option value="" disabled selected>-Delivered By-</option>
							<option value="class 01"
				${incomeparcel.deliveredBy == 'class 01' ? 'selected' : ''}>Class 01</option>
				<option value="class 02"
				${incomeparcel.deliveredBy == 'class 02' ? 'selected' : ''}>Class 02</option>
				<option value="class 03"
				${incomeparcel.deliveredBy == 'class 03' ? 'selected' : ''}>Class 03</option>
						</select> <i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- <div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="defaultCheck1"> <label class="form-check-label"
							for="defaultCheck1"> Verified </label>
					</div> -->
				</div>
				<div class="form-row">
					
					<div class="form-holder">
						<select name="driver" id="driver" class="form-control"disabled>
							<option value="">-Driver-</option>
							<c:forEach var="options" items="${driverList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==incomeparcel.driver ? 'selected' : ''}>${options.name} [${options.id}]</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>	
					&nbsp;
					<div class="form-holder">
						<select name="conductor" id="conductor" class="form-control"disabled>
							<option value="">-Conductor-</option>
							<c:forEach var="options" items="${conductorList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==incomeparcel.conductor ? 'selected' : ''}>${options.name} [${options.id}]</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;						
					<input type="text" name="preparedBy" id="preparedBy" class="form-control" placeholder="Prepared By" value="${incomeparcel.preparedBy}">
				</div>
				<textarea name="details" id="details" placeholder="Details" class="form-control"
					style="height: 130px;" >${incomeparcel.details}</textarea>
					<input type="hidden" id="lrnoarray" name="lrnoarray"
					class="form-control">  
					<input type="hidden" id="status" name="status"
					class="form-control">  <br>
				<div class="row control-margin">
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary button-margin"
							id="btnSave">Save</button>
					</div>
					<div class="col-md-4">		
						<button type="reset" class="btn btn-primary button-margin"
							name="btnClear">Clear</button>
					</div>	
					<div class="col-md-4">		
						<a href="/menu"><button type="button" class="btn btn-primary button-margin" id="btnClear">Back</button></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>