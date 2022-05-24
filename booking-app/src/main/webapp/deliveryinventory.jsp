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
			
			<form action="" style="margin-left:30px">
			<h3>
					<b>Delivery Inventory</b>
				</h3>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/inventoryMenu">Back</a>
					</div>
				</div>
				
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							<th scope="col">LR</th>
							<th scope="col">From</th>
							<th scope="col">To</th>
							<th scope="col">Paid</th>
							<th scope="col">To Pay</th>
							<th scope="col">From Name</th>
							<th scope="col">From Number</th>
							<th scope="col">To Name</th>
							<th scope="col">To Number</th>
							<th scope="col">OGPL no</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${deliveryinventory}"
							varStatus="status">
							<tr>

								<td id="tdBook">${details.lrNumber}</td>
								<td id="tdBook">${details.fromLocation}</td>
								<td id="tdBook">${details.toLocation}</td>
								<td id="tdBook">${details.paid}</td>
								<td id="tdBook">${details.topay}</td>
								<td id="tdBook">${details.fromName}</td>
								<td id="tdBook">${details.from_phone}</td>
								<td id="tdBook">${details.toName}</td>
								<td id="tdBook">${details.to_phone}</td>
								<td id="tdBook">${details.ogplNo}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
			</form>
		</div>
	</div>
<style>
#tdBook
{
width:100px;
}
</style>
	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>