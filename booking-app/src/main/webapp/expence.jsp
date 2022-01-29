
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
				<h3><b>Expense</b></h3>
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
						<c:forEach var="expense" items="${expences}"
							varStatus="status">
							<tr>
								<td>${expense.expenseNumber}</td>
								<td>${expense.expenseDate}</td>
								<td>${expense.expenseBranch}</td>
								<td>${expense.expenseCategory}</td>
								<td>${expense.expenseSubCategory}</td>
								<td>${expense.description}</td>
								<td>${expense.paymentMode}</td>
								<td>${expense.amount}</td>
								<td><a class="btn btn-primary button-margin" href="/expence/edit/${expense.id}" id="${expense.id}">edit</a></td>
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