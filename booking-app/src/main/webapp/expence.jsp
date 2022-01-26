
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
				<h3><b>Expence</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/expence/add">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:80%">
					<thead>
						<tr>
							<th scope="col">Expense No</th>
							<th scope="col">Date</th>
							<th scope="col">Branch</th>
							<th scope="col">Category</th>
							<th scope="col">Sub Category</th>
							<th scope="col">Description </th>
							<th scope="col">Payment Mode</th>
							<th scope="col">Amount</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${payOptionListing}"
							varStatus="status">
							<tr>
								<td>${details.payOption}</td>
								<td>${details.details}</td>
								<td>${details.payOption}</td>
								<td>${details.details}</td>
								<td>${details.payOption}</td>
								<td>${details.details}</td>
								<td>${details.payOption}</td>
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