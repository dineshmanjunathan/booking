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
				<h3><b>Manage Charges</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="addCharge.jsp">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Charge Type</th>
							<th scope="col">Pay Type</th>
							<th scope="col">Charge Value</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${chargeListing}"
							varStatus="status">
							<tr>

								<td>${details.id}</td>
								<td>${details.chargetype}</td>
								<td>${details.paytype}</td>
								<td>${details.value}</td>
								<td><a class="btn btn-primary button-margin"
									href="/charge/edit/${details.id}" id="${details.id}">Edit</a>
									<a class="btn btn-primary button-margin"
									href="/charge/delete/${details.id}">Delete</a></td>
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