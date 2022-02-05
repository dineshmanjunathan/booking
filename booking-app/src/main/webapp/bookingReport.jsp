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
		<div class="inner" style="padding-left: 4%;">

			<form action="">
				<h3>
					<b>Booking Report</b>
				</h3>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped"
					style="width: 100%">
					<thead>
						<tr>

							<th scope="col">S. No</th>
							<th scope="col">LR No</th>
							<th scope="col">From Location</th>
							<th scope="col">To Location</th>
							<!-- <th scope="col">Booked by (Login user)</th> -->
							<th scope="col">Booking date</th>
							<th scope="col">From Name</th>
							<th scope="col">From Number</th>
							<th scope="col">To Name</th>
							<th scope="col">To Number</th>
							<th scope="col">no of Item</th>
							<th scope="col">Weight</th>
							<th scope="col">Pay Type</th>
							<th scope="col">Feright Value</th>
							<th scope="col">Loading Charge</th>
							<th scope="col">Fuel Charge</th>
							<th scope="col">Total Charge</th>
							<th scope="col">Booked By</th>
							<th scope="col">With Bill</th>
							<th scope="col">Without Bill</th>
							<th scope="col">Cons Inv</th>
							<th scope="col">Bill Value</th>
							<th scope="col">Eway Bill</th>
							<th scope="col">Remark</th>
							<th scope="col">Value</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${Booking}" varStatus="status">
							<tr>
								<td>${details.id}</td>
								<td>${details.lrNumber}</td>
								<td>${details.fromLocation}</td>
								<td>${details.toLocation}</td>
								<td>${details.bookedOn}</td>
								<td>${details.fromName}</td>
								<td>${details.from_phone}</td>
								<td>${details.toName}</td>
								<td>${details.to_phone}</td>
								<td>${details.weight}</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
								<td>-</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
			</form>
		</div>
	</div>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>