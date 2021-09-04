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
				+ "&toLocation=" + to + "&bookedOn=" + bookedOn;
	}
	function today() {
		document.getElementById("bookedOn").valueAsDate = new Date();
	}
	window.onload = function() {
		today();
	};
</script>
</head>
<body>

	<div class="wrapper">
		<div class="inner">
			<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<form action="">
				<h3>Incoming Parcel</h3>
				<div class="form-row">
					<div class="form-holder">
						<select name="fromLocation" id="fromLocation" class="form-control">
							<option value="">-Select From Location-</option>
							<c:forEach var="options" items="${locationList}"
								varStatus="status">
								<option value="${options.id}"
									${options.id==selectfrom ? 'selected="selected"':''}>${options.location}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>

					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;
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
					<input type="text" class="form-control" id="no" placeholder="No">
					<input type="date" class="form-control" id="bookedOn"
						name="bookedOn" placeholder="Date">
				</div>
				<div class="form-row">
					<input type="text" class="form-control" id="ogplno"
						placeholder="OGPL No"> <input type="date"
						class="form-control" id="ogpldate" placeholder="OGPL Date">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="defaultCheck1"> <label class="form-check-label"
							for="defaultCheck1"> Manual </label>
					</div>
				</div>
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th scope="col">No.</th>
							<th scope="col">Name</th>
							<th scope="col">Nos LR</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">Charges</th>
							<th scope="col">Hamali Rem</th>
							<th scope="col">Free Hold Status To</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="incomelist" items="${incomeList}"
							varStatus="status">
							<tr>
								<th scope="row">1</th>
								<td>${incomelist.toName}</td>
								<td>${incomelist.lrNumber}</td>
								<td>${incomelist.paid}</td>
								<td>${incomelist.topay}</td>
								<td>${incomelist.total_charges}</td>
								<td>Hamali Rem</td>
								<td>Free Hold Status To</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<br>
				<div class="form-row">
					<div class="form-holder">
						<select name="from" id="from" class="form-control">
							<option value="">-Vehicle No-</option>
							<c:forEach var="options1" items="${vehicleList}"
								varStatus="status">
								<option value="${options1.id}">${options1.vehicle}</option>
							</c:forEach>
						</select><i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-holder">
						<select name="" id="" class="form-control">
							<option value="" disabled selected>Delivered By</option>
							<option value="class 01">Class 01</option>
							<option value="class 02">Class 02</option>
							<option value="class 03">Class 03</option>
						</select> <i class="zmdi zmdi-chevron-down"></i>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value=""
							id="defaultCheck1"> <label class="form-check-label"
							for="defaultCheck1"> Verified </label>
					</div>
				</div>
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
						<button type="button" class="btn btn-primary button-margin"
							id="btnClear">Save</button>
						<button type="reset" class="btn btn-primary button-margin"
							name="submit">Clear</button>

						<button type="button" class="btn btn-primary button-margin"
							id="btnPrint">Help</button>
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