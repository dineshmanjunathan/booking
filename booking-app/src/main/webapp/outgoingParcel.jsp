<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Outgoing Parcel</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- MATERIAL DESIGN ICONIC FONT -->
<link rel="stylesheet"
	href="../../fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

<!-- STYLE CSS -->
<link rel="stylesheet" href="../../css/incoming/style.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.1/jquery.min.js"></script>

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
		window.location.href = "/get/outgoingParcel?fromLocation=" + from
				+ "&toLocation=" + to;
	}
</script>
</head>

<body>

	<div class="wrapper">
		<div class="inner">
			<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<form action="">
				<h3>Outgoing Parcel</h3>
				<div class="form-row">
					<div class="form-holder">
						<select name="fromLocation" id="fromLocation" class="form-control">
						<option value="">-Select From Location-</option>
						<c:forEach var="options" items="${locationList}"
							varStatus="status">
							<option value="${options.id}">${options.location}</option>
						</c:forEach>
					</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="toLocation" id="toLocation" class="form-control">
						<option value="">-Select To Location-</option>
						<c:forEach var="options" items="${locationList}"
							varStatus="status">
							<option value="${options.id}">${options.location}</option>
						</c:forEach>
					</select><i class="zmdi zmdi-chevron-down"></i>
				
					</div>
						&nbsp;&nbsp;
					<a class="btn btn-primary button-margin"
						id="import" onclick="return getSearchParcel();">Import</a>
				</div>
							<div class="form-row">
					<input type="text" class="form-control" placeholder="OGPL NO">
					<input type="date" class="form-control" placeholder="Date">
				</div>
				<div class="form-row">
					<div class="form-holder">
						<select name="" id="" class="form-control">
							<option value="" disabled selected>Delivered By</option>
							<option value="class 01">Class 01</option>
							<option value="class 02">Class 02</option>
							<option value="class 03">Class 03</option>
						</select> <i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="from" id="from" class="form-control">
						<option value="">-Vehicle No-</option>
						<c:forEach var="options1" items="${vehicleList}"
							varStatus="status">
							<option value="${options1.id}">${options1.vehicle}</option>
						</c:forEach>
					</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
				</div>
				<table class="table">
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
 							<td>dummy ogpl</td>
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
					<input type="text" class="form-control" placeholder="Driver">
				</div>
				<br>
				<div class="form-row">
					<input type="text" class="form-control" placeholder="Conductor">
				</div>
				<br>
				<div class="form-row">
					<input type="text" class="form-control" placeholder="Prepared By">
				</div>
				<br>
				<textarea name="" id="" placeholder="Details" class="form-control"
					style="height: 130px;"></textarea>
					<br>
				<div class="row control-margin">
			<div class="col-md-12">
			<button type="button" class="btn btn-primary button-margin" id="btnClear">Save </button>
				<button type="submit" class="btn btn-primary button-margin" name="submit">Clear</button>
				
				<button type="button" class="btn btn-primary button-margin" id="btnPrint">Help</button>
				<a class="btn btn-primary button-margin" href="/menu">Back</a>
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