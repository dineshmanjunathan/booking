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
				<h3><b>OGPL Report</b></h3>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							
							<!-- <th scope="col">Booked on</th> -->
							<th scope="col">S.No</th>
							<th scope="col">OGPL No</th>
							<th scope="col">LR Number</th>
							<th scope="col">From</th>
							<th scope="col">To</th>
							<th scope="col">Vehicle No</th>
							<th scope="col">Driver</th>
							<th scope="col">Load Incharge</th>
							<th scope="col">Prepared by</th>
							<th scope="col">Pay Type</th>
							<th scope="col">Freight Value</th>
							<th scope="col">Loading Charge</th>
							<th scope="col">Fuel Charge</th>
							<th scope="col">Total Paid</th>
							<th scope="col">Total ToPay</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${OGPL}"
							varStatus="status">
							<tr>
								<td>${details.id}</td>
								<td>${details.ogplNo}</td>
								<td>${details.LRno}</td>
								<td>${details.fromLocation}</td>
								<td>${details.toLocation}</td>
								<td>${details.vehicleNo}</td>
								<td>${details.driver}</td>
								<td>${details.conductor}</td>
								<td>${details.preparedBy}</td>
								<td>${details.payType}</td>
								<td>${details.freightvalue}</td>
								<td>${details.loadingcharges}</td>
								<td>${details.doorpickcharges}</td>
								<td>${details.totalpaid}</td>
								<td>${details.totaltopay}</td>
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