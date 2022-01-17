<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
				<c:when test="${not empty charge}">
					<c:url var="action" value="/editCharge" />
				</c:when>
				<c:otherwise>
					<c:url var="action" value="/addCharge" />
				</c:otherwise>
			</c:choose>
			<form action="${action}" method="post">
				<h3>
					<b>Manage Charges</b>
				</h3>
				<p style="color: red" align="center">${errormsg}</p>
				<input type="hidden" class="form-control" name="id"	id="id" placeholder="id" value="${charge.id}">
			<div class="row element-margin">
				<div class="col-sm-5">
					<select class="form-select bg-info text-dark" id="fromLocation"
						name="fromLocation" required>

						<option value="">-Select From Location-</option>
						<c:forEach var="options" items="${locationList}"
							varStatus="status">
							<option value="${options.id}"
								${options.id == charge.fromLocation ? 'selected' : ''}>${options.location}</option>
						</c:forEach>
					</select>
					</div>
					<div class="col-sm-1"><h4><b>←→</b></h4></div>
					<div class="col-sm-5">
					<select class="form-select bg-info text-dark" id="toLocation"
						name="toLocation" required>

						<option value="">-Select To Location-</option>
						<c:forEach var="options" items="${locationList}"
							varStatus="status">
							<%-- <option value="${options.id}" ${options.id == booking.toLocation ? 'selected' : ''}>${options.location}</option> --%>
							<option value="${options.id}"
								${options.id == charge.toLocation ? 'selected' : ''}>${options.location}</option>
						</c:forEach>
					</select>
				</div>
				</div>

				<select name="chargetype" id="chargetype" class="form-control" required>
																		<option value="">-Select Charge Type-</option>
																		<option value="FREIGHT"
																			${charge.chargetype == 'FREIGHT' ? 'selected' : ''}>Freight</option>
																		<option value="LOADING CHARGES"
																			${charge.chargetype == 'LOADING CHARGES' ? 'selected' : ''}>Loading Charges</option>
																		<option value="FUEL CHARGES"
																			${charge.chargetype == 'FUEL CHARGES' ? 'selected' : ''}>Fuel Charges</option>
				</select>
				<br>
				<div class="form-row">
					<input type="number" class="form-control" name="value"
						id="value" placeholder="Value" step="0.001"  pattern="^\d+(?:\.\d{1,3})?$" value="${charge.value}"
						required>
				</div>
				<br>
				<div class="row control-margin">
					<div class="col-md-4">
						<button type="submit" class="btn btn-primary button-margin"
							id="btnClear">Save</button>
					</div>
					<div class="col-md-4">
						<a href="/chargeListing"><button type="button"
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