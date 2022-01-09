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
				<c:when test="${not empty bookedby}">
					<c:url var="action" value="/editBookedby" />
				</c:when>
				<c:otherwise>
					<c:url var="action" value="/addBookedBy" />
				</c:otherwise>
			</c:choose>
			<form action="${action}" method="post">
				<h3>
					<b>Manage Booked Name</b>
				</h3>
				<p style="color: red" align="center">${errormsg}</p>
				<c:choose>
				<c:when test="${not empty bookedby}">
				<div class="form-row">
					<input type="text" class="form-control" name="id" id="id"
						placeholder="Code" value="${bookedby.id}" readonly>
				</div>
				</c:when>
				<c:otherwise>
				<div class="form-row">
					<input type="text" class="form-control" name="id" id="id"
						placeholder="Code" value="${bookedby.id}" required>
				</div>
				</c:otherwise>
				</c:choose>
				<div class="form-row">
					<input type="text" class="form-control" name="name"
						id="name" placeholder="Name" value="${bookedby.name}"
						required>
				</div>
				<br>
				<div class="row control-margin">
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary button-margin"
							id="btnClear">Save</button>
					</div>
					<div class="col-md-4">
						<a href="/bookedByListing"><button type="button"
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