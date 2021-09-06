<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Outgoing Parcel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="header.jsp"%>
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
		let bookedOn = $("#bookedOn").val();
		window.location.href = "/get/outgoingParcel?fromLocation=" + from
				+ "&toLocation=" + to + "&bookedOn=" + bookedOn;
	}
	function today() {
		document.getElementById("bookedOn").valueAsDate = new Date();
	}
	window.onload = function() {
		today();
	};

	var lrNumbers = new Array();
	function checkBoxFn() {
		lrNumbers = [ document.getElementById("ogpnoarray").value ];
		var lrNos = document.getElementById("ogp");
		if (lrNos.checked == true) {
			lrNumbers.push(lrNos.value);
		} else {
			lrNumbers.splice(lrNumbers.indexOf(lrNos.value), 1)
		}
		document.getElementById("ogpnoarray").innerHTML = lrNumbers;
	}
</script>
</head>

<body>

	<div class="wrapper">
		<div class="inner" style="width: 90%">
			<div style="width: 250px;">
				<img src="../../img/product/parcel.jpg" alt="">				
			</div>
			<h3><b>Outgoing Parcel</b></h3>
			<form action="/ogpl/save" id="ogplform" method="POST">
				<p style="color: green" align="center">${outgoingsuccessmessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
			
				<div class="form-row">
					<div class="form-holder">
						<input type="text" class="form-control" name="fromLocation"
														id="fromLocation" value="${sessionScope.USER_LOCATION}"
														readonly>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="toLocation" id="toLocation" class="form-control">
							<option value="">-Select To Location-</option>
							<c:forEach var="options" items="${locationList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==selectto ? 'selected="selected"':''}>${options.location}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>

					</div>
					&nbsp;&nbsp; <a class="btn btn-primary button-margin" id="import"
						onclick="return getSearchParcel();">Import</a>
				</div>
				<div class="form-row">
					<input type="text" class="form-control" placeholder="OGPL NO">
					<input type="date" id="bookedOn" name="bookedOn"
						class="form-control" placeholder="Date">
				</div>
				<div class="form-row">
					<div class="form-holder">
						<select name="deliveredBy" id="deliveredBy" class="form-control">
							<option value="" disabled selected>Delivered By</option>
							<option value="class 01">Class 01</option>
							<option value="class 02">Class 02</option>
							<option value="class 03">Class 03</option>
						</select> <i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="from" id="vehicleNo" name="vehicleNo"
							class="form-control">
							<option value="">-Vehicle No-</option>
							<c:forEach var="options1" items="${vehicleList}"
								varStatus="status">
								<option value="${options1.id}">${options1.vehicle}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
				</div>
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th scope="col">LR No.</th>
							<th scope="col">OGP</th>
							<th scope="col">To Name</th>
							<th scope="col">NOs</th>
							<th scope="col">Remarks</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">Charges</th>
							<th scope="col">Booked Date</th>
							<th scope="col">Br</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="outgoingList" items="${outgoingList}"
							varStatus="status">
							<tr>
								<!-- 							<th scope="row">1</th>
 -->
								<td>${outgoingList.lrNumber}</td>
								<td><input type="checkbox" name="ogp" id="ogp"
									onchange="checkBoxFn()" value="${outgoingList.lrNumber}" />&nbsp;</td>
								<td>${outgoingList.toName}</td>
								<td>${outgoingList.bookingNo}</td>
								<td>${outgoingList.remarks}</td>
								<td>${outgoingList.paid}</td>
								<td>${outgoingList.topay}</td>
								<td>${outgoingList.total_charges}</td>
								<td>${outgoingList.bookedOn}</td>
								<td>dummy br</td>

							</tr>
						</c:forEach>
				</table>
				<br>
				<div class="form-row">
					<input type="text" id="driver" name="driver" class="form-control"
						placeholder="Driver">
						<input type="text" id="conductor" name="conductor"
						class="form-control" placeholder="Conductor">
				</div>
				<div class="form-row">
					<input type="text" id="preparedBy" name="preparedBy"
						class="form-control" placeholder="Prepared By">
				</div>
				<textarea id="details" name="details" placeholder="Details"
					class="form-control" style="height: 130px;"></textarea>
				<input type="hidden" id="ogpnoarray" name="ogpnoarray"
					class="form-control"> <br>


				<div class="row control-margin">
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary button-margin"
							id="btnClear">Save</button>
					</div>
					<div class="col-md-4">		
						<button type="reset" class="btn btn-primary button-margin"
							name="submit">Clear</button>
					</div>		
					<div class="col-md-4">
						<a href="/menu"><button type="button"
								class="btn btn-primary button-margin" id="btnClear">Back</button></a>
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