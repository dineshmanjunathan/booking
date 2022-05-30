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

			<c:choose>
				<c:when test="${not empty location}">
					<c:url var="action" value="/editLocation" />
				</c:when>
				<c:otherwise>
					<c:url var="action" value="/addLocation" />
				</c:otherwise>
			</c:choose>
			<form action="${action}" method="post">
				<h3>
					<b>Location</b>
				</h3>
				<p style="color: red" align="center">${errormsg}</p>
				
				<c:choose>
				<c:when test="${not empty location}">
				<div class="form-row">
					<input type="text" class="form-control" name="id" id="id"
						placeholder="Code" value="${location.id}" readonly>
				</div>
				</c:when>
				<c:otherwise>
				<div class="form-row">
					<input type="text" class="form-control" name="id" id="id"
						placeholder="Code" value="${location.id}" required>
				</div>
				</c:otherwise>
				</c:choose>
				
				<div class="form-row">
					<input type="text" class="form-control" name="location"
						id="location" placeholder="Location" value="${location.location}"
						required>
				</div>
				
				<div class="form-row">
					<input type="text" class="form-control" name="uploadingCharge"
						id="uploadingCharge" placeholder="Un Loading Charges " value="${location.uploadingCharge}"
						required>
				</div>
				<textarea name="address" id="address" placeholder="Address"
					class="form-control" style="height: 130px;">${location.address}</textarea>
				<br>
				<div class="row control-margin">
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary button-margin"
							id="btnClear">Save</button>
					</div>
					<div class="col-md-4">
						<button type="reset" class="btn btn-primary button-margin"
							id="btnClear">Clear</button>
					</div>
					<div class="col-md-4">
						<a href="/locationListing"><button type="button"
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