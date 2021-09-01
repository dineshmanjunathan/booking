<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
			<form action="" >
				<h3>Pay Type</h3>
				<p style="color: green" align="center">${successMessage}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="payOption.jsp">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Pay Type</th>
							<th scope="col">Description</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${payOptionListing}"
							varStatus="status">
							<tr>
								<td>${details.payOption}</td>
								<td>${details.details}</td>
								<td><a class="btn btn-primary button-margin" href="/payOption/edit/${details.id}" id="${details.id}">edit</a> <a class="btn btn-primary button-margin" href="/payOption/delete/${details.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
			</form>
		</div>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>