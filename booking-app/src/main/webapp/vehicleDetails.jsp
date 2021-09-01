<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookingApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- MATERIAL DESIGN ICONIC FONT -->
<link rel="stylesheet"
	href="../../fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

<!-- STYLE CSS -->
<link rel="stylesheet" href="../../css/incoming/style.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<%@ include file="header.jsp"%>
	<meta charset="ISO-8859-1">
</head>

<body>
	<div class="wrapper">
		<div class="inner">
			<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			
			<form action="/vehicle" method="post">
			
				<h3> Vehicle Details</h3>
				
				<input type="hidden" class="form-control" name="id" id="id" value="${vehicle.id}" required>
					<div class="form-row">
						<input type="text" class="form-control" name="vehicle" id="vehicle" placeholder="Vehicle No" value="${vehicle.vehicle}" required>
					</div>
					<br>
					<textarea name="details" id="details" placeholder="Vehicle Description" class="form-control"
						style="height: 130px;" >${vehicle.details}</textarea>
					<br>
					<div class="row control-margin">
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary button-margin" id="btnClear">Save</button>
						</div>
						<div class="col-md-4">
							<button type="reset" class="btn btn-primary button-margin" id="btnClear">Clear</button>
						</div>
						<div class="col-md-4">
							<a href="/vehicleListing"><button type="button" class="btn btn-primary button-margin" id="btnClear">Back</button></a>
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