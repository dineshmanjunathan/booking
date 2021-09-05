<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<form action="" >
				<h3><b>Vehicle Details</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: green" align="center">${deletesuccessmessage}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="vehicleDetails.jsp">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							<th scope="col">Vehicle No</th>
							<th scope="col">Description</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${vehicleListing}"
							varStatus="status">
							<tr>
								<td>${details.vehicle}</td>
								<td>${details.details}</td>
								<td><a class="btn btn-primary button-margin" href="/vehicle/edit/${details.id}" >Edit</a> <a class="btn btn-primary button-margin" href="/vehicle/delete/${details.id}">Delete</a></td>
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