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
				<h3><b>Location</b></h3>
				<p style="color: green" align="center">${successMessage}</p>
				<p style="color:red" align="center">${errormsg}</p>
				
				<div class="row control-margin">
					<div class="col-md-12">
						<a class="btn btn-primary button-margin" href="addLocation.jsp">Add</a>
						<a class="btn btn-primary button-margin" href="/menu">Back</a>
					</div>
				</div>
				<table id="data-table" class="table table-striped" style="width:100%">
					<thead>
						<tr>
							<th scope="col">Code</th>
							<th scope="col">Location</th>
							<th scope="col">Address</th>
							<th scope="col">Unload Charge</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="details" items="${locationListing}"
							varStatus="status">
							<tr>

								<td>${details.id}</td>
								<td>${details.location}</td>
								<td>${details.address}</td>
								<td>${details.uploadingCharge}</td>
								
								<td><a class="btn btn-primary button-margin"
									href="/location/edit/${details.id}" id="${details.id}">Edit</a>
									<a class="btn btn-primary button-margin"
									 onclick="deleteItem('${details.id}')">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<br>
			</form>
		</div>
	</div>

	<script src="js/main.js"></script>
	
	<script type="text/javascript">
	function deleteItem(id) {
		
		if (confirm("Are you sure to delete the Location ?") == true) {
			
			window.location.href = "/locationDelete?id=" + id;
}
	}
	</script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>