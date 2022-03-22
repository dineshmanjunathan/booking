<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8">
<title>Outgoing Parcel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="header.jsp"%>
<meta charset="ISO-8859-1">
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script> -->
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
		//let bookedOn = $("#bookedOn").val();
		window.location.href = "/get/outgoingParcel?fromLocation=" + from
				+ "&toLocation=" + to;
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
		    //console.log(files);
		    document.getElementById("ogpnoarray").value = files;
		    if(files.length === 0){
		    	alert("Please select any Parcel to Out!");
				return false;	
		    }
		 });

		})
		
</script>
</head>

<body>

	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 15%;">
			<h3><b>Outgoing Parcel</b></h3>
				<img src="../../img/product/parcel.jpg" alt="">				
			</div>
			<b style="width: 5%;"></b>
			<form action="/ogpl/save" id="ogplform" method="POST" style="width: 100%;">
				<p style="color: green" align="center">${outgoingsuccessmessage}</p>
				<p style="color: green;font-weight: bold; " align="center">${ogplno}</p>
				<p style="color: red" align="center">${errormsg}</p>
			
				<div class="form-row">
					<div class="form-holder">
						<c:choose>
							<c:when test="${sessionScope.ROLE eq 'ADMIN'}">
								<select name="fromLocation" id="fromLocation" class="form-control">
									<option value="">-Select From Location-</option>
									<c:forEach var="options" items="${locationList}"
										varStatus="status">
										<option value="${options.id}"
											${options.id==outgoingparcel.fromLocation ? 'selected="selected"':''}>${options.location}</option>
									</c:forEach>
								</select>
								<i class="zmdi zmdi-chevron-down"></i>
							</c:when>
							<c:otherwise>
							<input type="hidden" name="fromLocation"
									id="fromLocation" value="${sessionScope.USER_LOCATIONID}"
									readonly>
								<input type="text" class="form-control" name="fromdummy"
									id="fromdummy" value="${sessionScope.USER_LOCATION}"
									readonly>
							</c:otherwise>
						</c:choose>

					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="toLocation" id="toLocation" class="form-control">
							<option value="">-Select To Location-</option>
							<c:forEach var="options" items="${locationList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==outgoingparcel.toLocation ? 'selected="selected"':''}>${options.location}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>

					</div>&nbsp;&nbsp;
					<%-- <input type="date" id="bookedOn" name="bookedOn"
						class="form-control" placeholder="Date" value="${bookedOn}">
					&nbsp;&nbsp; --%> <a class="btn btn-primary button-margin" id="import"
						onclick="return getSearchParcel();">Import</a>
				</div>
				<div class="form-row">
					<input type="text" class="form-control" id="ogplno" value="${outgoingparcel.ogplNo}" placeholder="OGPL NO">
					<div class="form-holder">
						<select name="deliveredBy" id="deliveredBy" class="form-control">
							<option value="" disabled selected>Delivered By</option>
				
								<option value="class 01"
				${outgoingparcel.deliveredBy == 'class 01' ? 'selected' : ''}>Class 01</option>
				<option value="class 02"
				${outgoingparcel.deliveredBy == 'class 02' ? 'selected' : ''}>Class 02</option>
				<option value="class 03"
				${outgoingparcel.deliveredBy == 'class 03' ? 'selected' : ''}>Class 03</option>
						</select> <i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select id="vehicleNo" name="vehicleNo"
							class="form-control">
							<option value="">-Vehicle No-</option>
							<c:forEach var="options1" items="${vehicleList}"
								varStatus="status">
								<option value="${options1.id}"
				${options1.id==outgoingparcel.vehicleNo ? 'selected' : ''}>${options1.vehicle}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
				</div>
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th scope="col">LR No.</th>
							<th scope="col">OGPL</th>
							<th scope="col">To Name</th>
							<th scope="col">No Of Items</th>
							<th scope="col">Remarks</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">Booked Date</th>
							<th scope="col">To Phone</th>
							<th scope="col"> EWAY Bill number</th>
							<th scope="col">Connection Point</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="outgoingList" items="${outgoingList}"
							varStatus="status">
							<tr>
								<td>${outgoingList.lrNumber}</td>
								<c:choose>
									<c:when test="${checkboxchecked=='1'}">
									<td><input type="checkbox" name="ogp" id="ogp"
										value="${outgoingList.lrNumber}" checked>&nbsp;</td>
									</c:when>
									<c:otherwise>
									<td><input type="checkbox" name="ogp" id="ogp"
										value="${outgoingList.lrNumber}">&nbsp;</td>
									</c:otherwise>
								</c:choose>
								<td>${outgoingList.toName}</td>
								<td>${outgoingList.item_count}</td>
								<td>${outgoingList.remarks}</td>
								<td>${outgoingList.paid}</td>
								<td>${outgoingList.topay}</td>
								<td>${outgoingList.bookedOn}</td>
								<td>${outgoingList.to_phone}</td>
								<td>${outgoingList.billNumber}</td>
								<td>${outgoingList.connectionPoint}</td>

							</tr>
						</c:forEach>
				</table>
				<br>
				<div class="form-row">	
					<div class="form-holder">
						<select name="driver" id="driver" class="form-control">
							<option value="">-Driver-</option>
							<c:forEach var="options" items="${driverList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==outgoingparcel.driver ? 'selected' : ''}>${options.name} [${options.id}]</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>					
					&nbsp;&nbsp;
					<div class="form-holder">
						<select name="conductor" id="conductor" class="form-control">
							<option value="">-Conductor-</option>
							<c:forEach var="options" items="${conductorList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==outgoingparcel.conductor ? 'selected' : ''}>${options.name} [${options.id}]</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;
										   
						<input type="text" id="preparedBy" name="preparedBy"
						class="form-control" placeholder="Prepared By" value="${outgoingparcel.preparedBy}">
				</div>
				<textarea id="details" name="details" placeholder="Details" 
					class="form-control" style="height: 130px;">${outgoingparcel.details}</textarea>
				<input type="hidden" id="ogpnoarray" name="ogpnoarray"
					class="form-control"> <br>


				<div class="row control-margin">
					<div class="col-md-6 control-margin">
						<button type="submit" class="btn btn-primary button-margin col-md-2"
							id="btnSave">Save</button>
						<button type="reset" class="btn btn-primary button-margin col-md-2"
							name="submit">Clear</button>
						<a href="/menu"><button type="button"
								class="btn btn-primary button-margin col-md-2" id="btnClear">Back</button></a>
						<button type="button" class="btn btn-primary button-margin col-md-2"
							name="print">Print</button>
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