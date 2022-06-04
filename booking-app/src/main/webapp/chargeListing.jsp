<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>

</head>

<body>

	<div class="wrapper">
		<div class="inner">
			<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<form action="">
				<h3>
					<b>Manage Charges</b>
				</h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/addchargepage">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>
							<th scope="col">From Location</th>
							<th scope="col">To Location</th>
							<th scope="col">FREIGHT CHARGES</th>
							<th scope="col">LOADING CHARGES</th>
							<th scope="col">FUEL CHARGES</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${chargeListing}"
							varStatus="status">
							<tr>

								<td>${details.fromLocation}</td>
								<td>${details.toLocation}</td>
								<td>${details.freight}</td>
								<td>${details.loading}</td>
								<td>${details.fuel}</td>
								
								<td><a class="btn btn-primary button-margin"
									href="/chargeedit/${details.fromLocation}/${details.toLocation}">Edit</a> 
									<a class="btn btn-primary button-margin"
									href="/chargedelete/${details.fromLocation}/${details.toLocation}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
			</form>
		</div>
	</div>

	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>