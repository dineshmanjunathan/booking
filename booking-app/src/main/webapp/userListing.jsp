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
			<form action="">
				<h3><b>Manage User</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color: red" align="center">${errormsg}</p>
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="/user/add">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							
							<th scope="col">User Id</th>
							<th scope="col">Name</th>
							<th scope="col">Mobile No</th>
							<th scope="col">Location</th>
							<th scope="col">Role</th>
							<th scope="col">Action</th>		
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
								<c:choose>
								<c:when test="${details.userId eq 'ADMIN'}">
								<td></td>
								</c:when>
								<c:otherwise>
								<td><a class="btn btn-primary button-margin" href="/user/edit?id=${details.id}" id="${details.id}">Edit</a>
								 <a class="btn btn-primary button-margin" onclick="return confirm('Are you sure you want to delete?')"  
								 href="/user/delete?id=${details.id}">Delete</a></td>
								 </c:otherwise>
								 </c:choose>
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