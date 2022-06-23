<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="header.jsp"%>
</head>

<body>
	<div class="wrapper">
		<div class="inner" style=" padding-left: 4%; ">
			
			<form action="">
				<h3><b>Delivery Report</b></h3>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="delivery-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							
							<th scope="col">LR</th>
							<th scope="col">OGPL</th>
							<th scope="col">From Name</th>
							<th scope="col">From Number</th>
							<th scope="col">To Name</th>
							<th scope="col">To Number</th>
							<th scope="col">Delivery Date</th>
							<th scope="col">Delivery By</th>
							<th scope="col">Unloading Charge</th>
							<th scope="col">Door Delivery Charge</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">Total</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${DeliveryReport}"
							varStatus="status">
							<tr>
								<td>${details.LRNo}</td>
								<td>${details.ogpl}</td>
								<td>${details.fromName}</td>
								<td>${details.from_phone}</td>
								<td>${details.toName}</td>
								<td>${details.to_phone}</td>
								<td>${details.deliveryDate}</td>
								<td>${details.deliveredBy}</td>
								<td>${details.unloadingCharges}</td>
								<td>${details.doorDeliveryCharges}</td>
								<td>${details.paid}</td>
								<td>${details.toPay}</td>
								<td>${details.total}</td>
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