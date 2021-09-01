<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>BookingApp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- MATERIAL DESIGN ICONIC FONT -->
<link rel="stylesheet"
	href="../../fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

<!-- STYLE CSS -->
<link rel="stylesheet" href="../../css/incoming/style.css">
<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/bootstrap.min.css">
<%@ include file="header.jsp"%>
	<meta charset="ISO-8859-1">
</head>

<body>
	<div class="wrapper">
		<div class="inner">
			<div class="image-holder">
				<img src="../../img/product/parcel.jpg" alt="">
			</div>
			<form action="">
				<h3>Manage User</h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/user/add">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr>
							
							<th scope="col">User Id</th>
							<th scope="col">Name</th>
							<th scope="col">Mobile No</th>
							<th scope="col">Location</th>
							<th scope="col">Role</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${userList}"
							varStatus="status">
							<tr>
								
								<td>${details.userId}</td>
								<td>${details.name}</td>
								<td>${details.phonenumber}</td>
								<td>${details.location.location}</td>
								<td>${details.role}</td>
								<td><a class="btn btn-primary button-margin" href="/user/edit?id=${details.id}" id="${details.id}">Edit</a>
								 <a class="btn btn-primary button-margin" onclick="return confirm('Are you sure you want to delete?')"  
								 href="/user/delete?id=${details.id}">Delete</a></td>
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