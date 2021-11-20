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
		<div class="inner col-lg-10">
			<div style="width: 350px;">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form action="">
				<h3><b>Booking Inventory</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<!-- <a class="btn btn-primary button-margin" href="addLocation.jsp">Add</a> -->
						<a class="btn btn-primary button-margin" href="/inventoryMenu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							<th scope="col">LR</th>
							<th scope="col">From</th>
							<th scope="col">To</th>
							<th scope="col">To Pay</th>
							<th scope="col">From Name</th>
							<th scope="col">From Number</th>
							<th scope="col">To Name</th>
							<th scope="col">To Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${bookinginventory}"
							varStatus="status">
							<tr>

								<td>${details.lrNumber}</td>
								<td>${details.fromLocation}</td>
								<td>${details.toLocation}</td>
								<td>${details.topay}</td>
								<td>${details.fromName}</td>
								<td>${details.from_phone}</td>
								<td>${details.toName}</td>
								<td>${details.to_phone}</td>
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