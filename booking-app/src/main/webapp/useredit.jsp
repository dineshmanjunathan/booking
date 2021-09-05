<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
			<form action="/register" method="post">
				<h3><b>User</b></h3>
				<br>
				<input type="hidden" class="form-control" name="id" id="id" value="${user.id}" required>
				
					<div class="form-row">
						<input type="text" class="form-control" name="userId" id="userId" placeholder="User ID" value="${user.userId}" required>
						<input type="text" class="form-control" name="name" id="name" placeholder="User Name" value="${user.name}" required>
					</div>
					<div class="form-row">
						<input type="password" class="form-control" name="password" id="password" placeholder="*******" value="${user.password}" required>
						
						<select name="location" id="location" class="form-control">
						<option value="">-Select location-</option>
						<c:forEach var="options" items="${locationList}"
							varStatus="status">
							<option value="${options.id}" ${options.id == user.location.id ? 'selected' : ''}>${options.location}</option>
						</c:forEach>
					</select>
					
					</div>
					<div class="form-row">
						<input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${user.email}" >

						<input type="tel" class="form-control" placeholder="Phone Number" pattern="[1-9]{1}[0-9]{9}" name="phonenumber" id="phonenumber" value="${user.phonenumber}" >
					</div>
					<div class="form-row">
						<select name="role" id="role" class="form-control">
																		<option value="">-Select role-</option>
																		<option value="ADMIN"
																			${user.role == 'ADMIN' ? 'selected' : ''}>ADMIN</option>
																		<option value="USER"
																			${user.role == 'USER' ? 'selected' : ''}>USER</option>
						</select>
						
					</div>
					<div class="row control-margin">
						<div class="col-md-4">
							<button type="submit" class="btn btn-primary button-margin" id="btnClear">Save</button>
						</div>
						<div class="col-md-4">
							<button type="reset" class="btn btn-primary button-margin" id="btnClear">Clear</button>
						</div>
						<div class="col-md-4">
							<a href="/userlisting"><button type="button" class="btn btn-primary button-margin" id="btnClear">Back</button></a>
						</div>
					</div>
			</form>
		</div>
	</div>

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>